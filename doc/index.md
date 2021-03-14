# Source des données

Les données sont récupérées à partir
de <https://www.data.gouv.fr/fr/pages/donnees-coronavirus>.

## Données Hospitalières

Source : <https://www.data.gouv.fr/fr/datasets/donnees-hospitalieres-relatives-a-lepidemie-de-covid-19>

Il s'agit de fichiers CSV.

Sexe : 
 * 0 : hommes + femmes (+ non communiqué)
 * 1 : hommes
 * 2 : femmes

### covid-hospit-incid-reg

#### Les données relatives à les nouvelles admissions en réanimation par région

nombre de nouveaux patients admis en réanimation dans les 24 dernières heures.

URL stable : https://www.data.gouv.fr/fr/datasets/r/a1466f7f-4ece-4158-a373-f5d4db167eb0

#### Format

| jour | nomReg | numReg | incid_rea
| --- | --- | --- | ---
| date notification | nom région | numéro région | nombre de nouveau patients en rea
| string | string | integer | integer
| 2020-03-19 | Auvergne-Rhône-Alpes | 84 |44

### donnees-hospitalieres-covid19

#### Les données hospitalières relatives à l'épidémie du COVID-19 par département et sexe du patient

nombre de patients hospitalisés, nombre de personnes actuellement en réanimation ou soins
intensifs, nombre cumulé de personnes retournées à domicile, nombre cumulé de personnes
décédées.

URL stable : https://www.data.gouv.fr/fr/datasets/r/63352e38-d353-4b54-bfd1-f1b3ee1cabd7

#### Format

| dep | sexe | jour | hosp | rea | rad | dc
| --- | --- | --- | --- | --- | --- | ---
| numéro département | numéro sexe | date notification | nombre actuellement hospitalisé | nombre actuellement en soins intensifs | cumul retour à domicile | cumul décés hôpital
| string | string | string | integer | integer | integer | integer
| 01 | 0 | 2020-03-18 | 2 | 0 | 1 | 0

### donnees-hospitalieres-nouveaux-covid19

#### Les données hospitalières quotidiennes relatives à l'épidémie du COVID-19 par département

nombre quotidien de personnes nouvellement hospitalisées, nombre quotidien de nouvelles
admissions en réanimation, nombre quotidien de personnes nouvellement décédées, nombre
quotidien de nouveaux retours à domicile.

URL stable : https://www.data.gouv.fr/fr/datasets/r/6fadff46-9efd-4c53-942a-54aca783c30c

#### Format

| dep | jour | incid_hosp | incid_rea | incid_dc | incid_rad
| --- | --- | --- | --- | --- | ---
| numéro département | date notification | nombre nouvelles hospitalisations | nombre nouvelles admissions soins intensifs | nombre nouveaux décés | nombre nouveaux retour à domicile
| string | string | integer | integer | integer | integer
| 01 | 2020-03-19 | 1 | 0 | 0 | 0

### donnees-hospitalieres-classe-age-covid19

#### Les données hospitalières relatives à l'épidémie du COVID-19 par région, et classe d'âge du patient

nombre de patients hospitalisés, nombre de personnes actuellement en réanimation ou soins
intensifs, nombre cumulé de personnes retournées à domicile, nombre cumulé de personnes
décédées.

URL stable : https://www.data.gouv.fr/fr/datasets/r/08c18e08-6780-452d-9b8c-ae244ad529b3

#### Format

| reg | cl_age90 | jour | hosp | rea | rad | dc
| --- | --- | --- | --- | --- | --- | ---
| numéro région | classe âge | date notification | nombre actuellement hospitalisé | nombre actuellement en soins intensifs | cumul retour à domicile | cumul décés hôpital
| string | string | string | integer| integer| integer| integer
| 1 | 0 | 2020-03-18 | 0 | 0 | 0 | 0

### donnees-hospitalieres-etablissements-covid19

#### Les données relatives aux établissements hospitaliers par département

nombre cumulé de services ayant déclaré au moins un cas.

URL stable : https://www.data.gouv.fr/fr/datasets/r/41b9bd2a-b5b6-4271-8878-e45a8902ef00

#### Format

TODO



