package com.example.gfix.biorelai2;

import java.util.ArrayList;

public class lesCategories {
    private static ArrayList<Categorie> listCategories = new ArrayList<Categorie>();

    public static void ajouterCategorie(Categorie uneCateg){
        listCategories.add(uneCateg);
    }

    public static ArrayList<Categorie> getListCategories() {
        return listCategories;
    }

    public static void clearListe(){
        listCategories.clear();
    }

    public static Categorie getCategorieByID(String unID){
        Categorie uneCat = new Categorie("0", "0");
        for(Categorie uneCateg : listCategories){
            if(uneCateg.getIdCagegorie().equals(unID)){
                return uneCateg;
            }
        }
        return uneCat;
    }

}
