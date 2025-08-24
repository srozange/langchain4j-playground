package org.srozange.langchain4j;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import jakarta.enterprise.context.ApplicationScoped;

@RegisterAiService
public interface SummarizationAgent {

    @SystemMessage("You are a professional text summarizer. Your task is to provide a concise summary of the given text.")
    @UserMessage("Summarize the following text: {text}")
    String summarize(String text);
}