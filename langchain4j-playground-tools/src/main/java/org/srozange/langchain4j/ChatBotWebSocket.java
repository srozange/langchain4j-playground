package org.srozange.langchain4j;

import dev.langchain4j.observability.api.event.ToolExecutedEvent;
import io.quarkiverse.langchain4j.observability.AiServiceSelector;
import io.quarkus.websockets.next.OnOpen;
import io.quarkus.websockets.next.OnTextMessage;
import io.quarkus.websockets.next.WebSocket;
import io.quarkus.websockets.next.WebSocketConnection;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@WebSocket(path = "/chatbot")
public class ChatBotWebSocket {

    private final Bot bot;

    private final WebSocketConnection connection;

    public ChatBotWebSocket(Bot bot, WebSocketConnection connection) {
        this.bot = bot;
        this.connection = connection;
    }

    @OnOpen
    public String onOpen() {
        return "Hello, comment je peux t'aider ? (j'utilise le modèle <b>" + ModelUtils.detectCurrentModel(ConfigProvider.getConfig()) + "</b>)";
    }

    @OnTextMessage
    public String onMessage(String message, WebSocketConnection connection) {
        return ChatUtils.computeChatResponse(message, (msg) -> bot.chat(connection.id(), msg));
    }

    public void botToolExecuted(@Observes @AiServiceSelector(Bot.class) ToolExecutedEvent toolExecutedEvent) {
        connection.sendTextAndAwait(ChatUtils.computeToolExecutedEvent(toolExecutedEvent));
    }
}