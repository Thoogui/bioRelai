package com.example.gfix.biorelai2;

public class LigneCommande {
    Produit unProduit;
    Commande uneCommande;
    Double quantite;
    Double quantiteLivreClient;
    Double QUANTITERECUPERECLIENT;
    Double QUANTITELIVREEPRODUCTEUR;
    Double QUANTITERECUPEREPRODUCTEUR;
    Boolean VUERESPONSABLE;

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


}
