# langchain4j-playground-observability

## Default Mode — Grafana / Tempo (LGTM)

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

### Dashboard Tokens

Import [`grafana/token-usage-dashboard.json`](grafana/token-usage-dashboard.json): **Dashboards → Import → Upload JSON file**, then select **Prometheus** as the data source. Panels: input/output/total tokens, cost, and tokens/day by type, model, and sub-agent.

## Jaeger Mode

Activates a `jaeger` profile that disables the LGTM dev-service and exports traces to a local Jaeger container (native OTLP).

```bash
# 1. Start Jaeger
docker compose up -d
# 2. Run the app on the jaeger profile
./mvnw quarkus:dev -Dquarkus.profile=jaeger
# 3. Ask a question, then open the Jaeger UI
#    http://localhost:16686  →  Service: langchain4j-observability
```

Without `-Dquarkus.profile=jaeger`, the default behavior (LGTM / Grafana) remains unchanged.

## Langfuse Mode (local, via Dev Service)

The `quarkus-langfuse` extension (added only under this profile via a Maven profile) starts a **complete local Langfuse** and wires the application automatically — no account, no key required.

```bash
# Docker required
./mvnw quarkus:dev -Dquarkus.profile=langfuse
```

The local Langfuse URL is displayed in the logs (e.g., `http://localhost:32807`, login `quarkus@quarkus.io` / `quarkuslangfuse`). Ask a question → the `expert-router.route` trace and its 2 sub-agents appear there.

## Token Consumption (Metrics)

quarkus-langchain4j automatically emits a token counter (nothing to code), exported to Prometheus/Mimir (LGTM) → visible in **default mode**.

Metric: `gen_ai_client_token_usage_total` — labels `gen_ai_token_type` (`input`/`output`), `gen_ai_request_model`, `ai_service_class_name` (per sub-agent). Also `gen_ai_client_estimated_cost_total` (cost).

PromQL (Grafana → Explore → Prometheus source):
```promql
sum by (gen_ai_token_type) (increase(gen_ai_client_token_usage_total[1d]))   # tokens/day
sum(increase(gen_ai_client_estimated_cost_total[1d]))                        # cost/day
```

The cost is **0** by default: [`GeminiCostEstimator`](src/main/java/org/srozange/langchain4j/GeminiCostEstimator.java) provides a price per token (indicative, to be adjusted). Dashboard ready to import.
