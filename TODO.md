# TODO — strava-run

Feuille de route d'apprentissage Java / Spring Boot. Chaque étape = un (ou plusieurs) commit(s).

Légende : `[x]` fait · `[ ]` à faire

---

## Étape 1 — Hygiène : git & secrets

- [x] Externaliser les identifiants de la base dans un fichier `.env`
- [x] Créer `.env.example` (versionné, valeurs bidon) pour documenter les variables attendues
- [x] Ajouter `.env` au `.gitignore`
- [x] Utiliser `${...}` dans `docker-compose.yaml` et `application.properties`
- [x] Charger le `.env` dans Spring via `spring.config.import=optional:file:.env[.properties]`
- [x] Corriger le `\` parasite en fin de ligne dans `application.properties`
- [x] Remettre `spring.application.name=strava-run` (ne pas confondre nom d'appli et nom de base)
- [x] `git init` + `git add .`
- [x] Vérifier que l'appli démarre et que `GET /api/workouts` répond
- [x] Premier commit
---

## Étape 2 — DTO de requête & mapper

- [x] Créer des DTO d'entrée (`record`) au lieu de recevoir les entités JPA dans les contrôleurs (`@RequestBody Workout`, `@RequestBody EffortType`)
- [x] Passer `WorkoutResponseDTO` en `record`
- [x] Sortir la conversion entité ↔ DTO du contrôleur vers une classe `Mapper` dédiée (`WorkoutMapper`)
- [x] Ne plus renvoyer d'entité JPA en réponse (`create` renvoie désormais un `WorkoutResponseDTO`)
- [x] Ajouter les validations manquantes (`@NotBlank` sur `title`, `@NotNull` sur `date` / `effortTypeId`…)
- [x] Supprimer l'import inutilisé de `WorkoutResponseDTO` dans `WorkoutService`

## Étape 3 — Gestion d'erreurs & codes HTTP

- [ ] Remplacer les réponses maison par `ProblemDetail` (RFC 9457)
- [ ] Supprimer le handler `HttpClientErrorException.NotFound` (concerne les appels HTTP sortants, jamais déclenché ici)
- [ ] Renvoyer toutes les erreurs de validation, pas seulement la première (`getFieldErrors().get(0)`)
- [ ] `create` : gérer `effortType` absent (NPE) ou inconnu (`orElseThrow()` → 500) avec un 400 / 404 explicite
- [ ] `POST` → `201 Created` (+ en-tête `Location`)
- [ ] `DELETE` → `204 No Content`, et 404 si l'id n'existe pas

## Étape 4 — Mise à jour d'un workout

- [x] Choisir entre `PUT` (remplacement complet) et `PATCH` (mise à jour partielle) → les deux, `PUT` d'abord
- [x] Implémenter `WorkoutService.update` (via `PUT` : remplacement complet)
- [x] Brancher l'endpoint `PUT /api/workouts/{id}` dans `WorkoutController`
- [x] Découvrir `@Transactional` et le *dirty checking* de JPA (testé : UPDATE sans `save()`)
- [x] Implémenter `PATCH /api/workouts/{id}` (mise à jour partielle, approche « non-null = modifie » ; testé)

## Étape 5 — Refonte d'`EffortType`

- [ ] Créer un `EffortTypeService` (le contrôleur appelle actuellement le repository directement)
- [ ] `DELETE /api/efforts/{id}` au lieu d'un `DELETE` avec body
- [ ] Uniformiser l'injection (`@RequiredArgsConstructor` comme ailleurs)
- [ ] Réfléchir : table de référence ou `enum` ?

## Étape 6 — Modèle de données

- [ ] Rendre `Workout` `abstract`
- [ ] Harmoniser les unités de durée (`durationInMinutes`, `movingTime`, `totalTime`) → envisager `java.time.Duration`
- [ ] `date` : passer de `LocalDate` à `LocalDateTime` / `Instant` (heure de début d'activité)
- [ ] Revoir le champ `discipline` (`@JsonIgnore` contradictoire avec `visible = true`)
- [ ] Corriger le problème N+1 sur `effortType` (`LAZY`) lors du `findAll` (`JOIN FETCH` / `@EntityGraph`)
- [ ] Gérer le cas `effortType == null` dans le mapping (NPE actuel)

## Étape 7 — Tests

- [ ] Tests unitaires du service avec JUnit 5 + Mockito
- [ ] Tests de contrôleur avec `@WebMvcTest`
- [ ] Tests d'intégration avec Testcontainers (Postgres) — `contextLoads()` dépend aujourd'hui d'une vraie base

## Étape 8 — Pour aller plus loin

- [ ] Pagination & tri (`Pageable`)
- [ ] Filtres (par discipline, par période)
- [ ] Migrations de schéma avec Flyway (remplacer `ddl-auto=update`)
- [ ] Endpoints de statistiques (distance totale, allure moyenne…)
- [ ] Nettoyer le `pom.xml` (BOM Spring AI inutilisé pour l'instant, balises vides)
- [ ] Spring AI / pgvector (piste future)
