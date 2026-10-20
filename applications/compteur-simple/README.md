# Compteur simple — projet pilote

**Objectif :** créer une application Android indépendante inspirée des fonctions générales de comptage de Simple Tally Counter, sans recopier son code, son design ni ses ressources.

## Source de référence

- Google Drive : `développement_application_android/com.kdevelop.simplecounter-36_arm64-v8a.xapk`
- Paquet : `com.kdevelop.simplecounter`
- Version déclarée dans l'archive : `1.1.22` (code 36)
- Format : XAPK, avec `base.apk`, `split_config.arm64_v8a.apk`, `split_config.fr.apk` et `split_config.xxhdpi.apk`
- SHA-256 de l'archive : `53574ba3229e149647326a3a6ee88fd22048f339035c323129c40b910bb49f17`
- Technologie détectée : Flutter (bibliothèques natives et assets).
- Limite : aucune observation interactive sur émulateur de l'application de référence n'a encore été réalisée.

## MVP original

- Afficher un compteur entier, initialement zéro
- Boutons +1, -1 et remise à zéro
- Persistance locale du compteur entre les ouvertures
- Aucun compte, réseau, publicité ni autorisation sensible

## Pipeline

Le projet Android Java et son workflow sont présents. GitHub Actions doit compiler un APK debug et exécuter des tests instrumentés sur un émulateur. **Ne pas considérer la chaîne validée sans logs de réussite.**

## Prochaines étapes

1. Contrôler les résultats GitHub Actions, corriger toute erreur de compilation ou d'émulation.
2. Installer l'archive XAPK de référence sur un appareil ARM64 compatible pour observer les écrans et comportements (pas encore fait).
3. Compléter le cahier des charges à partir d'observations vérifiées et d'avis utilisateurs sourcés.
4. Comparer les comportements, puis améliorer le prototype.
