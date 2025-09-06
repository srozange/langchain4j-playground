package org.srozange.langchain4j;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import jakarta.enterprise.context.ApplicationScoped;
import org.zeroturnaround.exec.ProcessExecutor;
import org.zeroturnaround.exec.ProcessResult;

@ApplicationScoped
public class CommandExecutorTool {

    @Tool("Execute a command")
    public String execute(@P("The command to execute") String command) {
        try {
            ProcessResult result = new ProcessExecutor().command("java", "-version").execute();
            return result.outputUTF8();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
