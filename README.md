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


### Contrôle hiérarchique, boucles de correction et assemblage

**Règle de fonctionnement** : chaque agent exécutant soumet une *brique* à son agent manager. Le manager vérifie les preuves et les critères d'acceptation avant toute intégration. Une brique non validée ne doit jamais être présentée comme terminée.

**Contrat de brique** : identifiant, responsable, dépendances, entrées, livrables, références exactes aux fichiers/fonctions/adresses analysés, hypothèses, niveau de confiance, tests reproductibles, résultats observés et limites. Pour le code produit : diff, commande de compilation, tests et résultat.

**Cycle** :
1. Le manager découpe l'objectif en tâches indépendantes et attribue chacune à un spécialiste.
2. L'exécutant livre sa brique avec preuves et procédure de vérification.
3. Le manager contrôle la cohérence, les cas limites et les interfaces avec les autres briques ; un agent vérificateur distinct exécute les tests lorsque possible.
4. Si refus : le manager renvoie un rapport d'anomalie précis (résultat attendu, résultat observé, référence, priorité, critère de résolution). L'exécutant corrige et soumet une nouvelle version ; chaque itération est historisée.
5. Après validation : la brique est figée avec sa version et son statut ; le manager l'assemble aux autres briques validées.
6. Les tests d'intégration et de non-régression contrôlent l'ensemble. Un échec réouvre les briques concernées et déclenche une nouvelle boucle.
7. Après plusieurs échecs sur la même anomalie, escalader vers un autre spécialiste ou l'orchestrateur ; ne pas tourner indéfiniment et ne jamais inventer une réussite.

**États obligatoires** : `à analyser`, `en cours`, `à vérifier`, `rejeté`, `corrigé`, `validé`, `intégré`, `bloqué`. Un résultat seulement plausible reste `à vérifier`, pas `validé`.

**Règle de séparation des rôles** : l'exécutant ne s'auto-valide pas ; le manager est responsable de l'acceptation et de l'assemblage ; l'orchestrateur arbitre les conflits entre managers. Les preuves de décompilation, les tests unitaires et les essais sur émulateur constituent des contrôles différents, qui doivent être suivis séparément.

**Traçabilité** : conserver par application les contrats, rapports de refus/correction, versions validées, dépendances et résultats de tests dans `applications/<slug>/analyse/` ; publier un état synthétique dans `suivi/`. Le fonctionnement effectif en agents autonomes exige un moteur d'orchestration à déployer ; cette description seule ne crée pas ces agents.


## Règle générale impérative : analyse validée avant création (transférable à ChatGPT Work)

Ce protocole s'applique **à toute application Android** et à tout nouveau sous-projet du monorepo. Les expériences ponctuelles ne définissent ni son nom ni sa version. Ne jamais présupposer le nom `V2` ou créer un sous-projet avant l'autorisation formelle.

### Phases et portes de décision

**Phase A — acquisition et inventaire** : recevoir l'APK/XAPK/APKS ; enregistrer empreintes, splits, ABI, SDK, composants, bibliothèques, ressources et provenance. Aucune génération de code applicatif.

**Phase B — rétro-analyse exhaustive** : les spécialistes DEX/Smali, code natif/assembleur, Flutter/AOT, ressources, persistance, réseau et événements reconstruisent les modules, points d'entrée, fonctions, procédures, callbacks, données, états et appels. Pour chaque unité, consigner identifiant stable, preuve (fichier/symbole/adresse), appelants, appelés, entrées, sorties, effets secondaires, conditions, erreurs, dépendances et niveau de confiance. Reconstituer les graphes d'appels, de flux de données, d'états et de navigation, notamment l'initialisation et les boucles événementielles. Ne pas substituer les descriptions commerciales à l'analyse du code.

**Phase C — vérification contradictoire** : chaque agent livre des preuves ; un manager indépendant vérifie couverture, cohérence, cas limites et interfaces. En cas d'insuffisance, il refuse la brique avec anomalie reproductible et renvoie à l'exécutant. Les boucles continuent jusqu'à validation ou blocage explicitement documenté ; escalade en cas de répétition. Une chaîne isolée ou un nom de fonction ne vaut pas preuve de son fonctionnement.

**Phase D — spécification intégrale** : produire l'arborescence de toutes les fonctions, les matrices des écrans/actions, des données/tableaux, des entrées/sorties, les graphes, les preuves et la liste des inconnues. Dresser aussi l'inventaire des exigences techniques **découvertes pendant l'analyse** : architecture CPU, bibliothèques, services, SDK, permissions, capacités GitHub Actions, émulateur ou matériel requis. Ne pas figer le futur environnement de construction avant cet inventaire.

**PORTE BLOQUANTE — autorisation de création** : l'orchestrateur n'autorise la phase E que si le manager a validé l'intégralité de l'analyse et ses critères de couverture, sans zone fonctionnelle non résolue. Si une partie reste incertaine ou inaccessible, déclarer `ANALYSE NON VALIDÉE / BLOQUÉE` et **ne pas créer de code, de module Android ni de workflow de build pour cette application**. Ne pas inventer un taux de couverture de 100 %. Toute impossibilité technique doit être remontée à l'utilisateur.

