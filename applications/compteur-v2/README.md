# Compteur V2 — phase d'analyse exclusive

**STATUT : ANALYSE INCOMPLÈTE — DÉVELOPPEMENT INTERDIT.**

L'utilisateur impose une barrière de validation : ne créer aucune refonte avant la cartographie exhaustive du fonctionnement de l'application originale à partir du code de l'APK/XAPK. Les descriptions Play Store ne sont pas des preuves primaires.

Les fichiers de prototype V2 précédemment créés sont **obsolètes, non validés et ne doivent être ni compilés ni développés davantage**. Leur présence historique dans le dépôt ne constitue pas une autorisation de développement.

## Livrables d'analyse obligatoires
1. Inventaire complet des APK/splits, DEX, ressources, ELF, bibliothèques et points d'entrée.
2. Arborescence des modules, classes, fonctions, procédures, callbacks et événements.
3. Pour chaque routine : adresse/symbole et preuve, entrées et types, sorties, effets secondaires, dépendances, appelants, appelés, conditions, erreurs et niveau de confiance.
4. Graphes d'appels et de flux de données reliant initialisation, boucle d'événements Flutter, gestion d'état, interface, persistance et bibliothèques natives.
5. Catalogue des écrans, widgets, actions, paramètres et tableaux, reliés aux routines et aux données du code.
6. Rapports de contre-vérification entre analystes DEX, Flutter AOT, assembleur ARM64 et intégrateur fonctionnel.
7. Liste explicite des zones indéchiffrées, de l'incertitude et des chemins non explorés.
8. Validation finale du manager avec couverture justifiée et vérification des contradictions avant tout feu vert au développement.

**Règle :** une chaîne trouvée dans `libapp.so` est un indice, pas une fonction démontrée. L'exhaustivité ne peut pas être affirmée sans preuves de couverture suffisantes. En cas de limite de désassemblage, marquer `BLOQUÉ` et ne pas déclencher le développement.

Documents initiaux : [preuves](analyse/PREUVES.md), [cahier des charges provisoire](analyse/CAHIER_DES_CHARGES.md).
