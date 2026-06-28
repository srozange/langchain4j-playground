package org.srozange.langchain4j.agents;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import io.quarkiverse.langchain4j.RegisterAiService;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RegisterAiService
public interface HistoryExpertAgent {

    @UserMessage("""
                        You are a history expert.
                        Always include in your answer that you are a history expert.
                        Answer in french.
                        Answer the question {{request}}
                        """)
    String invoke(@V("request") String request);
}