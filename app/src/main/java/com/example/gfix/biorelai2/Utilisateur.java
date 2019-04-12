package com.example.gfix.biorelai2;

public class Utilisateur {
    private String IDUTILISATEUR;
    private String STATUT;
    private String NOMUTILISATEUR;
    private String PRENOMUTILISATEUR;
    private String LOGINUTILISATEUR;
    private String idAdherent;

    public Utilisateur(String IDUTILISATEUR, String STATUT, String NOMUTILISATEUR, String PRENOMUTILISATEUR, String LOGINUTILISATEUR, String idAdherent) {
        this.IDUTILISATEUR = IDUTILISATEUR;
        this.STATUT = STATUT;
        this.NOMUTILISATEUR = NOMUTILISATEUR;
        this.PRENOMUTILISATEUR = PRENOMUTILISATEUR;
        this.LOGINUTILISATEUR = LOGINUTILISATEUR;
        this.idAdherent = idAdherent;
    }

    public void setIDUTILISATEUR(String IDUTILISATEUR) {
        this.IDUTILISATEUR = IDUTILISATEUR;
    }


    public String getSTATUT() {
        return STATUT;
    }

    public void setSTATUT(String STATUT) {
        this.STATUT = STATUT;
    }

    public String getNOMUTILISATEUR() {
        return NOMUTILISATEUR;
    }

    public void setNOMUTILISATEUR(String NOMUTILISATEUR) {
        this.NOMUTILISATEUR = NOMUTILISATEUR;
    }

    public String getPRENOMUTILISATEUR() {
        return PRENOMUTILISATEUR;
    }

    public void setPRENOMUTILISATEUR(String PRENOMUTILISATEUR) {
        this.PRENOMUTILISATEUR = PRENOMUTILISATEUR;
    }

    public String getLOGINUTILISATEUR() {
        return LOGINUTILISATEUR;
    }

    public void setLOGINUTILISATEUR(String LOGINUTILISATEUR) {
        this.LOGINUTILISATEUR = LOGINUTILISATEUR;
    }

    public void setIdAdherent(String idAdherent) {
        this.idAdherent = idAdherent;
    }

    public String getIDUTILISATEUR() {
        return IDUTILISATEUR;
    }
}
