package com.example.gfix.biorelai2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mmaitre on 12/04/2019.
 */

public class lesCommandes {
    private static ArrayList<Commande> listCommandes = new ArrayList<Commande>();

    public static void ajouterCommande(Commande uneComm){
        listCommandes.add(uneComm);
    }

    public static ArrayList<Commande> getListCommandes() {
        return listCommandes;
    }

    public static ArrayList<Commande> getListCommandesJour() {
        ArrayList<Commande> liste = new ArrayList<Commande>();
        Date date = new Date();
        String dateJour = new SimpleDateFormat("yyyy-MM-dd").format(date);
        for(Commande uneComm : listCommandes){
            if(uneComm.getDateCommande().equals(dateJour)){
                liste.add(uneComm);
            }
        }
        return liste;
    }


    public static ArrayList<Commande> getListCommandesJourAdherent(String idAdherent) {
        ArrayList<Commande> liste = new ArrayList<Commande>();
        Date date = new Date();
        String dateJour = new SimpleDateFormat("yyyy-MM-dd").format(date);
        for(Commande uneComm : listCommandes){
            if(uneComm.getDateCommande().equals(dateJour) && uneComm.getAdherent().getIdAdherent().equals(idAdherent)){
                liste.add(uneComm);
            }
        }
        return liste;
    }


    public static ArrayList<Commande> getListCommandesJourProducteur(String idAdherent) {
        ArrayList<Commande> liste = new ArrayList<Commande>();
        Date date = new Date();
        String dateJour = new SimpleDateFormat("yyyy-MM-dd").format(date);
        for(Commande uneComm : listCommandes){
            if(uneComm.getDateCommande().equals(dateJour) && uneComm.getAdherent().getIdAdherent().equals(idAdherent)){
                liste.add(uneComm);
            }
        }
        return liste;
    }
}
