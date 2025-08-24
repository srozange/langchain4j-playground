# langchain4j-playground-rag

## Using the application

Place documents to ingest in: `easy-rag-catalog/`

Start Ollama (if `quarkus.langchain4j.ollama.base-url` is active):
```shell script
ollama serve
```

Run the backend:
```shell script
./mvnw quarkus:dev
```

Start the web application:
```shell script
cd ../langchain4j-playground-web
./mvnw quarkus:dev
```

Ask a question via command line:
```shell
curl -X POST http://localhost:8080/api -H "Content-Type: text/plain" -d "How are you today?"
```

Access the UI to ask questions: http://localhost:8082

QDrant Dashboard: http://localhost:32778/dashboard (use `docker ps` to find the port, e.g., 0.0.0.0:32778->6333/tcp)