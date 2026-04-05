package org.srozange.langchain4j;

import io.quarkus.runtime.util.StringUtil;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.function.Function;

public class ChatUtils {

    public static String computeChatResponse(String message, Function<String, String> modelCall) {
        long startTime = System.nanoTime();
        return convertMarkdownToHtml(modelCall.apply(sanitized(message)))
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

    public static String computeToolExecutedEvent(dev.langchain4j.observability.api.event.ToolExecutedEvent toolExecutedEvent) {
        return "<i style=\"color: blueviolet;\">Tool called :<ul><li>name : " + toolExecutedEvent.request().name()
                + "</li><li>arguments : " + toolExecutedEvent.request().arguments()
                + "</li><li>result : " + truncate(toolExecutedEvent.resultText(), 600) + "</li></ul></i>";
    }

    private static String truncate(String str, int maxLength) {
        if (str == null || str.length() <= maxLength) return str;
        return str.substring(0, maxLength) + "...";
    }

}
