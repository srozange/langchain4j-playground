# langchain4j-playground-observability

## Mode par défaut — Grafana / Tempo (LGTM)

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

### Dashboard tokens

Importe [`grafana/token-usage-dashboard.json`](grafana/token-usage-dashboard.json) : **Dashboards → Import → Upload JSON file**, puis choisis la source **Prometheus**. Panneaux : tokens input/output/total, coût, et tokens/jour par type, modèle et sous-agent.

## Mode Jaeger

Active un profil `jaeger` qui désactive le dev-service LGTM et exporte les traces vers un conteneur Jaeger local (OTLP natif).

```bash
# 1. Lancer Jaeger
docker compose up -d
# 2. Lancer l'app sur le profil jaeger
./mvnw quarkus:dev -Dquarkus.profile=jaeger
# 3. Poser une question, puis ouvrir l'UI Jaeger
#    http://localhost:16686  →  Service: langchain4j-observability
```

Sans `-Dquarkus.profile=jaeger`, le comportement par défaut (LGTM / Grafana) reste inchangé.

## Mode Langfuse (local, via Dev Service)

L'extension `quarkus-langfuse` (ajoutée seulement sous ce profil, via un profil Maven) démarre un **Langfuse local complet** et câble l'appli automatiquement — aucun compte, aucune clé.

```bash
# Docker requis
./mvnw quarkus:dev -Dquarkus.profile=langfuse
```

L'URL du Langfuse local est affichée dans les logs (ex. `http://localhost:32807`, login `quarkus@quarkus.io` / `quarkuslangfuse`). Pose une question → la trace `expert-router.route` et ses 2 sous-agents y apparaissent.

## Conso de tokens (métriques)

quarkus-langchain4j émet automatiquement un compteur de tokens (rien à coder), exporté vers Prometheus/Mimir (LGTM) → visible en **mode par défaut**.

Métrique : `gen_ai_client_token_usage_total` — labels `gen_ai_token_type` (`input`/`output`), `gen_ai_request_model`, `ai_service_class_name` (par sous-agent). Aussi `gen_ai_client_estimated_cost_total` (coût).

PromQL (Grafana → Explore → source Prometheus) :
```promql
sum by (gen_ai_token_type) (increase(gen_ai_client_token_usage_total[1d]))   # tokens/jour
sum(increase(gen_ai_client_estimated_cost_total[1d]))                        # coût/jour
```

Le coût est à **0** par défaut : [`GeminiCostEstimator`](src/main/java/org/srozange/langchain4j/GeminiCostEstimator.java) fournit un tarif par token (indicatif, à ajuster). Dashboard prêt à l'emploi : voir [Dashboard tokens](#dashboard-tokens).
