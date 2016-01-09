create or replace TRIGGER post_insert_etuidant 
AFTER INSERT ON table_etudiant 
FOR EACH ROW

Declare
nb NUMBER;

BEGIN

	SELECT count(*) into nb FROM STAT1;
			
			--On test si une ligne est déja presente dans la table stat
    		IF nb>0 THEN
    			-- On met a jour le nombre d'étudiant avec un stage et sansStage
    			UPDATE STAT1 
    			SET nbSansStage=nbSansStage+1
    			WHERE ANNEE=2016;
    			
    			dbms_output.put_line( 'Mise à jour de la table stat1' );
    		
    		--Si elle n'est pas presente on insert la ligne
    		ELSE 
    		
    			INSERT INTO STAT1 (ANNEE,nbSansStage,nbStage)
    			VALUES (2016,1,0);
    		
    			dbms_output.put_line( 'Ecriture de la ligne dans la table stat1' );

			END IF;
END;


create or replace TRIGGER post_insert_stage 
AFTER INSERT ON TABLE_STAGE 
FOR EACH ROW

BEGIN
			-- On met a jour le nombre d'étudiant avec un stage et sansStage
    		UPDATE STAT1 
    		SET nbStage=nbStage+1,
    		 	nbSansStage=nbSansStage-1
    		WHERE ANNEE=2016;
    		
    		dbms_output.put_line( 'Mise à jour de la table stat1' );
END;

create or replace TRIGGER before_delete_stage 
BEFORE DELETE ON TABLE_STAGE 
FOR EACH ROW

BEGIN
		
	  -- Test pour ne modifier que les stats pour un stage ayant lieu en 2016
      IF :OLD.ANNEESTAGE>2015 THEN 
      -- On met a jour le nombre d'étudiant avec un stage et sans Stage
    		UPDATE STAT1 
    		SET nbStage=nbStage-1,
    		 	nbSansStage=nbSansStage+1
    		WHERE ANNEE=2016;
    		
    	dbms_output.put_line( 'Mise à jour de la table stat1' );
      ELSE
        dbms_output.put_line( 'Mise à jour non requise pour cette année' );
      END IF;
END;