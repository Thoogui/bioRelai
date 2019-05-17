package com.example.gfix.biorelai2;

import java.util.Comparator;

public class LigneCommande {
    private Produit unProduit;
    private Commande uneCommande;
    private Double quantite;
    private Double quantiteLivreClient;
    private Double QUANTITERECUPERECLIENT;
    private Double QUANTITELIVREEPRODUCTEUR;
    private  Double QUANTITELIVREECLIENT;
    private Double QUANTITERECUPEREPRODUCTEUR;
    private Boolean VUERESPONSABLE;

    public LigneCommande(Produit unProduit, Commande uneCommande, Double quantite, Double quantiteLivreClient, Double QUANTITERECUPERECLIENT, Double QUANTITELIVREEPRODUCTEUR, Double QUANTITERECUPEREPRODUCTEUR, int VUERESPONSABLE, Double QUANTITELIVREECLIENT) {

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
        this.QUANTITELIVREECLIENT =  QUANTITELIVREECLIENT;

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

    public Double getQUANTITELIVREECLIENT() {
        return QUANTITELIVREECLIENT;
    }

    public void setQUANTITELIVREECLIENT(Double QUANTITELIVREECLIENT) {
        this.QUANTITELIVREECLIENT = QUANTITELIVREECLIENT;
    }

    public void setVUERESPONSABLE(Boolean VUERESPONSABLE) {
        this.VUERESPONSABLE = VUERESPONSABLE;
    }

    public static Comparator<LigneCommande> ComparatorNomProducteur = new Comparator<LigneCommande>() {
        @Override
        public int compare(LigneCommande e1, LigneCommande e2) {
            return e1.getUnProduit().getUnProducteur().getNomProducteur().compareTo(e2.getUnProduit().getUnProducteur().getNomProducteur());
        }
    };

    public static Comparator<LigneCommande> ComparatorCategorie = new Comparator<LigneCommande>() {
        @Override
        public int compare(LigneCommande e1, LigneCommande e2) {
            return e1.getUnProduit().getUneCategorie().getNomCategorie().compareTo(e2.getUnProduit().getUneCategorie().getNomCategorie());
        }
    };

    public static Comparator<LigneCommande> ComparatorClient = new Comparator<LigneCommande>() {
        @Override
        public int compare(LigneCommande e1, LigneCommande e2) {
            return e1.getUneCommande().getAdherent().getNOMUTILISATEUR().compareTo(e2.getUneCommande().getAdherent().getNOMUTILISATEUR());
        }
    };

    public static Comparator<LigneCommande> ComparatorProduit = new Comparator<LigneCommande>() {
        @Override
        public int compare(LigneCommande e1, LigneCommande e2) {
            return e1.getUnProduit().getNomProduit().compareTo(e2.getUnProduit().getNomProduit());
        }
    };
}
