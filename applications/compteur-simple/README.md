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


## Comparaison fonctionnelle — 20 octobre 2026

**Source** : fiche officielle [Simple Tally Counter sur Google Play](https://play.google.com/store/apps/details?id=com.kdevelop.simplecounter) et code du prototype. Ce tableau ne prétend pas constituer une inspection interactive de l'APK.

| Fonction | Référence publiée | Prototype | Statut |
|---|---|---|---|
| Incrémenter | Toucher n'importe où sur l'écran | Toucher le nombre ou le bouton Ajouter | Partiel |
| Décrémenter | Touches volume / commandes | Bouton -1 et volume bas | Implémenté, non revérifié |
| Pas configurable | Pas personnalisable | Cycle 1/2/5/10 | Partiel |
| Persistance | Historique et sauvegardes | Valeur et pas en préférences locales | Partiel |
| Plusieurs compteurs | Oui | Non | Manquant |
| Son et vibration optionnels | Oui | Non | Manquant |
| Animation de toucher | Oui | Non | Manquant |
| Lecture vocale et vitesse | Oui | Non | Manquant |
| Historique / archives | Oui | Non | Manquant |
| Écran maintenu allumé | Oui | Non | Manquant |
| Partage du résultat | Oui | Non | Manquant |
| Objectifs par compteur | Souhait utilisateur du 27/06/2026 | Non | Amélioration à envisager |
| Désactiver l'effet visuel | Souhait utilisateur du 27/06/2026 | Non applicable | Amélioration à envisager |

L'application de référence est distribuée en XAPK ARM64 ; les tests du prototype utilisent un émulateur x86_64. Il ne faut donc pas prétendre avoir comparé visuellement les deux applications sur le même émulateur.

### Itération en cours

Ajoutés au code : toucher le compteur pour incrémenter, réglage du pas par cycles, prise en charge des touches de volume. Un test instrumenté du toucher a été ajouté. **La réussite CI après ces changements reste à vérifier** ; le succès du workflow précédent ne valide que le commit antérieur.

### Priorités suivantes

1. Exécuter les tests après modification et corriger les échecs.
2. Ajouter plusieurs compteurs nommés avec sauvegarde locale.
3. Ajouter historique et partage.
4. Ajouter réglages de vibration/son, synthèse vocale, maintien de l'écran allumé.
5. Comparer sur un appareil ARM64 compatible les écrans de l'application de référence et de la version originale.
