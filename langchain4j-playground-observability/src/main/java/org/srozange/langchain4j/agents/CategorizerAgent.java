package org.srozange.langchain4j.agents;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import io.quarkiverse.langchain4j.RegisterAiService;
import jakarta.enterprise.context.ApplicationScoped;
import org.srozange.langchain4j.RequestCategory;

@ApplicationScoped
@RegisterAiService
public interface CategorizerAgent {

    @UserMessage("""
            Analyze the following user request and :
            - categorize it as 'HISTORY' if it is related to the historical domain
            - categorize it as 'MATHEMATICS' if it is related to the mathematical domain
            - categorize it as 'UNKNOWN' if it does not belong to either category
            Reply with only the chosen category word and nothing else.
            The user request is: '{{request}}'.
            """)
    RequestCategory classify(@V("request") String request);

}