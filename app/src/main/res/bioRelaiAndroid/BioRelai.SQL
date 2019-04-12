
# -----------------------------------------------------------------------------
#       TABLE : VENTE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS VENTE
 (
   IDVENTE VARCHAR(5) NOT NULL  ,
   DATEVENTE DATE NULL  ,
   DATEDEBUTPROD DATE NULL  ,
   DATEFINPROD DATE NULL  ,
   DATEDEBUTCLI DATE NULL  ,
   DATEFINCLI DATE NULL  
   , PRIMARY KEY (IDVENTE) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : CATEGORIE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS CATEGORIE
 (
   IDCATEGORIE INTEGER(11) NOT NULL AUTO_INCREMENT  ,
   NOMCATEGORIE VARCHAR(40) NULL  
   , PRIMARY KEY (IDCATEGORIE) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : PRODUCTEUR
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS PRODUCTEUR
 (
   IDPRODUCTEUR INTEGER(11) NOT NULL AUTO_INCREMENT ,
   IDUTILISATEUR INTEGER(11) NOT NULL  ,
   ADRESSEPRODUCTEUR VARCHAR(50) NULL  ,
   COMMUNEPRODUCTEUR VARCHAR(40) NULL  ,
   CODEPOSTALPRODUCTEUR VARCHAR(5) NULL  ,
   DESCRIPTIFPRODUCTEUR VARCHAR(255) NULL  ,
   PHOTOPRODUCTEUR VARCHAR(40) NULL  
   , PRIMARY KEY (IDPRODUCTEUR) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE PRODUCTEUR
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_PRODUCTEUR_UTILISATEUR
     ON PRODUCTEUR (IDUTILISATEUR ASC);

# -----------------------------------------------------------------------------
#       TABLE : PRODUIT
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS PRODUIT
 (
   IDPRODUIT INTEGER(11) NOT NULL AUTO_INCREMENT ,
   IDPRODUCTEUR INTEGER(11) NULL  ,
   IDCATEGORIE INTEGER(11) NOT NULL  ,
   NOMPRODUIT VARCHAR(50) NULL  ,
   DESCRIPTIFPRODUIT VARCHAR(255) NULL  ,
   PHOTOPRODUIT VARCHAR(40) NULL  
   , PRIMARY KEY (IDPRODUIT) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE PRODUIT
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_PRODUIT_PRODUCTEUR
     ON PRODUIT (IDPRODUCTEUR ASC);

CREATE  INDEX I_FK_PRODUIT_CATEGORIE
     ON PRODUIT (IDCATEGORIE ASC);

# -----------------------------------------------------------------------------
#       TABLE : ADHERENT
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS ADHERENT
 (
   IDADHERENT INTEGER(11) NOT NULL AUTO_INCREMENT,
   IDUTILISATEUR INTEGER(11) NOT NULL  
   , PRIMARY KEY (IDADHERENT) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE ADHERENT
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_ADHERENT_UTILISATEUR
     ON ADHERENT (IDUTILISATEUR ASC);

# -----------------------------------------------------------------------------
#       TABLE : COMMANDE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS COMMANDE
 (
   IDCOMMANDE INTEGER(11) NOT NULL AUTO_INCREMENT ,
   IDADHERENT INTEGER(11) NOT NULL  ,
   DATECOMMANDE DATE NULL  
   , PRIMARY KEY (IDCOMMANDE) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE COMMANDE
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_COMMANDE_ADHERENT
     ON COMMANDE (IDADHERENT ASC);

# -----------------------------------------------------------------------------
#       TABLE : UTILISATEUR
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS UTILISATEUR
 (
   IDUTILISATEUR INTEGER(11) NOT NULL AUTO_INCREMENT ,
   MAIL VARCHAR(50) NULL  ,
   MDP VARCHAR(128) NULL  ,
   STATUT VARCHAR(15) NULL  ,
   NOMUTILISATEUR CHAR(40) NULL  ,
   PRENOMUTILISATEUR VARCHAR(40) NULL  
   , PRIMARY KEY (IDUTILISATEUR) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : PROPOSER
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS PROPOSER
 (
   IDPRODUIT INTEGER(11) NOT NULL  ,
   IDVENTE VARCHAR(5) NOT NULL  ,
   UNITE VARCHAR(10) NULL  ,
   QUANTITE VARCHAR(5) NULL  ,
   PRIX CHAR(32) NULL  
   , PRIMARY KEY (IDPRODUIT,IDVENTE) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE PROPOSER
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_PROPOSER_PRODUIT
     ON PROPOSER (IDPRODUIT ASC);

CREATE  INDEX I_FK_PROPOSER_VENTE
     ON PROPOSER (IDVENTE ASC);

# -----------------------------------------------------------------------------
#       TABLE : LIGNES_COMMANDE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS LIGNES_COMMANDE
 (
   IDPRODUIT INTEGER(11) NOT NULL  ,
   IDCOMMANDE INTEGER(11) NOT NULL  ,
   QUANTITE DECIMAL(10,2) NULL  ,
   QUANTITELIVREECLIENT DECIMAL(10,2) NULL  ,
   QUANTITERECUPERECLIENT DECIMAL(10,2) NULL  ,
   QUANTITELIVREEPRODUCTEUR DECIMAL(10,2) NULL  ,
   QUANTITERECUPEREPRODUCTEUR DECIMAL(10,2) NULL  ,
   VUERESPONSABLE BOOL NULL  
   , PRIMARY KEY (IDPRODUIT,IDCOMMANDE) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE LIGNES_COMMANDE
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_LIGNES_COMMANDE_PRODUIT
     ON LIGNES_COMMANDE (IDPRODUIT ASC);

CREATE  INDEX I_FK_LIGNES_COMMANDE_COMMANDE
     ON LIGNES_COMMANDE (IDCOMMANDE ASC);


# -----------------------------------------------------------------------------
#       CREATION DES REFERENCES DE TABLE
# -----------------------------------------------------------------------------


ALTER TABLE PRODUCTEUR 
  ADD FOREIGN KEY FK_PRODUCTEUR_UTILISATEUR (IDUTILISATEUR)
      REFERENCES UTILISATEUR (IDUTILISATEUR) ;


ALTER TABLE PRODUIT 
  ADD FOREIGN KEY FK_PRODUIT_PRODUCTEUR (IDPRODUCTEUR)
      REFERENCES PRODUCTEUR (IDPRODUCTEUR) ;


ALTER TABLE PRODUIT 
  ADD FOREIGN KEY FK_PRODUIT_CATEGORIE (IDCATEGORIE)
      REFERENCES CATEGORIE (IDCATEGORIE) ;


ALTER TABLE ADHERENT 
  ADD FOREIGN KEY FK_ADHERENT_UTILISATEUR (IDUTILISATEUR)
      REFERENCES UTILISATEUR (IDUTILISATEUR) ;


ALTER TABLE COMMANDE 
  ADD FOREIGN KEY FK_COMMANDE_ADHERENT (IDADHERENT)
      REFERENCES ADHERENT (IDADHERENT) ;


ALTER TABLE PROPOSER 
  ADD FOREIGN KEY FK_PROPOSER_PRODUIT (IDPRODUIT)
      REFERENCES PRODUIT (IDPRODUIT) ;


ALTER TABLE PROPOSER 
  ADD FOREIGN KEY FK_PROPOSER_VENTE (IDVENTE)
      REFERENCES VENTE (IDVENTE) ;


ALTER TABLE LIGNES_COMMANDE 
  ADD FOREIGN KEY FK_LIGNES_COMMANDE_PRODUIT (IDPRODUIT)
      REFERENCES PRODUIT (IDPRODUIT) ;


ALTER TABLE LIGNES_COMMANDE 
  ADD FOREIGN KEY FK_LIGNES_COMMANDE_COMMANDE (IDCOMMANDE)
      REFERENCES COMMANDE (IDCOMMANDE) ;

