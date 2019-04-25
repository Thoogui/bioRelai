package com.example.gfix.biorelai2;

public class Producteur {
    private String idProducteur;
    private String idUtiliateur;
    private String nomProducteur;
    private String prenomProducteur;
    private String mail;
    private String adresseProducteur;
    private String communeProducteur;
    private String cpProducteur;
    private String decriptifProducteur;
    private String photoCouverture;

    public Producteur(String idProducteur, String idUtiliateur, String nomProducteur, String prenomProducteur, String mail, String adresseProducteur, String communeProducteur, String cpProducteur, String decriptifProducteur, String photoCouverture) {
        this.idProducteur = idProducteur;
        this.idUtiliateur = idUtiliateur;
        this.nomProducteur = nomProducteur;
        this.prenomProducteur = prenomProducteur;
        this.mail = mail;
        this.adresseProducteur = adresseProducteur;
        this.communeProducteur = communeProducteur;
        this.cpProducteur = cpProducteur;
        this.decriptifProducteur = decriptifProducteur;
        this.photoCouverture = photoCouverture;
    }

    public String getIdProducteur() {
        return idProducteur;
    }

    public void setIdProducteur(String idProducteur) {
        this.idProducteur = idProducteur;
    }

    public String getIdUtiliateur() {
        return idUtiliateur;
    }

    public void setIdUtiliateur(String idUtiliateur) {
        this.idUtiliateur = idUtiliateur;
    }

    public String getNomProducteur() {
        return nomProducteur;
    }

    public void setNomProducteur(String nomProducteur) {
        this.nomProducteur = nomProducteur;
    }

    public String getPrenomProducteur() {
        return prenomProducteur;
    }

    public void setPrenomProducteur(String prenomProducteur) {
        this.prenomProducteur = prenomProducteur;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAdresseProducteur() {
        return adresseProducteur;
    }

    public void setAdresseProducteur(String adresseProducteur) {
        this.adresseProducteur = adresseProducteur;
    }

    public String getCommuneProducteur() {
        return communeProducteur;
    }

    public void setCommuneProducteur(String communeProducteur) {
        this.communeProducteur = communeProducteur;
    }

    public String getCpProducteur() {
        return cpProducteur;
    }

    public void setCpProducteur(String cpProducteur) {
        this.cpProducteur = cpProducteur;
    }

    public String getDecriptifProducteur() {
        return decriptifProducteur;
    }

    public void setDecriptifProducteur(String decriptifProducteur) {
        this.decriptifProducteur = decriptifProducteur;
    }

    public String getPhotoCouverture() {
        return photoCouverture;
    }

    public void setPhotoCouverture(String photoCouverture) {
        this.photoCouverture = photoCouverture;
    }
}
