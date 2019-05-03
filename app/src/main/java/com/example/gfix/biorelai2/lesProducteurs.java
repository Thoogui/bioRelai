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

    public static Producteur getProducteurByID(String unID){
        for(Producteur unPro : listProducteurs){
            if(unPro.getIdProducteur().equals(unID)){
                return unPro;
            }
        }
        return null;
    }

    public static Producteur getProducteurByIDUtilisateur(String unID){
        for(Producteur unPro : listProducteurs){
            if(unPro.getIdUtiliateur().equals(unID)){
                return unPro;
            }
        }
        return null;
    }
}
