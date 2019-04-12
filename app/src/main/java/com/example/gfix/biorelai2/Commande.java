package com.example.gfix.biorelai2;

/**
 * Created by leconnard on 05/04/2019.
 */

public class Commande {
    private String idCommande;
    private String idAdherent;
    private String dateCommande;

    public Commande(String idCommande, String idAdherent, String dateCommande) {
        this.idCommande = idCommande;
        this.idAdherent = idAdherent;
        this.dateCommande = dateCommande;
    }

    public String getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(String idCommande) {
        this.idCommande = idCommande;
    }

    public String getIdAdherent() {
        return idAdherent;
    }



    public void setIdAdherent(String idAdherent) {
        this.idAdherent = idAdherent;
    }

    public String getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(String dateCommande) {
        this.dateCommande = dateCommande;
    }


}
