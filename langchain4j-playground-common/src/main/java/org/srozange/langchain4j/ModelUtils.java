package org.srozange.langchain4j;

import org.eclipse.microprofile.config.Config;
import java.util.Optional;

public class ModelUtils {

    public static ModelInfo detectCurrentModel(Config config) {

        Optional<String> openaiApiKey = config.getOptionalValue("quarkus.langchain4j.openai.api-key", String.class);
        Optional<String> openaiModelName = config.getOptionalValue("quarkus.langchain4j.openai.chat-model.model-name", String.class);
        if (openaiApiKey.isPresent() && openaiModelName.isPresent()) {
            return new ModelInfo("OpenAI", openaiModelName.get(), "External API");
        }

        Optional<String> ollamaBaseUrl = config.getOptionalValue("quarkus.langchain4j.ollama.base-url", String.class);
        Optional<String> ollamaModelId = config.getOptionalValue("quarkus.langchain4j.ollama.chat-model.model-id", String.class);
        if (ollamaBaseUrl.isPresent() && ollamaModelId.isPresent()) {
            return new ModelInfo("Ollama", ollamaModelId.get(),
                    ollamaBaseUrl.orElse("http://localhost:11434"));
        }

        Optional<String> anthropicApiKey = config.getOptionalValue("quarkus.langchain4j.anthropic.api-key", String.class);
        Optional<String> anthropicModelName = config.getOptionalValue("quarkus.langchain4j.anthropic.chat-model.model-name", String.class);
        if (anthropicApiKey.isPresent() && anthropicModelName.isPresent()) {
            return new ModelInfo("Anthropic", anthropicModelName.get(), "External API");
        }

        Optional<String> mistralApiKey = config.getOptionalValue("quarkus.langchain4j.mistralai.api-key", String.class);
        Optional<String> mistralModelName = config.getOptionalValue("quarkus.langchain4j.mistralai.chat-model.model-name", String.class);
        if (mistralApiKey.isPresent() && mistralModelName.isPresent()) {
            return new ModelInfo("Mistral AI", mistralModelName.get(), "External API");
        }

        return new ModelInfo("Unknown", "Unknown", "Unknown");
    }

    public record ModelInfo(String provider, String modelName, String endpoint) {
        @Override
        public String toString() {
            return  (provider + "/" + modelName).toLowerCase();
        }
    }
}
