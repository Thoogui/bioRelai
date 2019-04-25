package com.example.gfix.biorelai2;

import java.util.ArrayList;

public class Categorie {
    private String idCagegorie;
    private String nomCategorie;
    private ArrayList<Produit> listeProduits = new ArrayList<>();

    public String getIdCagegorie() {
        return idCagegorie;
    }

    public void setIdCagegorie(String idCagegorie) {
        this.idCagegorie = idCagegorie;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

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
