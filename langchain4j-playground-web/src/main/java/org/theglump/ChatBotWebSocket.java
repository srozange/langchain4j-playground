package org.theglump;

import io.quarkus.websockets.next.OnOpen;
import io.quarkus.websockets.next.OnTextMessage;
import io.quarkus.websockets.next.WebSocket;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@WebSocket(path = "/chatbot")
public class ChatBotWebSocket {

    @ConfigProperty(name = "quarkus.langchain4j.ollama.chat-model.model-id", defaultValue = "Unknown")
    String modelId;

    private final Bot bot;

    public ChatBotWebSocket(Bot bot) {
        this.bot = bot;
    }

    @OnOpen
    public String onOpen() {
        return "Hello, comment je peux t'aider ? (j'utilise le modèle <b>" + modelId + "</b>)";
    }

    @OnTextMessage
    public String onMessage(String message) {
        long startTime = System.nanoTime();
        return convertMarkdownToHtml(bot.chat(sanitized(message)))
                + "<br>"
                + getEllapsedTimeAsHtml(startTime);
    }

    private static String sanitized(String message) {
        return message.replaceAll("</?p>", "");
    }

    private static String convertMarkdownToHtml(String markdown) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }

    private static String getEllapsedTimeAsHtml(long startTime) {
        long endTime = System.nanoTime();
        long durationInSeconds = (endTime - startTime) / 1_000_000_000;
        long minutes = durationInSeconds / 60;
        double seconds = durationInSeconds % 60;
        return "<p><i style='font-size:12px'>Temps écoulé : " + minutes + " min " + String.format("%.0f", seconds) + " sec</i></p>";
    }

}