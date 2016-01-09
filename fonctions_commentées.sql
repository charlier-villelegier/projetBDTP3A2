-- le nombre de stages par ville choisi par l'utilisateur pour les n derniere années
create or replace FUNCTION fonctionVilleTotal(vil in VARCHAR, val in FLOAT)
RETURN FLOAT is
nb FLOAT;
mois INTEGER;
annee INTEGER;

BEGIN

mois:=extract(month FROM (sysdate)); -- on extrait le mois 
annee:=extract(year FROM (sysdate)); -- on extrait l'année

IF  mois>=1 AND mois<9 THEN -- Test permetant de traiter les années scolaires
	annee:=annee-1;
END IF;

select COUNT(*) into NB
from lcharli.TABLE_STAGE st, lcharli.TABLE_entreprise e
where e.NumEntreprise=ST.ENTREPRISE.numEntreprise
AND E.ADRESSE.Ville=vil
AND ST.ANNEESTAGE BETWEEN annee-val+1 AND annee
;

return nb;

end fonctionVilleTotal;

-- la moyenne des stages par ville choisi par l'utilisateur pour les n derniere années
create or replace FUNCTION fonctionVilleMoyen(vil in VARCHAR, val in FLOAT)
RETURN FLOAT is
n FLOAT;
nb FLOAT;
BEGIN

n:=fonctionVilleTotal(vil, val);
nb:=trunc(n/val,2);

RETURN nb;

end fonctionVilleMoyen;


-- le nombre de stages par departement choisi par l'utilisateur pour les n derniere années
create or replace FUNCTION fonctionDepTotal(dep in INTEGER, val in FLOAT)
RETURN FLOAT is
nb FLOAT;
mois INTEGER;
annee INTEGER;

BEGIN
mois:=extract(month FROM (sysdate)); -- on extrait le mois 
annee:=extract(year FROM (sysdate)); -- on extrait l'année
IF  mois>=1 AND mois<9 THEN -- Test permetant de traiter les années scolaires
	annee:=annee-1;
END IF;

select COUNT(*) into NB
from lcharli.TABLE_STAGE st, lcharli.TABLE_entreprise e
where e.NumEntreprise=ST.ENTREPRISE.numEntreprise
AND E.ADRESSE.Departement=dep
AND ST.ANNEESTAGE BETWEEN annee-val+1 AND annee
;
return nb;
end fonctionDepTotal;

-- la moyenne des stages par departement choisi par l'utilisateur pour les n derniere années
create or replace FUNCTION fonctionDepMoyen(dep in INTEGER, val in FLOAT)
RETURN FLOAT is
n FLOAT;
nb FLOAT;
BEGIN

n:=fonctionDepTotal(dep, val);
nb:=trunc(n/val,2); -- On ne garde que 2 chiffre apres la virgule

RETURN nb;

end fonctionDepMoyen;

-- le nombre d'entreprises ayant encadré un stage les n dernières années,
create or replace FUNCTION getNbEntreprise()
RETURN number is
nb NUMBER;

BEGIN
select COUNT(E.NUMENTREPRISE) into nb
from lcharli.TABLE_STAGE st, lcharli.TABLE_entreprise e
where e.NumEntreprise=ST.ENTREPRISE.numEntreprise
;

return nb;

end getNbEntreprise;

-- le nombre moyen de stagiaires encadrés par les entreprises dans les n dernières années,
create or replace FUNCTION fonctionAVGStage(val in NUMBER)
RETURN NUMBER is
nb NUMBER;
n NUMBER;
mois INTEGER;
annee INTEGER;

BEGIN
mois:=extract(month FROM (sysdate)); -- on extrait le mois
annee:=extract(year FROM (sysdate)); -- on extrait l'année
IF  mois>=1 AND mois<9 THEN -- Test permetant de traiter les années scolaires
	annee:=annee-1;
END IF;

n:=getNbEntreprise();

select (COUNT(*)/n)/val INTO nb
from lcharli.TABLE_STAGE st, lcharli.TABLE_entreprise e
where e.NumEntreprise=ST.ENTREPRISE.numEntreprise
AND ST.ANNEESTAGE BETWEEN annee-val+1 AND annee
;

return trunc(nb,2);

end fonctionAVGStage;

-- le nombre d'entreprises ayant encadré un stage les n dernières années
create or replace FUNCTION nbStageTotalEntreprise(entrep in VARCHAR, val in FLOAT)
RETURN FLOAT is
nb FLOAT;
mois INTEGER;
annee INTEGER;

BEGIN
mois:=extract(month FROM (sysdate)); -- on extrait le mois
annee:=extract(year FROM (sysdate)); -- on extrait l'année

IF  mois>=1 AND mois<9 THEN -- Test permetant de traiter les années scolaires
	annee:=annee-1;
END IF;

select COUNT(*) INTO nb
from lcharli.TABLE_STAGE st
where ST.ENTREPRISE.nomentreprise=entrep
AND ST.ANNEESTAGE BETWEEN annee-val+1 AND annee
;

return nb;

end nbStageTotalEntreprise;

-- le nombre moyen de stagiaires encadrés par les entreprises dans les n dernières années,
create or replace FUNCTION nbStageMoyenEntreprise(entrep in VARCHAR, val in FLOAT)
RETURN FLOAT is
n FLOAT;
nb FLOAT;
BEGIN

n:=nbStageTotalEntreprise(entrep, val); --On récupère le nombre d'entreprises ayant encadré un stage les n dernières années
nb:=trunc(n/val,2); -- On ne garde que 2 chiffre apres la virgule

RETURN nb;

end nbStageMoyenEntreprise;