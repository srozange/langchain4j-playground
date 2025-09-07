package org.srozange.langchain4j;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import org.zeroturnaround.exec.ProcessExecutor;
import org.zeroturnaround.exec.ProcessResult;

@ApplicationScoped
public class CommandExecutorTool {

    @Tool("Execute a command")
    public String execute(@P("The command to execute") String command) {
        try {
            Log.info("Execution de la commande : " + command);
            ProcessResult result = new ProcessExecutor().command("/bin/sh", "-c", command).readOutput(true).execute();
            return result.outputUTF8();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
