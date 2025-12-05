package org.srozange.langchain4j;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ModelUtils {

    private static final String[] PROVIDER_MODEL_KEYS = {
            "quarkus.langchain4j.openai.chat-model.model-name",
            "quarkus.langchain4j.anthropic.chat-model.model-name",
            "quarkus.langchain4j.mistralai.chat-model.model-name",
            "quarkus.langchain4j.ollama.chat-model.model-id",
            "quarkus.langchain4j.ai.gemini.chat-model.model-id"
    };

    public static ModelInfo detectCurrentModel(Config config) {
        java.util.Optional<String> provider = config.getOptionalValue("quarkus.langchain4j.chat-model.provider", String.class);
        return Stream.of(PROVIDER_MODEL_KEYS)
                .map(modelKey -> createModelInfo(config, modelKey))
                .filter(Objects::nonNull)
                .filter(modelInfo -> provider.isPresent() && provider.get().equals(modelInfo.provider))
                .findFirst()
                .orElse(new ModelInfo("Unknown", "Unknown", config.getValue("quarkus.application.name", String.class)));
    }

    private static ModelInfo createModelInfo(Config config, String modelKey) {
        return config.getOptionalValue(modelKey, String.class)
                .map(option -> new ModelInfo(getProvider(modelKey), option, config.getValue("quarkus.application.name", String.class)))
                .orElse(null);
    }

    private static String getProvider(String modelKey) {
        Matcher matcher = Pattern.compile("quarkus\\.langchain4j\\.([a-z\\-\\.]+)\\.chat-model.*").matcher(modelKey);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        return "";
    }

    public record ModelInfo(String provider, String modelName, String appName) {
        @Override
        public String toString() {
            return  (provider + "/" + modelName).toLowerCase() + " - " + appName;
        }
    }
}