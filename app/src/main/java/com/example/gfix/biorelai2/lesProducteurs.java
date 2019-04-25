package com.example.gfix.biorelai2;

import java.util.ArrayList;

public class lesProducteurs {
    private static ArrayList<Producteur> listProducteurs = new ArrayList<Producteur>();

    public static void ajouterProducteur(Producteur unProducteur){
        listProducteurs.add(unProducteur);
    }

    public static ArrayList<Producteur> getListProducteurs() {
        return listProducteurs;
    }
}
