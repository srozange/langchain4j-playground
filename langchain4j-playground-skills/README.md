# langchain4j-playground-skills

Run the backend:
```bash
mvn quarkus:dev
```

Start the Coffee App application (http://localhost:8081):
```bash
cd ../langchain4j-playground-mcp-server/coffee-app
docker compose up --force-recreate
```

Start the web application:
```shell script
cd ../../langchain4j-playground-web
./mvnw quarkus:dev
```

Access the chat UI: http://localhost:8082