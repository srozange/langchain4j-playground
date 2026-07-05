#!/usr/bin/env bash
set -euo pipefail

cd "$(dirname "$0")"

echo "Choisir le profil d'observabilité :"
echo "  1) lgtm     - LGTM local : Grafana / Tempo / Prometheus "
echo "  2) jaeger   - traces OTLP vers un Jaeger local (docker compose)"
echo "  3) langfuse - traces vers un Langfuse local via Dev Service"
echo

read -rp "Votre choix [1-3] (défaut 1) : " choice
choice="${choice:-1}"

case "$choice" in
    1)
        exec mvn quarkus:dev -Dquarkus.profile=lgtm
        ;;
    2)
        echo "==> Démarrage de Jaeger (docker compose up -d)"
        docker compose up -d
        echo "==> Lancement en mode jaeger  (UI : http://localhost:16686)"
        exec mvn quarkus:dev -Dquarkus.profile=jaeger
        ;;
    3)
        exec mvn quarkus:dev -Dquarkus.profile=langfuse
        ;;
    *)
        echo "Choix invalide : '$choice'. Attendu 1, 2 ou 3." >&2
        exit 1
        ;;
esac
