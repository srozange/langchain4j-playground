---
name: favorite-coffee
description: "Expert en interaction avec l'API REST Favorite Coffee pour explorer, filtrer et noter des cafés. Utiliser ce skill dès que l'utilisateur mentionne des cafés, des grains, des notes de café, des recommandations ou veut explorer une collection — même sans mentionner explicitement une API. Exemples de déclencheurs : c'est quoi les cafés fruités, note le Yirgacheffe 3 étoiles, qu'est-ce que tu me recommandes comme café, montre-moi ce que j'ai déjà goûté, je veux découvrir de nouveaux cafés."
---

# Favorite Coffee

Exécuter soi-même les appels curl. Ne jamais demander à l'utilisateur de lancer une commande. Répondre en français, formater en Markdown.

## Exemple complet : noter un café

Voici le workflow exact à suivre pour noter un café :

```bash
# Étape 1 — récupérer la liste et les UUID réels
curl -s 'http://localhost:8081/beans'
# Réponse : [{"uuid":"a3f1c2d4-58e7-4b2a-9f01-2c3d4e5f6a7b","name":"Finca La Esperanza FTO RFA",...}, ...]

# Étape 2 — noter avec l'UUID extrait de la réponse (PUT, JSON, /beans)
curl -s -X PUT 'http://localhost:8081/beans/a3f1c2d4-58e7-4b2a-9f01-2c3d4e5f6a7b/ratings' \
  -H 'Content-Type: application/json' \
  -d '{"rating": 2}'
```

Points clés de cet exemple :
- Le path est `/beans`, pas `/coffees`
- La méthode est `PUT`, pas `POST`
- L'UUID vient du champ `uuid` de la réponse JSON — ce n'est ni un nombre ni le nom du café
- Le body est du JSON `{"rating": N}` avec `Content-Type: application/json`

## Toutes les APIs disponibles

URL de base : `http://localhost:8081`

| Méthode | Endpoint                  | Description                              |
|---------|---------------------------|------------------------------------------|
| GET     | `/beans?flavor={flavor}`  | Lister tous les cafés (filtre optionnel) |
| GET     | `/beans/rated`            | Cafés déjà notés par l'utilisateur       |
| GET     | `/beans/recommended`      | Cafés recommandés selon les goûts        |
| GET     | `/beans/untested`         | Cafés non encore testés                  |
| PUT     | `/beans/{uuid}/ratings`   | Noter un café (rating 1, 2 ou 3)         |

Arômes possibles pour le filtre : `Spicy` · `Chocolaty` · `Fruity` · `Flowery` · `Buttery` · `Earthy` · `Winey` · `Nutty`

## Notes

| Valeur | Signification                    |
|--------|----------------------------------|
| `1`    | Pas bon / pas à mon goût         |
| `2`    | Moyen / correct                  |
| `3`    | Très bon / exactement à mon goût |
