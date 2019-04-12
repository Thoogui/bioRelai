package com.example.gfix.biorelai2;

/**
 * Created by cguestereguy on 12/04/2019.
 */

public class Adherent {
    private String IDADHERENT;
    private String IDUTILISATEUR;

    public Adherent(String IDADHERENT, String IDUTILISATEUR) {
        this.IDADHERENT = IDADHERENT;
        this.IDUTILISATEUR = IDUTILISATEUR;
    }

    public String getIDADHERENT() {
        return IDADHERENT;
    }

    public void setIDADHERENT(String IDADHERENT) {
        this.IDADHERENT = IDADHERENT;
    }

    public String getIDUTILISATEUR() {
        return IDUTILISATEUR;
    }

    public void setIDUTILISATEUR(String IDUTILISATEUR) {
        this.IDUTILISATEUR = IDUTILISATEUR;
    }
}
