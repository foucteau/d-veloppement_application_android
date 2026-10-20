# Compteur V2 — refonte fondée sur le XAPK original

**Nouveau projet indépendant**, sans réutilisation du code du compteur V1. Référence : `com.kdevelop.simplecounter-36_arm64-v8a.xapk`, version 1.1.22 (36), SHA-256 `53574ba3229e149647326a3a6ee88fd22048f339035c323129c40b910bb49f17`.

## Sources et méthode
1. Inspection de l'archive XAPK et des APK scindés.
2. Extraction de `lib/arm64-v8a/libapp.so` (Flutter Dart AOT), `libflutter.so`, `libdatastore_shared_counter.so`, `classes.dex` et ressources.
3. Extraction de chaînes et de noms de classes/méthodes du binaire AOT ; ces symboles prouvent une présence dans l'image compilée, **pas le comportement exact**.
4. Analyse ultérieure requise : DEX/Smali, désassemblage ARM64, graphes d'appels, lecture structurée des ressources et exécution de la référence sur ARM64.
5. Aucune description commerciale ne constitue une preuve fonctionnelle primaire.

**État** : analyse statique initiale vérifiée ; spécification détaillée ci-dessous ; implémentation V2 à construire et tester. Les fonctionnalités marquées « à confirmer » ne sont pas garanties.

Voir [cahier des charges](analyse/CAHIER_DES_CHARGES.md) et [inventaire des preuves](analyse/PREUVES.md).
