# langchain4j-playground-a2a

Agent-to-Agent (A2A) text summarization service using LangChain4j and Quarkus.

Code sample from this [article](https://www.the-main-thread.com/p/build-ai-agent-java-quarkus-langchain4j-a2a).

## Using the application

Run the A2A agent:
```shell script
./mvnw quarkus:dev
```

Run the A2A inspector (for testing):
```shell script
# Wsl :  
docker run --rm --add-host=host.docker.internal:$(cat /etc/resolv.conf | grep nameserver | awk '{print $2}') -p 8081:8080 srozange/a2a-inspector

# Mac/Win :    
docker run --rm -p 8081:8080 srozange/a2a-inspector
```

Access the A2A inspector: http://localhost:8081/

Agent card URL: `host.docker.internal:8080`