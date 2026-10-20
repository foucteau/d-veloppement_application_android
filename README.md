# Laboratoire de développement d'applications Android

Bienvenue dans notre espace de développement Android.

Ce dépôt GitHub regroupe **plusieurs applications Android indépendantes**, organisées en sous-dossiers. Nous y concevons, développons, compilons et testons des applications originales, y compris des alternatives fonctionnelles à des applications existantes, avec leurs propres codes et identités graphiques.

## Organisation

```text
applications/           Une application Android par sous-dossier
modeles/                Modèles réutilisables pour démarrer un projet
outils/                 Scripts communs d'analyse, de compilation et de test
documentation/          Méthodologie, conventions et documents partagés
.github/workflows/      Automatisations GitHub Actions
```

Chaque application aura son propre dossier, par exemple `applications/gestionnaire-fichiers/`, contenant son code source, sa documentation et ses tests. Les projets restent séparés, même s'ils partagent le même dépôt.

## Méthode de travail

1. **Étudier les besoins** : fonctions attendues, parcours utilisateur et limites techniques.
2. **Analyser les retours** : avis publics accessibles, problèmes récurrents et demandes d'amélioration.
3. **Rédiger les spécifications** : cahier des charges et priorités documentées.
4. **Développer indépendamment** : code et ressources originaux, sans recopier le code propriétaire.
5. **Tester** : tests unitaires, compilation et, lorsque configuré, émulation Android dans GitHub Actions.
6. **Améliorer** : corriger les défauts constatés et maintenir un historique des décisions.

## Règles communes

- Conserver une trace des sources et distinguer les fonctionnalités observées des hypothèses.
- Respecter les licences, les droits d'auteur, les conditions d'accès aux services et les données personnelles.
- Ne jamais enregistrer de clés API, mots de passe ou autres secrets dans le dépôt.
- Utiliser des branches et des demandes de fusion pour les évolutions importantes.
- Garder les applications indépendantes et éviter les modifications inutiles des autres projets.
- Ne pas présenter une compilation ou un test comme réussi avant son exécution effective.

## État du laboratoire

**Structure initiale créée.** Aucun projet Android ni émulateur n'est encore installé ou validé. Les workflows de compilation et de test seront ajoutés lors de la création de la première application.

## Ajouter une application

Créer un sous-dossier dans `applications/`, en partant de [modeles/README.md](modeles/README.md), puis documenter son objectif, ses fonctions, ses dépendances et ses tests.

---
*Ce dépôt sert de point d'entrée commun à tous nos futurs projets Android.*
