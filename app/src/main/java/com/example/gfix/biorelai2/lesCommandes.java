package com.example.gfix.biorelai2;

import java.util.ArrayList;

/**
 * Created by mmaitre on 12/04/2019.
 */

public class lesCommandes {
    private static ArrayList<Commande> listCommande = new ArrayList<Commande>();

    public static void ajouterCommande(Commande uneComm){
        listCommande.add(uneComm);
    }

    public static ArrayList<Commande> getListCommande() {
        return listCommande;
    }
}
