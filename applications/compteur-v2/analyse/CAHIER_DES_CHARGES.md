# Cahier des charges — Compteur V2

## 1. Objet et règles
Recréer indépendamment les **comportements observables et démontrés** de l'APK de référence. Ne pas copier son code, ses ressources, son nom commercial ni contourner d'éventuelles licences. **P1 = indice statique**, **P2 = contrôle de flux analysé**, **P3 = confirmé à l'exécution**, **P4 = validé dans V2**.

## 2. Inventaire des écrans, listes et tableaux

| ID | Écran / tableau | Preuve binaire | Données / colonnes candidates | Interactions à vérifier | Niveau |
|---|---|---|---|---|---|
| E01 | HomePage | `ViewControllers/HomePage.dart` | compteur courant, valeur | toucher, incrément, décrément, reset, navigation | P1 |
| E02 | EditCounterList | `ViewControllers/EditCounterList.dart` | liste de compteurs, identifiant, nom | sélectionner, modifier, supprimer, réordonner ? | P1 |
| E03 | ArchiveList | `ViewControllers/ArchiveList.dart` | archives, compteur lié, valeur, date ? | consulter, supprimer, tout supprimer | P1 |
| E04 | Setting | `ViewControllers/Setting.dart` | préférences utilisateur | son, vibration, pas, parole, options d'archives | P1 |
| E05 | ArchiveTutorial | `ArchiveTutorial` | aide sur les archives | navigation et fermeture à confirmer | P1 |
| E06 | SpeechCorrectionList | `SpeechCorrectionList` | corrections de synthèse vocale ? | ajout/édition/suppression à confirmer | P1 |

**Attention :** les colonnes avec « ? » sont des hypothèses de conception, non des colonnes SQL prouvées.

## 3. Matrice exhaustive *provisoire* des procédures identifiées

| ID | Fonction candidate | Indice dans le binaire | Préconditions / résultats à vérifier | État |
|---|---|---|---|---|
| F01 | Afficher compteur actif | `currentCounter`, `HomePage.dart` | valeur initiale, format, persistance | P1 |
| F02 | Incrémenter / décrémenter | `_calculateScrollIncrement`, `ScrollIncrementType` | gestes, pas, limites, sens | P1 |
| F03 | Réinitialiser un compteur | `_resetCounter`, `Reset this counter` | confirmation, archive éventuelle | P1 |
| F04 | Réinitialiser tous les compteurs | `_resetAllCounters` | confirmation, effet sur archives | P1 |
| F05 | Modifier compteur | `Edit Counter`, `updateCounter` | nom, valeur, validation | P1 |
| F06 | Supprimer compteur | `Delete this counter` | dernier compteur protégé ? | P1 |
| F07 | Changer compteur | `selectCustomCounter`, `counterList` | sélection, persistance | P1 |
| F08 | Créer archive | `insertArchive`, `_saveArchive` | date, valeur, nom | P1 |
| F09 | Sauvegarder et réinitialiser | `Save to archive and reset` | atomicité, confirmation | P1 |
| F10 | Afficher archives | `_getArchives`, `ArchiveList.dart` | ordre, filtres, pagination | P1 |
| F11 | Supprimer archive | `deleteArchive` | confirmation, impact | P1 |
| F12 | Supprimer toutes les archives | `deleteAllArchive` | confirmation | P1 |
| F13 | Activer vibration | `MethodChannelVibration` | déclenchement, intensité | P1 |
| F14 | Activer son | `SystemSound.play`, 3 MP3 | choix du son, volume | P1 |
| F15 | Régler incrément | `ScrollIncrementType` | valeurs autorisées | P1 |
| F16 | Synthèse vocale | `setSpeechRate`, `SpeechInputToggle` | langues, débit, déclenchement | P1 |
| F17 | Partager | `SharePlus` | format et destination | P1 |
| F18 | Préférences | `_setSettingData`, `_updateUserSettingsData` | valeurs par défaut, persistance | P1 |
| F19 | Tutoriel archives | `ArchiveTutorial` | déclenchement et contenu | P1 |
| F20 | Corrections vocales | `SpeechCorrectionList` | modèle de données | P1 |

## 4. Modèle de données provisoire

| Entité | Champs candidats | Preuves / réserves |
|---|---|---|
| Counter | id, name, value | `counterId`, `counterName`, `CounterDB` ; type exact inconnu |
| Archive | id, counterId, value, timestamp | `Models/Archive.dart`, `insertArchive` ; colonnes non prouvées |
| Settings | sound, vibration, increment, speechRate | symboles de réglage ; stockage exact à déterminer |

## 5. Travaux obligatoires avant validation fonctionnelle
- Décompiler le DEX et cartographier les appels plateforme et permissions.
- Désassembler `libapp.so` ARM64 et relier les références à des chemins d'exécution.
- Inspecter les ressources, traductions et widgets ; produire une table de **chaque** contrôle et action observable.
- Installer les splits originaux sur un appareil ou émulateur **ARM64 compatible**, relever écrans, gestes, états, confirmations et cas limites.
- Comparer la référence et V2 à scénario identique, avec tests instrumentés et preuves.
- Établir la disponibilité gratuite/premium par observation légitime, sans contourner les protections.

## 6. Critères d'acceptation
Chaque Fxx/Eyy doit posséder : source technique, scénario reproductible, résultat attendu, résultat observé sur la référence, implémentation V2, test et statut. Le manager refuse toute fonction dont la preuve est insuffisante. Aucun « 100 % » tant que les inconnues subsistent.

## 7. État actuel
**Cahier des charges initial basé sur le binaire** ; inventaire non exhaustif et non validé à l'exécution. La refonte V2 doit être réalisée progressivement sur ces preuves, avec boucles de correction et intégration.
