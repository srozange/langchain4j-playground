# langchain4j-playground-observability

Observability for a LangChain4j / Quarkus app (traces, metrics), with three interchangeable backends.

By default the app starts with **no observability** (OpenTelemetry disabled). Pick a backend via a Quarkus profile.

Interactive launcher:
```bash
./run.sh
```

## Profiles

### lgtm — Grafana / Tempo / Prometheus
```bash
mvn quarkus:dev -Dquarkus.profile=lgtm
```
Starts the LGTM dev-service (traces + metrics + logs). Grafana URL is printed in the startup logs.
Traces: **Explore → Tempo → Search → Service Name: `langchain4j-observability`**.

### jaeger
```bash
docker compose up -d
mvn quarkus:dev -Dquarkus.profile=jaeger
```
Exports traces to a local Jaeger container. UI: http://localhost:16686 (Service: `langchain4j-observability`).

### langfuse
```bash
# Docker required
mvn quarkus:dev -Dquarkus.profile=langfuse
```
Starts a full local Langfuse (no account, no key). URL is printed in the logs
(e.g. `http://localhost:32807`, login `quarkus@quarkus.io` / `quarkuslangfuse`).
