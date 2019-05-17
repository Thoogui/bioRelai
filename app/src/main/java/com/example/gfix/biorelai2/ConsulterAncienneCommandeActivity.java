package com.example.gfix.biorelai2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.OkHttpClient;

public class ConsulterAncienneCommandeActivity extends Activity {

    String responseStr;
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_commande);

        JSONObject log = null;
        //Création d'une ArrayList contenant des Commandes
        ArrayList<Commande> commandes = new ArrayList<>();
        try {
            //Récupération de la variable log
            log = new JSONObject(getIntent().getStringExtra("log"));

            //Si l'utilisateur est un client alors...
            if(log.getString("statut").equals("client")){
                //On récupere l'objet utilisateur lui correspondant
                Utilisateur unUtil = lesUtilisateurs.getUnUtilisateurByIDUTI(log.getString("idutilisateur"));
                //On récupere ses commandes
                commandes = lesCommandes.getListOldCommandesAdherent(unUtil.getIdAdherent());
            }
            //si l'utilisateur est un producteur alors...
            else if(log.getString("statut").equals("producteur")){
                //On récupere l'objet Producteur correspondant à l'utilisateur
                Producteur unProducteur =  lesProducteurs.getProducteurByIDUtilisateur(log.getString("idutilisateur"));
                //On récupere les commandes dans lesquels il y a au moins un des produits du producteur
                commandes = lesCommandes.getListOldCommandesProducteur(unProducteur.getIdProducteur());
            }
            //sinon (si c'est un responsable) alors...
            else{
                //On récupere toutes les anciennes commmandes
                commandes = lesCommandes.getListOldCommandes();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        final ArrayList<HashMap<String,String>> listeCommandes = new  ArrayList<HashMap<String,String>>();
        HashMap<String,String> item ;
        for(Commande uneComm : commandes){
            item = new HashMap<String,String>();
            item.put("ligne1", "Commande n°"+uneComm.getIdCommande());
            item.put("ligne2" , "Client : " +uneComm.getAdherent().getNOMUTILISATEUR() +" - "+uneComm.getDateCommande());
            item.put("idCommande" , uneComm.getIdCommande());
            listeCommandes.add(item);
        }
        //On récupere la listView
        ListView listViewCommande = (ListView) findViewById(R.id.listViewCommandes);
        //On créer un Adapter
        SimpleAdapter adapter = new SimpleAdapter(ConsulterAncienneCommandeActivity.this, listeCommandes, android.R.layout.simple_list_item_2,
                new String[]{"ligne1" , "ligne2"},new int[]{android.R.id.text1 , android.R.id.text2});

        listViewCommande.setAdapter(adapter);

        //On ajoute un listener pour que lorque l'on clique sur un item celui-ci ouvre une activity avec les lignes Commandes
        listViewCommande.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ConsulterAncienneCommandeActivity.this, ConsulterAncienneLigneCommandeActivity.class);
                intent.putExtra("idCommande", listeCommandes.get(position).get("idCommande"));
                intent.putExtra("log", getIntent().getStringExtra("log"));
                startActivity(intent);
            }
        });
    }

}