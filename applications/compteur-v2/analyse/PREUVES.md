# Preuves extraites du XAPK original

## Provenance
Archive `com.kdevelop.simplecounter-36_arm64-v8a.xapk`, SHA-256 `53574ba3229e149647326a3a6ee88fd22048f339035c323129c40b910bb49f17`.
- `base.apk` : 5 968 821 octets ; contient `classes.dex`, `assets/flutter_assets/`, trois sons `assets/sound/button_1.mp3` à `button_3.mp3`.
- `split_config.arm64_v8a.apk` : 17 543 749 octets ; contient `libapp.so` (Dart AOT), `libflutter.so` et `libdatastore_shared_counter.so`.
- Splits français et xxhdpi également présents.

## Symboles/chaînes identifiés dans libapp.so
Les éléments suivants ont été observés par extraction de chaînes directement dans `libapp.so` :
- Données : `CounterDB`, `counterList`, `counterId`, `counterName`, `currentCounter`, `package:simplecounter/Models/Counter.dart`, `Models/SqliteHandler.dart`.
- Écrans : `ViewControllers/HomePage.dart`, `EditCounterList.dart`, `ArchiveList.dart`, `Setting.dart`.
- Opérations compteurs : `Reset all counters`, `Reset this counter`, `Delete this counter`, `Edit Counter`, `_resetAllCounters`, `_resetCounter`, `updateCounter`.
- Archives : `insertArchive`, `_saveArchive`, `_getArchives`, `deleteArchive`, `deleteAllArchive`, `Save to archive and reset`, `Delete all archives`.
- Réglages : `_setSettingData`, `_updateUserSettingsData`, `ScrollIncrementType`, `_calculateScrollIncrement`, `MethodChannelVibration`, `SystemSound.play`, `setSpeechRate`, `SpeechInputToggle`.
- Autres : `SharePlus`, `_changeSoundStr`, `_reviewSetting`, `ArchiveTutorial`, `SpeechCorrectionList`.

## Niveaux de preuve
**P1** : nom/fichier/chaîne attesté dans l'archive ; **P2** : flux de contrôle et paramètres vérifiés par désassemblage ; **P3** : action et résultat observés sur l'application originale ; **P4** : comportement reproduit et testé dans V2.

Actuellement, les fonctions ci-dessus ne sont établies qu'au niveau **P1**. L'existence d'une chaîne traduite ou d'un nom de méthode ne démontre pas l'accessibilité de la fonction, son modèle économique ni son résultat précis.

## Limites
Aucun inventaire exhaustif des boutons, widgets, formulaires et tableaux n'est encore établi : la majeure partie de l'UI Flutter est compilée dans le binaire AOT. L'analyse DEX, le désassemblage ARM64 et l'exécution ARM64 restent nécessaires. Aucun état premium n'est établi.
