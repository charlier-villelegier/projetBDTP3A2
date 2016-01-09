CREATE TYPE objet_adresse AS OBJECT( 
  Ville VARCHAR(100),
  Departement INTEGER);

CREATE TYPE objet_personne AS OBJECT(
  Nom VARCHAR(50),
  Prenom VARCHAR(50),
  Adresse objet_adresse,
  Mail VARCHAR(50));

CREATE OR REPLACE TYPE objet_etudiant AS OBJECT(
  NumEtudiant INTEGER,
  Personne objet_personne);
  
CREATE TABLE table_etudiant OF objet_etudiant
  (NumEtudiant PRIMARY KEY);

CREATE TYPE objet_contact AS OBJECT(
  NumContact INTEGER,
  Personne objet_personne);

CREATE TABLE table_contact OF objet_contact
  (NumContact PRIMARY KEY);

CREATE TYPE objet_entreprise AS OBJECT(
  NumEntreprise INTEGER,
  NomEntreprise VARCHAR(50),
  Adresse objet_adresse,
  Contact REF objet_contact);
  
CREATE TABLE table_entreprise OF objet_entreprise
  (NumEntreprise PRIMARY KEY);
  
  
CREATE TYPE objet_stage AS OBJECT(
  NumStage INTEGER,
  AnneeStage INTEGER,
  Etudiant REF objet_etudiant,
  Entreprise REF objet_entreprise,
  DateStageTrouve DATE);

CREATE TABLE table_stage OF objet_stage
  (NumStage PRIMARY KEY);
  
CREATE TABLE stat1 (
  Annee NUMBER,
  NbSansStage NUMBER,
  NbStage NUMBER);