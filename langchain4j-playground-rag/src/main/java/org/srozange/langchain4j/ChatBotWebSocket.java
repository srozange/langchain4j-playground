package org.srozange.langchain4j;

import io.quarkus.websockets.next.OnOpen;
import io.quarkus.websockets.next.OnTextMessage;
import io.quarkus.websockets.next.WebSocket;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@WebSocket(path = "/chatbot")
public class ChatBotWebSocket {

    private final Bot bot;

    public ChatBotWebSocket(Bot bot) {
        this.bot = bot;
    }

    @OnOpen
    public String onOpen() {
        return "Hello, comment je peux t'aider ? (j'utilise le modèle <b>" + ModelUtils.detectCurrentModel(ConfigProvider.getConfig()) + "</b>)";
    }

    @OnTextMessage
    public String onMessage(String message) {
        return ChatUtils.computeChatResponse(message, bot::chat);
    }

}