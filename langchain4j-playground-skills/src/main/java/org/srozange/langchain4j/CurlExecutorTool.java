package org.srozange.langchain4j;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import org.zeroturnaround.exec.ProcessExecutor;
import org.zeroturnaround.exec.ProcessResult;

@ApplicationScoped
public class CurlExecutorTool {

    @Tool("Exécute une commande curl et retourne la sortie")
    public String executeCurl(@P("Les arguments passés à curl (ex: -s 'http://localhost:8081/beans')") String args) {
        try {
            String command = "curl " + args;
            Log.info("Exécution de la commande : " + command);
            ProcessResult result = new ProcessExecutor().command("/bin/sh", "-c", command).readOutput(true).execute();
            return result.outputUTF8();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
