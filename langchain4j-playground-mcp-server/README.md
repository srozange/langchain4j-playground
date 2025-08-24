# langchain4j-playground-mcp-server

## MCP Server

Build the JAR in target/:
```bash
mvn clean package
```

## Coffee App

Start the application (http://localhost:8081):
```bash
cd coffee-app
docker compose up --force-recreate
```

## Claude Desktop

In the `claude_desktop_config.json` file:

```json
{
  "mcpServers": {
    "favorite-coffee": {
      "command": "java",
      "args": ["-jar", "C:/dev/workspace/langchain4j-playground-mcp-server-1.0-SNAPSHOT-runner.jar"]
    }
  }
}
```