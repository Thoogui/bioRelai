package com.example.gfix.biorelai2;

import java.util.ArrayList;

public class lesLignesCommandes {
    private static ArrayList<LigneCommande> listLigneCommandes = new ArrayList<LigneCommande>();

    public static void ajouterLigne(LigneCommande uneLigne){
        listLigneCommandes.add(uneLigne);
    }

    public static ArrayList<LigneCommande> getListLigneCommandes() {
        return listLigneCommandes;
    }


}
