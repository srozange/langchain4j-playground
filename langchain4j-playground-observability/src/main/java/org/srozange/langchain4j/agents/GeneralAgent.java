package org.srozange.langchain4j.agents;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import io.quarkiverse.langchain4j.RegisterAiService;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RegisterAiService
public interface GeneralAgent {

    @UserMessage("""
                        You are a general agent.
                        Always include in your answer that you are a general agent.
                        Answer in french.
                        Answer the question {{request}}
                        """)
    String invoke(@V("request") String request);

}
