package org.srozange.langchain4j.agents;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import io.quarkiverse.langchain4j.RegisterAiService;
import jakarta.enterprise.context.ApplicationScoped;
import org.srozange.langchain4j.tools.ElapsedTimeTool;

@ApplicationScoped
@RegisterAiService(tools = ElapsedTimeTool.class)
public interface MathExpertAgent {

    @UserMessage("""
                        You are a mathematics expert.
                        Always include in your answer that you are a mathematics expert.
                        Answer in french.
                        Answer the question {{request}}
                        """)
    String invoke(@V("request") String request);
}