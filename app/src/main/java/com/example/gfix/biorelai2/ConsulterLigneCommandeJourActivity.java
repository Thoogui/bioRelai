package com.example.gfix.biorelai2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
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
    ArrayList<LigneCommande> ligneCommandes = new ArrayList<>();
    String statut;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_lignescommande);

        JSONObject log = null;
        try {
            log = new JSONObject(getIntent().getStringExtra("log"));
            Commande uneCommande = lesCommandes.getUneCommandeByID(getIntent().getStringExtra("idCommande"));
            TextView text = (TextView) findViewById(R.id.textLigne);
            text.setText(text.getText() + getIntent().getStringExtra("idCommande"));
            RadioButton premierBouton = (RadioButton) findViewById(R.id.radioTrie1);
            RadioButton deuxiemeBouton = (RadioButton) findViewById(R.id.radioButtonTrie2);
            View.OnClickListener first_radio_listener = new View.OnClickListener(){
                public void onClick(View v) {
                    if(statut.equals("client")){
                        ligneCommandes.sort(LigneCommande.ComparatorNomProducteur);
                    }
                    else if(statut.equals("producteur")){
                        ligneCommandes.sort(LigneCommande.ComparatorCategorie);
                    }
                    else{
                        ligneCommandes.sort(LigneCommande.ComparatorProduit);
                    }
                    remplirList();
                }
            };

            View.OnClickListener second_radio_listener = new View.OnClickListener(){
                public void onClick(View v) {
                    if(statut.equals("client")){
                        ligneCommandes.sort(LigneCommande.ComparatorCategorie);
                    }
                    else if(statut.equals("producteur")){
                        ligneCommandes.sort(LigneCommande.ComparatorProduit);
                    }
                    else{
                        ligneCommandes.sort(LigneCommande.ComparatorNomProducteur);
                    }
                    remplirList();
                }
            };
            premierBouton.setOnClickListener(first_radio_listener);
            deuxiemeBouton.setOnClickListener(second_radio_listener);
            statut = log.getString("statut");


            if(log.getString("statut").equals("producteur")){
                Producteur unProducteur =  lesProducteurs.getProducteurByIDUtilisateur(log.getString("idutilisateur"));
                ligneCommandes = lesLignesCommandes.getUneListeLigneProducteurByCommande(uneCommande,unProducteur);
                premierBouton.setText("Trier par Produit");
                deuxiemeBouton.setText("Trier par Catégorie");
                ligneCommandes.sort(LigneCommande.ComparatorClient);
            }
            else{
                ligneCommandes = lesLignesCommandes.getUneListeLigneByCommande(uneCommande);
                if(log.getString("statut").equals("client")){
                    premierBouton.setText("Trier par Producteurs");
                    deuxiemeBouton.setText("Trier par Catégories");
                    ligneCommandes.sort(LigneCommande.ComparatorNomProducteur);
                }else{
                    premierBouton.setText("Trier par Produit");
                    deuxiemeBouton.setText("Trier par Producteur");
                    ligneCommandes.sort(LigneCommande.ComparatorClient);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        remplirList();

    }

    public void remplirList(){
        final ArrayList<HashMap<String,String>> listeCommandes = new  ArrayList<HashMap<String,String>>();
        HashMap<String,String> item ;
        for(LigneCommande uneLigne : ligneCommandes){
            item = new HashMap<String,String>();
            item.put("ligne1", uneLigne.getUnProduit().getNomProduit() +" - "+uneLigne.getQuantite() +"g");
            item.put("ligne2" ,uneLigne.getUnProduit().getUneCategorie().getNomCategorie() +" - "+ uneLigne.getUnProduit().getDescriptifProduit());
            item.put("idProduit" , uneLigne.getUnProduit().getIdProduit());
            item.put("idCommande" , uneLigne.getUneCommande().getIdCommande());
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
                intent.putExtra("ancienne", false);
                intent.putExtra("log", getIntent().getStringExtra("log"));
                startActivity(intent);
            }
        });
    }



}