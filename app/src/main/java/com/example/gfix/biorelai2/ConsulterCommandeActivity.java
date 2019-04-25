package com.example.gfix.biorelai2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.OkHttpClient;

public class ConsulterCommandeActivity extends Activity {

    String responseStr;
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_commande);
        ArrayList<Commande> commandes = lesCommandes.getListCommandes();
        ArrayList<HashMap<String,String>> listeCommandes = new  ArrayList<HashMap<String,String>>();
        HashMap<String,String> item ;
        for(Commande uneComm : commandes){
            item = new HashMap<String,String>();
            item.put("ligne1", "Commande n°"+uneComm.getIdCommande());
            item.put("ligne2" , uneComm.getDateCommande());
            listeCommandes.add(item);
        }
        ListView listViewCommande = (ListView) findViewById(R.id.listViewCommandes);
        SimpleAdapter adapter = new SimpleAdapter(ConsulterCommandeActivity.this, listeCommandes, android.R.layout.simple_list_item_2,
                new String[]{"ligne1" , "ligne2"},new int[]{android.R.id.text1 , android.R.id.text2});
        listViewCommande.setAdapter(adapter);
    }

}