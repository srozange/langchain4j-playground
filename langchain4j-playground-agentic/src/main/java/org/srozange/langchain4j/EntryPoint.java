package org.srozange.langchain4j;

import dev.langchain4j.agentic.AgenticServices;
import dev.langchain4j.agentic.UntypedAgent;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import org.srozange.langchain4j.agents.*;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Scanner;

public class EntryPoint {

    private static final ChatModel CHAT_MODEL = createChatModel(false);

    public static void main(String[] args) {

        CategorizerAgent categorizerAgent = AgenticServices
                .agentBuilder(CategorizerAgent.class)
                .chatModel(CHAT_MODEL)
                .outputName("category")
                .build();

        UntypedAgent routerAgent = AgenticServices.conditionalBuilder()
                .subAgents( agenticScope -> agenticScope.readState("category") == RequestCategory.MATHEMATICS, createAgent(MathExpertAgent.class))
                .subAgents( agenticScope -> agenticScope.readState("category") == RequestCategory.HISTORY, createAgent(HistoryExpertAgent.class))
                .subAgents( agenticScope -> agenticScope.readState("category") == RequestCategory.UNKNOWN, createAgent(GeneralAgent.class))
                .build();

        ExpertRouterAgent expertRouterAgent = AgenticServices
                .sequenceBuilder(ExpertRouterAgent.class)
                .subAgents(categorizerAgent, routerAgent)
                .outputName("response")
                .build();

        System.out.println("Hello to the Agentic demo (Using the " + CHAT_MODEL.provider().toString() + " AI provider)");
        try (Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
            while (true) {
                System.out.print("\nYou > ");
                String userInput = scanner.nextLine();
                if ("quit".equalsIgnoreCase(userInput) || "exit".equalsIgnoreCase(userInput)) {
                    break;
                }
                System.out.print("\nAgent > ");
                String response = expertRouterAgent.ask(userInput);
                System.out.println(response);
            }
        }
    }

    private static <T> T createAgent(Class<T> clazz) {
        return AgenticServices
                .agentBuilder(clazz)
                .chatModel(CHAT_MODEL)
                .outputName("response")
                .build();
    }

    private static ChatModel createChatModel(boolean remote) {
        if (!remote) {
            return OllamaChatModel.builder()
                    .modelName("qwen2.5:3b")
                    .baseUrl("http://localhost:11434")
                    .timeout(Duration.ofMinutes(2))
                    .build();
        }
        return GoogleAiGeminiChatModel.builder()
                .apiKey(System.getenv("GOOGLE_API_KEY"))
                .modelName("gemini-2.0-flash")
                .build();
    }
}