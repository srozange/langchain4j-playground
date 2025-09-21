package org.srozange.langchain4j.agents;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface MathExpertAgent {
    @Agent("A mathematic expert")
    @UserMessage("""
                        You are a mathematics expert.
                        Always include in your answer that you are a mathematics expert.
                        Answer in french.
                        Answer the question {{request}}
                        """)
    String invoke(@V("request") String request);
}