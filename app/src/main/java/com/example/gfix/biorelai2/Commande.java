package com.example.gfix.biorelai2;

/**
 * Created by leconnard on 05/04/2019.
 */

public class Commande {
    //Attribut de la classe Commande
    private String idCommande;
    private Utilisateur Adherent;
    private String dateCommande;

    //Constructeur de la Classe Commande
    public Commande(String idCommande, Utilisateur Adherent, String dateCommande) {
        this.idCommande = idCommande;
        this.Adherent = Adherent;
        this.dateCommande = dateCommande;
    }

    public String getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(String idCommande) {
        this.idCommande = idCommande;
    }

    public Utilisateur getAdherent() {
        return Adherent;
    }



    public void setIdAdherent(Utilisateur Adherent) {
        this.Adherent = Adherent;
    }

    public String getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(String dateCommande) {
        this.dateCommande = dateCommande;
    }


}
