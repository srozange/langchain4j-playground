package org.srozange.langchain4j;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.mcp.runtime.McpToolBox;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RegisterAiService
public interface Bot {

    @SystemMessage("""
            Réponds directement et uniquement à la question, sans phrases d’introduction, sans métacommentaires et sans justification. Utilise uniquement la même langue que la question. Si la question est en français, la réponse doit être entièrement en français. Formate toujours la réponse en Markdown.
            """)
    @McpToolBox
    String chat(@MemoryId String memoryId, @UserMessage String question);
}