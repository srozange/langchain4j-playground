package org.srozange.langchain4j;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.ToolBox;
import io.quarkiverse.langchain4j.skills.SkillsSystemMessageProvider;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RegisterAiService(systemMessageProviderSupplier = SkillsSystemMessageProvider.class)
public interface Bot {
    @ToolBox(CurlExecutorTool.class)
    String chat(@MemoryId String memoryId, @UserMessage String question);
}