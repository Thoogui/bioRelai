package com.example.gfix.biorelai2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.OkHttpClient;

public class ConsulterLigneCommandeJourActivity extends Activity
{

    String responseStr;
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_lignescommande);

        JSONObject log = null;
        ArrayList<LigneCommande> ligneCommandes = new ArrayList<>();
        try {
            log = new JSONObject(getIntent().getStringExtra("log"));
            Commande uneCommande = lesCommandes.getUneCommandeByID(getIntent().getStringExtra("idCommande"));
            TextView text = (TextView) findViewById(R.id.textLigne);
            text.setText(text.getText() + getIntent().getStringExtra("idCommande"));
            if(log.getString("statut").equals("producteur")){
                Producteur unProducteur =  lesProducteurs.getProducteurByIDUtilisateur(log.getString("idutilisateur"));
                ligneCommandes = lesLignesCommandes.getUneListeLigneProducteurByCommande(uneCommande,unProducteur);
            }
            else{
                ligneCommandes = lesLignesCommandes.getUneListeLigneByCommande(uneCommande);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        final ArrayList<HashMap<String,String>> listeCommandes = new  ArrayList<HashMap<String,String>>();
        HashMap<String,String> item ;
        for(LigneCommande uneLigne : ligneCommandes){
            item = new HashMap<String,String>();
            item.put("ligne1", uneLigne.unProduit.getNomProduit());
            item.put("ligne2" , uneLigne.unProduit.getDescriptifProduit());
            item.put("idProduit" , uneLigne.unProduit.getIdProduit());
            item.put("idCommande" , uneLigne.uneCommande.getIdCommande());
            listeCommandes.add(item);
        }
        ListView listViewLigneCommande = (ListView) findViewById(R.id.listViewCommandes);
        SimpleAdapter adapter = new SimpleAdapter(ConsulterLigneCommandeJourActivity.this, listeCommandes, android.R.layout.simple_list_item_2,
                new String[]{"ligne1" , "ligne2"},new int[]{android.R.id.text1 , android.R.id.text2});
        listViewLigneCommande.setAdapter(adapter);

        listViewLigneCommande.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ConsulterLigneCommandeJourActivity.this, ConsulterLaLigneCommandeActivity.class);
                intent.putExtra("idCommande", listeCommandes.get(position).get("idCommande"));
                intent.putExtra("idProduit", listeCommandes.get(position).get("idProduit"));
                intent.putExtra("log", getIntent().getStringExtra("log"));
                startActivity(intent);
            }
        });
    }

}