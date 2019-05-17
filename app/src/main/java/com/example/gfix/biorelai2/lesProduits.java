package com.example.gfix.biorelai2;

import java.util.ArrayList;

public class lesProduits {
    private static ArrayList<Produit> listProduits = new ArrayList<Produit>();

    public static void ajouterProduit(Produit unProduit){
        listProduits.add(unProduit);
    }

    public static ArrayList<Produit> getListProduits() {
        return listProduits;
    }

    public static Produit getUnProduitByID(String id) {
        for(Produit unProd : listProduits){
            if(unProd.getIdProduit().equals(id)){
                return unProd;
            }
        }
        return  null;
    }

    public static void clearListe(){
        listProduits.clear();
    }
}
