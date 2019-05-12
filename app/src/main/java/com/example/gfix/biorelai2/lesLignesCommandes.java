package com.example.gfix.biorelai2;

import java.util.ArrayList;

public class lesLignesCommandes {
    private static ArrayList<LigneCommande> listLigneCommandes = new ArrayList<LigneCommande>();

    public static void ajouterLigne(LigneCommande uneLigne){
        listLigneCommandes.add(uneLigne);
    }

    public static LigneCommande getLigneCommandes(Commande uneComm,Produit unProd) {
        for(LigneCommande uneLigne : listLigneCommandes){
            if(uneLigne.getUneCommande().equals(uneComm) && uneLigne.getUnProduit().equals(unProd)){
                return uneLigne;
            }
        }
        return null;
    }

    public static ArrayList<LigneCommande> getListLigneCommandes() {
        return listLigneCommandes;
    }

    public static ArrayList<LigneCommande> getUneListeLigneByCommande(Commande commande) {
        ArrayList<LigneCommande> liste = new ArrayList<LigneCommande>();
        for(LigneCommande uneLigne : listLigneCommandes){
            if(uneLigne.getUneCommande().equals(commande)){
                liste.add(uneLigne);
            }
        }
        return  liste;
    }

    public static ArrayList<LigneCommande> getUneListeLigneProducteurByCommande(Commande commande, Producteur unPro) {
        ArrayList<LigneCommande> liste = new ArrayList<LigneCommande>();
        for(LigneCommande uneLigne : listLigneCommandes){
            if(uneLigne.getUneCommande().equals(commande) && uneLigne.getUnProduit().getUnProducteur().equals(unPro)){
                liste.add(uneLigne);
            }
        }
        return  liste;
    }


}
