package org.srozange.langchain4j.agents;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import org.srozange.langchain4j.RequestCategory;

public interface CategorizerAgent {

    @Agent("Categorizes a user request")
    @UserMessage("""
            Analyze the following user request and :
            - categorize it as 'history' if it is related to the historical domain
            - categorize it as 'mathematics' if it is related to the mathematical domain
            - categorize it as 'unknown' if it does not belong to either category
            Reply with only the chosen category word and nothing else.
            The user request is: '{{request}}'.
            """)
    RequestCategory classify(@V("request") String request);

}