# Calculatrice Test — premier projet Android

Application Android **originale**, développée pour valider la chaîne GitHub → Gradle → APK → émulateur → tests.

## Périmètre initial

- Addition, soustraction, multiplication, division
- Décimales, remise à zéro et division par zéro
- Interface à boutons en français
- Tests instrumentés Android : addition, erreur de division et remise à zéro

## Construction

Prérequis : Java 17, Android SDK 35, Gradle 8.9.

Depuis la racine du dépôt :

```bash
gradle :applications:calculatrice-test:assembleDebug
```

APK attendu : `applications/calculatrice-test/build/outputs/apk/debug/calculatrice-test-debug.apk`.

## Tests

```bash
gradle :applications:calculatrice-test:connectedDebugAndroidTest
```

Le workflow GitHub Actions compile et exécute ces tests dans un émulateur Android. **Leur réussite doit être constatée dans GitHub Actions ; elle n'est pas présumée.**

## Limites

Projet de démonstration, sans publication sur Google Play. L'application ne copie aucun code ou élément graphique d'une autre calculatrice.
