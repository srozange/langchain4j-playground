package org.srozange.langchain4j.agents;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface GeneralAgent {

    @Agent("A general agent")
    @UserMessage("""
                        You are a general agent.
                        Always include in your answer that you are a general agent.
                        Answer in french.
                        Answer the question {{request}}
                        """)
    String invoke(@V("request") String request);

}
