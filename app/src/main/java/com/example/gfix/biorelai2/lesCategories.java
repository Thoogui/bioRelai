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
}
