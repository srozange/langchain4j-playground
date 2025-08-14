# langchain4j-playground-mcp-client

Set open ai api key in ```OPENAI_API_KEY```

Run coffee-app :
```bash
cd ../langchain4j-playground-mcp-server/coffee-app
docker compose up --force-recreate
```

Run backend : 
```bash
mvn quarkus:dev
```

Start web app :
```shell script
cd ../langchain4j-playground-web
./mvnw quarkus:dev
```

Access chatui : http://localhost:8082