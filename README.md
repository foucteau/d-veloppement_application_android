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

**Premier projet créé :** [Calculatrice Test](applications/calculatrice-test/README.md). Son code Android, ses tests instrumentés et un workflow GitHub Actions de compilation et d'émulation sont présents. La réussite effective du workflow reste à vérifier dans GitHub Actions.

## Projet pilote : compteur simple

- [Compteur simple — application indépendante et tests](applications/compteur-simple/README.md)
- [Workflow GitHub Actions du compteur](.github/workflows/compteur-simple.yml)
- APK de référence XAPK déposé sur Google Drive ; rapport initial dans le sous-dossier `compteur-simple/`.

## Premier essai : calculatrice Android

- [Voir le sous-projet Calculatrice Test](applications/calculatrice-test/README.md)
- [Voir le workflow de compilation et tests Android](.github/workflows/calculatrice-test.yml)
- [Voir les exécutions GitHub Actions](../../actions)

## Ajouter une application

Créer un sous-dossier dans `applications/`, en partant de [modeles/README.md](modeles/README.md), puis documenter son objectif, ses fonctions, ses dépendances et ses tests.

---
*Ce dépôt sert de point d'entrée commun à tous nos futurs projets Android.*


## Procédure générique — nouvelle application Android

Déclencheur : « Lis le README de mon dossier Google Drive développement_application_android et crée une nouvelle version indépendante de l'application déposée à la racine. »

1. **Découverte** : consulter Drive et son README, lister APK/XAPK/APKS, identifier le nouveau fichier ; si plusieurs candidats sont ambigus, demander lequel. Ne jamais altérer l'original.
2. **Contrôle** : copier pour analyse, calculer SHA-256, inspecter ZIP/manifest/version/minSdk/permissions/ABI/splits. Ne pas installer un split ARM64 sur un émulateur x86_64 incompatible.
3. **Référence** : documenter séparément ce qui est prouvé par l'archive, la fiche Play Store, les avis datés et une observation interactive réelle. Ne jamais inventer captures ou tests.
4. **Spécifications** : produire une matrice de fonctionnalités, écrans, parcours, stockage et écarts ; prioriser MVP, fonctions avancées et améliorations issues des avis.
5. **Développement indépendant** : nouveau module applications/<slug>, package unique, code et graphismes originaux, README, tests ; ajouter le module dans settings.gradle. Ne pas reproduire les ressources protégées.
6. **GitHub Actions** : compiler APK debug avec JDK 17/Gradle/Android SDK, lancer émulateur compatible et tests instrumentés, archiver APK et rapports. Éviter le paquet SDK obsolète `tools` ; `android-actions/setup-android@v4` avec `packages: 'platform-tools'` a résolu l'erreur rencontrée.
7. **Itération** : retrouver le run ID, inspecter jobs/logs/artifacts, corriger, relancer, revérifier. Un point vert valide le workflow exécuté, pas une copie fonctionnelle fidèle.
8. **Livraison** : communiquer URL du run, artefact, tests exécutés, limites et fonctions manquantes ; consigner le statut dans les README et un rapport Drive. Ne déclarer « reproduction validée » qu'après comparaison fonctionnelle de référence.
9. **Autonomie** : réaliser tout ce qui est techniquement accessible sans questions superflues ; demander seulement les éléments bloquants ou nécessitant une intervention physique.

**Précédent vérifié :** [compteur-simple, exécution 39410394914](https://github.com/foucteau/d-veloppement_application_android/actions/runs/39410394914) : APK compilé et 2 tests instrumentés réussis sur Android 10 x86_64. Cette validation précède les améliorations ultérieures, qui doivent être retestées.


## Architecture multi-agents d'analyse et de reconstruction

**Principe obligatoire :** décomposer le travail par spécialité ; exécuter les analyses indépendantes en parallèle lorsque l'environnement le permet ; centraliser les résultats avant d'écrire le cahier des charges. Cette section décrit une organisation cible, et non des agents déjà déployés.

### Rôles

- **Orchestrateur** : identifie les composants, affecte les tâches, suit les dépendances, détecte les contradictions, valide les preuves et décide des itérations.
- **Analyseur DEX / code lisible** : examine manifeste, ressources, Java/Kotlin décompilés, Smali, événements, stockage, appels réseau et contrôles d'accès. Utilise JADX et apktool si disponibles.
- **Analyseur natif / assembleur** : examine ELF, ARM32/ARM64, symboles, chaînes, références croisées, flux de contrôle et interfaces JNI ; utilise Ghidra, radare2 ou équivalent si disponibles. Ne confond pas désassemblage et code source.
- **Analyseur Flutter / autres frameworks** : inspecte l'architecture spécifique, les bibliothèques, les canaux de plateforme et le code AOT ; signale les parties impossibles à déduire avec certitude.
- **Analyseur interface et parcours** : cartographie les écrans, contrôles cliquables/sélectionnables, états, gestes, navigation et permissions à partir des ressources et d'observations réellement effectuées.
- **Analyste fonctionnel intégrateur** : fusionne les preuves des agents, décrit les procédures et scénarios de bout en bout, distingue comportements prouvés, probables et inconnus ; construit une matrice de couverture.
- **Développeur indépendant** : réalise une application nouvelle à partir de spécifications fonctionnelles, sans reprendre code ni ressources protégées.
- **Agent qualité et CI** : compile, exécute tests unitaires et instrumentés, compare les parcours observables, publie rapports et anomalies.

### Protocole de collaboration

1. L'orchestrateur produit un inventaire des APK, splits, ABI, langages et frameworks, puis un plan de travail.
2. Les analyseurs statiques spécialisés travaillent en parallèle sur des ensembles de fichiers distincts. L'analyse dynamique complète les constats si un appareil ou émulateur compatible est accessible.
3. Chaque constat est rendu sous forme structurée : `id`, `agent`, `fichier`, `fonction/adresse`, `action`, `préconditions`, `effets`, `preuves`, `confiance`, `inconnues`. Ne jamais inventer de preuve.
4. L'intégrateur rapproche les appels Java/JNI/natifs/Flutter et les interactions UI, signale les conflits, et demande une contre-analyse ciblée.
5. Le développement ne commence sur une fonction qu'après définition de son comportement attendu et de ses critères de test ; les fonctions inconnues restent explicitement marquées.
6. L'agent qualité teste, signale les écarts et renvoie les anomalies à l'agent compétent jusqu'à validation.
7. Publier les rapports dans `applications/<slug>/analyse/` et un état synthétique dans `suivi/`. Les rôles peuvent être exécutés par des processus indépendants, des outils ou des passes successives ; ne pas prétendre qu'ils tournent en parallèle si ce n'est pas le cas.

**Priorité des sources :** l'APK/XAPK réel et les observations de son exécution priment sur les descriptions commerciales. Les pages de présentation et avis sont seulement des sources secondaires de contexte. L'analyse ne doit pas servir à contourner des licences, paiements ou protections d'accès.
