package org.srozange.langchain4j.agents;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.V;

public interface ExpertRouterAgent {

    @Agent
    String ask(@V("request") String request);
}
