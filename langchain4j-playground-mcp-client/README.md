# langchain4j-playground-mcp-client

Set the OpenAI API key in `OPENAI_API_KEY` environment variable.

Run the coffee application:
```bash
cd ../langchain4j-playground-mcp-server/coffee-app
docker compose up --force-recreate
```

Run the backend:
```bash
mvn quarkus:dev
```

Start the web application:
```shell script
cd ../langchain4j-playground-web
./mvnw quarkus:dev
```

Access the chat UI: http://localhost:8082