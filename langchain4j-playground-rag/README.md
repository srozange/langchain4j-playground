# langchain4j-playground-rag

## Use app

Put documents to ingest in : easy-rag-catalog/

Run application :
```shell script
./mvnw quarkus:dev
```

Ask a question via command line :
```shell
curl -X POST http://localhost:8080/api -H "Content-Type: text/plain" -d "How are you today ?"
```

Ask a question via UI : http://localhost:8080

Dashboard QDrant : http://localhost:32778/dashboard  (type ```docker ps``` to find port : eg. 0.0.0.0:32778->6333/tcp)

## About LangChain4j Easy RAG

This code is a very basic sample service to start developing with Quarkus LangChain4j using Easy RAG.

This code is set up to use OpenAI as the LLM, thus you need to set the `QUARKUS_LANGCHAIN4J_OPENAI_API_KEY` environment variable to your OpenAI API key.

In `./easy-rag-catalog/` you can find a set of example documents that will be used to create the RAG index which the bot (`src/main/java/org/acme/Bot.java`) will ingest.

On first run, the bot will create the RAG index and store it in `easy-rag-catalog.json` file and reuse it on subsequent runs.
This can be disabled by setting the `quarkus.langchain4j.easy-rag.reuse-embeddings.enabled` property to `false`.

Add it to a Rest endpoint:
```java
    @Inject
    Bot bot;
    
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String chat(String q) {
        return bot.chat(q);
    }
```

In a more complete example, you would have a web interface and use websockets that would provide more interactive experience, see [ChatBot Easy RAG Sample](https://github.com/quarkiverse/quarkus-langchain4j/tree/main/samples/chatbot-easy-rag) for such an example.