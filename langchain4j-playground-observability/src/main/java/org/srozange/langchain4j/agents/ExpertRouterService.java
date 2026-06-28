package org.srozange.langchain4j.agents;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.srozange.langchain4j.RequestCategory;

@ApplicationScoped
public class ExpertRouterService {

    @Inject
    CategorizerAgent categorizerAgent;

    @Inject
    MathExpertAgent mathExpertAgent;

    @Inject
    HistoryExpertAgent historyExpertAgent;

    @Inject
    GeneralAgent generalAgent;

    public String route(String request) {
        RequestCategory category = categorizerAgent.classify(request);
        return switch (category) {
            case MATHEMATICS -> mathExpertAgent.invoke(request);
            case HISTORY -> historyExpertAgent.invoke(request);
            case UNKNOWN -> generalAgent.invoke(request);
        };
    }
}
