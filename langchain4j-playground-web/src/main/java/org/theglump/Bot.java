package org.theglump;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

/**
 * \{@code
 *     @Inject
 *     WebBot bot;
 *
 *     @POST
 *     @Produces(MediaType.TEXT_PLAIN)
 *     public String chat(String q) {
 *         return bot.chat(q);
 *     }
 * }
 */
@RegisterAiService // no need to declare a retrieval augmentor here, it is automatically generated and discovered
public interface Bot {

    @SystemMessage("""
            Your response must be polite, use the same language as the question, and be relevant to the question.
            When you don't know, respond that you don't know the answer.
            """)
    String chat(@UserMessage String question);
}