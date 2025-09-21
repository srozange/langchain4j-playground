package org.srozange.langchain4j.agents;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import org.srozange.langchain4j.RequestCategory;

public interface CategorizerAgent {

    @Agent("Categorizes a user request")
    @UserMessage("""
            Analyze the following user request and categorize it as 'mathematics' or 'history'.
            If the request doesn't belong to either of these categories, categorize it as 'unknown'.
            Reply with only one of those words and nothing else.
            The user request is: '{{request}}'.
            """)
    RequestCategory classify(@V("request") String request);

}