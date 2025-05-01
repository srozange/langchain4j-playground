# langchain4j-playground-mcp-server

## mcp-server

Création du jar dans target/ :
```bash
mvn clean package
```

## coffe-app

Lancement de l'appli (http://localhost:8081) :
```bash
cd coffee-app
docker compose up --force-recreate
```

## Claude desktop

Dans le fichier claude_desktop_config.json :

```xml
{
  "mcpServers": {
    "favorite-coffee": {
      "command": "java",
      "args": ["-jar", "C:/dev/workspace/langchain4j-playground-mcp-server-1.0-SNAPSHOT-runner.jar"]
    }
  }
}
```