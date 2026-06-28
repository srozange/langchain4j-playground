# langchain4j-playground-observability

Run the application:
```bash
./mvnw quarkus:dev
```

Interact via the command line prompt, then access traces in Grafana.

The Grafana URL is printed in the startup logs:
```
2026-06-28 12:43:24,804 WARNING [io.quarkus.opentelemetry.runtime.exporter.otlp.sender.VertxHttpSender] (vert.x-eventloop-thread-9) Failed to export . The request could not be executed. Full error message: Connection refused: localhost/127.0.0.1:4317
```

Navigate to: **Explore → Tempo → Search → Service Name: `langchain4j-observability`**