**Phase E — création du sous-projet** : seulement après autorisation, créer un module isolé dans le dépôt GitHub commun, choisir les outils/SDK/ABI/dépendances en fonction des résultats d'analyse, implémenter indépendamment et écrire les tests correspondant aux comportements prouvés.

**Phase F — tests et boucles de correction** : compiler, exécuter les tests adaptés, comparer les résultats aux spécifications validées, faire rejeter/corriger les briques par les managers, puis assembler et exécuter les tests d'intégration. Conserver la traçabilité des anomalies et validations.

**Phase G — livraison** : fournir artefacts, résultats de tests, écarts, preuves et état final, sans déclarer une équivalence non vérifiée.

### Organisation transférable à ChatGPT Work

Work est le **pilote** du processus, non un substitut à l'analyse technique : orchestrateur général, managers de spécialité, agents exécutants et vérificateurs. GitHub est le monorepo et l'outil de CI ; Drive sert de boîte d'entrée et de conservation des originaux/rapports. Le fonctionnement parallèle, les agents réellement disponibles et les outils de décompilation doivent être vérifiés avant de prétendre qu'ils sont déployés.

**Consigne de démarrage universelle** : « Lis le protocole générique, analyse intégralement le binaire Android déposé, fais vérifier toutes les briques et livre le dossier de rétro-analyse. Ne crée aucun sous-projet ni code tant que la porte de validation de l'analyse n'est pas franchie. »


## Organisation Drive : boîte d'entrée et mémoire centrale par application

Le dossier Google Drive `développement_application_android` est le point d'entrée. **La racine sert uniquement de boîte de dépôt** pour les nouveaux APK, XAPK ou APKS ; les fichiers déjà classés dans des sous-dossiers ne sont pas de nouvelles demandes.

**Dès le démarrage de l'analyse**, avant toute décompilation :
1. Lister la racine et identifier chaque archive Android non prise en charge. Si plusieurs archives correspondent à une seule application ou si l'identité est ambiguë, résoudre ce point avant de déplacer.
2. Déterminer un nom de dossier stable et lisible à partir du nom/package de l'application ; rechercher un dossier existant pour éviter les doublons et conserver les analyses antérieures.
3. Créer au besoin le sous-dossier `<application>/`, puis **déplacer** l'archive originale depuis la racine dans ce dossier, sans la renommer ni la modifier ; enregistrer SHA-256, taille, version, date et ID Drive.
4. Faire de ce sous-dossier **l'unique mémoire centrale du dossier d'analyse de cette application**. Tout constat, extraction, inventaire, graphe, contrat d'agent, résultat de contre-vérification, anomalie, décision et état d'avancement doit y être consigné au fur et à mesure.
5. À chaque reprise, **lire d'abord la mémoire centrale et le journal d'avancement**, vérifier les preuves existantes et reprendre au dernier point validé ; ne pas refaire aveuglément le travail déjà accepté.
6. Ne créer un sous-projet de code dans le monorepo GitHub qu'après validation intégrale de l'analyse selon la porte bloquante définie plus haut. Les informations propres à une application restent dans son dossier Drive ; les règles communes restent dans ce README générique.

### Arborescence cible par application

```text
développement_application_android/
  README.md                        # protocole générique
  <nouvel-original>.xapk           # boîte d'entrée, temporaire
  <application>/
    original/
      <archive-originale>.xapk     # conserver les octets d'origine
    README.md                      # point d'entrée de la mémoire centrale
    etat.json                      # phase, tâches, blocages, dernière validation
    journal.md                     # progression chronologique, reprise
    inventaire/                    # hashes, manifestes, ABI, splits, ressources
    decompilation/                 # sorties d'outils, provenance, versions
    architecture/                  # modules, fonctions, appels, entrées/sorties
    donnees/                       # schémas, tableaux, flux de données
    interfaces/                    # widgets, actions, états, navigation
    preuves/                       # adresses, symboles, références et confiance
    agents/                        # contrats, rapports, refus, corrections
    validations/                   # couverture, contradictions, porte bloquante
    specifications/                # cahier des charges issu du code
```

Cette arborescence est **une cible documentaire** : ne pas déclarer créés les fichiers ou sous-dossiers qui n'existent pas encore. Le dossier `original/` est recommandé ; si l'archive a été déplacée directement à la racine du sous-dossier, elle reste valide et son emplacement doit être enregistré dans le README.

**Politique de reprise** : chaque étape produit un état durable, horodaté, avec liens de preuves, responsable, version et prochaine action. Une interruption ne doit jamais faire perdre le dernier état validé. Les artefacts volumineux peuvent être conservés dans un stockage associé, mais doivent être indexés depuis la mémoire centrale Drive.
