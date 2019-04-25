package com.example.gfix.biorelai2;

public class Produit {
    private String idProduit;
    private Producteur unProducteur;
    private Categorie uneCategorie;
    private String nomProduit;
    private String descriptifProduit;
    private String photoProduit;

    public Produit(String idProduit, Producteur unProducteur, Categorie uneCategorie, String nomProduit, String descriptifProduit, String photoProduit) {
        this.idProduit = idProduit;
        this.unProducteur = unProducteur;
        this.uneCategorie = uneCategorie;
        this.nomProduit = nomProduit;
        this.descriptifProduit = descriptifProduit;
        this.photoProduit = photoProduit;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    public Producteur getUnProducteur() {
        return unProducteur;
    }

    public void setUnProducteur(Producteur unProducteur) {
        this.unProducteur = unProducteur;
    }

    public Categorie getUneCategorie() {
        return uneCategorie;
    }

    public void setUneCategorie(Categorie uneCategorie) {
        this.uneCategorie = uneCategorie;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getDescriptifProduit() {
        return descriptifProduit;
    }

    public void setDescriptifProduit(String descriptifProduit) {
        this.descriptifProduit = descriptifProduit;
    }

    public String getPhotoProduit() {
        return photoProduit;
    }

    public void setPhotoProduit(String photoProduit) {
        this.photoProduit = photoProduit;
    }
}
