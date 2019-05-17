package com.example.gfix.biorelai2;

import java.util.ArrayList;

public class Categorie {
    //Attributs de la Classe
    private String idCagegorie;
    private String nomCategorie;
    private ArrayList<Produit> listeProduits = new ArrayList<>();

    //Renvoie l'id de l'objet Categorie
    public String getIdCagegorie() {
        return idCagegorie;
    }

    //Modifie l'id de l'objet
    public void setIdCagegorie(String idCagegorie) {
        this.idCagegorie = idCagegorie;
    }

    //Renvoie le nom de l'objet Categorie
    public String getNomCategorie() {
        return nomCategorie;
    }

    //Modifie le nom de la Categorie
    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    //Constructeur pour instancier une Categorie
    public Categorie(String idCagegorie, String nomCategorie) {
        this.idCagegorie = idCagegorie;
        this.nomCategorie = nomCategorie;
    }


    public ArrayList<Produit> getListeProduits() {
        return listeProduits;
    }

    public void setListeProduits(ArrayList<Produit> listeProduits) {
        this.listeProduits = listeProduits;
    }
}
