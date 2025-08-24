package org.srozange.langchain4j;

import org.eclipse.microprofile.config.Config;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ModelUtils {

    private static final String[] PROVIDER_MODEL_KEYS = {
            "quarkus.langchain4j.openai.chat-model.model-name",
            "quarkus.langchain4j.anthropic.chat-model.model-name",
            "quarkus.langchain4j.mistralai.chat-model.model-name",
            "quarkus.langchain4j.ollama.chat-model.model-id"
    };

    public static ModelInfo detectCurrentModel(Config config) {
        return Stream.of(PROVIDER_MODEL_KEYS)
                .map(modelKey -> createModelInfo(config, modelKey))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(new ModelInfo("Unknown", "Unknown"));
    }

    private static ModelInfo createModelInfo(Config config, String modelKey) {
        return config.getOptionalValue(modelKey, String.class)
                .map(option -> new ModelInfo(getProvider(modelKey), option))
                .orElse(null);
    }

    private static String getProvider(String modelKey) {
        Matcher matcher = Pattern.compile("quarkus\\.langchain4j\\.([a-z\\-]+)\\..*").matcher(modelKey);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        return "";
    }

    public record ModelInfo(String provider, String modelName) {
        @Override
        public String toString() {
            return  (provider + "/" + modelName).toLowerCase();
        }
    }
}