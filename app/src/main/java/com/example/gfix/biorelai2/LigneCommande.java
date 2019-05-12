package com.example.gfix.biorelai2;

public class LigneCommande {
    private Produit unProduit;
    private Commande uneCommande;
    private Double quantite;
    private Double quantiteLivreClient;
    private Double QUANTITERECUPERECLIENT;
    private Double QUANTITELIVREEPRODUCTEUR;
    private Double QUANTITERECUPEREPRODUCTEUR;
    private Boolean VUERESPONSABLE;

    public LigneCommande(Produit unProduit, Commande uneCommande, Double quantite, Double quantiteLivreClient, Double QUANTITERECUPERECLIENT, Double QUANTITELIVREEPRODUCTEUR, Double QUANTITERECUPEREPRODUCTEUR, int VUERESPONSABLE) {
        this.unProduit = unProduit;
        this.uneCommande = uneCommande;
        this.quantite = quantite;
        this.quantiteLivreClient = quantiteLivreClient;
        this.QUANTITERECUPERECLIENT = QUANTITERECUPERECLIENT;
        this.QUANTITELIVREEPRODUCTEUR = QUANTITELIVREEPRODUCTEUR;
        this.QUANTITERECUPEREPRODUCTEUR = QUANTITERECUPEREPRODUCTEUR;
        if(VUERESPONSABLE == 1 ){
            this.VUERESPONSABLE = true;
        }else{
            this.VUERESPONSABLE = false;
        }

    }

    public Produit getUnProduit() {
        return unProduit;
    }


    public void setUnProduit(Produit unProduit) {
        this.unProduit = unProduit;
    }

    public Commande getUneCommande() {
        return uneCommande;
    }

    public void setUneCommande(Commande uneCommande) {
        this.uneCommande = uneCommande;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Double getQuantiteLivreClient() {
        return quantiteLivreClient;
    }

    public void setQuantiteLivreClient(Double quantiteLivreClient) {
        this.quantiteLivreClient = quantiteLivreClient;
    }

    public Double getQUANTITERECUPERECLIENT() {
        return QUANTITERECUPERECLIENT;
    }

    public void setQUANTITERECUPERECLIENT(Double QUANTITERECUPERECLIENT) {
        this.QUANTITERECUPERECLIENT = QUANTITERECUPERECLIENT;
    }

    public Double getQUANTITELIVREEPRODUCTEUR() {
        return QUANTITELIVREEPRODUCTEUR;
    }

    public void setQUANTITELIVREEPRODUCTEUR(Double QUANTITELIVREEPRODUCTEUR) {
        this.QUANTITELIVREEPRODUCTEUR = QUANTITELIVREEPRODUCTEUR;
    }

    public Double getQUANTITERECUPEREPRODUCTEUR() {
        return QUANTITERECUPEREPRODUCTEUR;
    }

    public void setQUANTITERECUPEREPRODUCTEUR(Double QUANTITERECUPEREPRODUCTEUR) {
        this.QUANTITERECUPEREPRODUCTEUR = QUANTITERECUPEREPRODUCTEUR;
    }

    public Boolean getVUERESPONSABLE() {
        return VUERESPONSABLE;
    }

    public String getVUERESPONSABLEChiffre() {
        String res;
        if(getVUERESPONSABLE()){
            res="1";
        }else{
            res="0";
        }
        return res;
    }

    public void setVUERESPONSABLE(Boolean VUERESPONSABLE) {
        this.VUERESPONSABLE = VUERESPONSABLE;
    }
}
