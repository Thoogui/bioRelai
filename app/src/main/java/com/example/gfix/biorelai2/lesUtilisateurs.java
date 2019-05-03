package com.example.gfix.biorelai2;

import java.util.ArrayList;

/**
 * Created by mmaitre on 12/04/2019.
 */

public class lesUtilisateurs {
    private static ArrayList<Utilisateur> listUtilisateur = new ArrayList<Utilisateur>();

    public static void ajouterUtilisateur(Utilisateur unUtilisateur){
        listUtilisateur.add(unUtilisateur);
    }

    public static ArrayList<Utilisateur> getListUtilisateur() {
        return listUtilisateur;
    }

    public static Utilisateur getUnUtilisateurByIDAD(String idAdherent) {
        for(Utilisateur unUtil : listUtilisateur){
            if(unUtil.getIdAdherent().equals(idAdherent)){
                return unUtil;
            }
        }
        return  null;
    }

    public static Utilisateur getUnUtilisateurByIDUTI(String idUtilisateur) {
        for(Utilisateur unUtil : listUtilisateur){
            if(unUtil.getIDUTILISATEUR().equals(idUtilisateur)){
                return unUtil;
            }
        }
        return  null;
    }

}
