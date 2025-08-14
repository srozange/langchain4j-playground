# langchain4j-playground-rag

## Use app

Put documents to ingest in : easy-rag-catalog/

Start Ollama (if ```quarkus.langchain4j.ollama.base-url``` active) :
```shell script
ollama --serve
```

Run backend :
```shell script
./mvnw quarkus:dev
```

Start web app :
```shell script
cd ../langchain4j-playground-web
./mvnw quarkus:dev
```

Ask a question via command line :
```shell
curl -X POST http://localhost:8080/api -H "Content-Type: text/plain" -d "How are you today ?"
```

Ask a question via UI : http://localhost:8082

Dashboard QDrant : http://localhost:32778/dashboard  (type ```docker ps``` to find port : eg. 0.0.0.0:32778->6333/tcp)