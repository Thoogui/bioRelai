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

public class ConsulterAncienneLigneCommandeActivity extends Activity {

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
            //On récupere l'objet commande qu'on a sélectionné dans l'activity précedente
            Commande uneCommande = lesCommandes.getUneCommandeByID(getIntent().getStringExtra("idCommande"));
            TextView text = (TextView) findViewById(R.id.textLigne);
            //On met le numéro de la commande pour l'affichage
            text.setText(text.getText() + getIntent().getStringExtra("idCommande"));
            //On récupere les objet radio button
            RadioButton premierBouton = (RadioButton) findViewById(R.id.radioTrie1);
            RadioButton deuxiemeBouton = (RadioButton) findViewById(R.id.radioButtonTrie2);

            //On creer un action listener par rapport au premier radio button
            View.OnClickListener first_radio_listener = new View.OnClickListener(){
                public void onClick(View v) {
                    //si il s'agit d'un client alors..
                    if(statut.equals("client")){
                        //on trie l'arraylist par producteur
                        ligneCommandes.sort(LigneCommande.ComparatorNomProducteur);
                    }
                    //si il s'agit d'un producteur
                    else if(statut.equals("producteur")){
                        //on trie l'arraylist par categorie
                        ligneCommandes.sort(LigneCommande.ComparatorCategorie);
                    }
                    //si il s'agit d'un responsable
                    else{
                        //on trie l'arraylist par produit
                        ligneCommandes.sort(LigneCommande.ComparatorProduit);
                    }
                    //On remplie la liste pour qu'elle soit dans l'ordre sélectionné
                    remplirList();
                }
            };

            //On creer une action listener par rapport au deuxieme radio button
            View.OnClickListener second_radio_listener = new View.OnClickListener(){
                public void onClick(View v) {
                    //si il s'agit d'un client alors..
                    if(statut.equals("client")){
                        //on trie l'arraylist par Categorie
                        ligneCommandes.sort(LigneCommande.ComparatorCategorie);
                    }
                    //si il s'agit d'un producteur
                    else if(statut.equals("producteur")){
                        //on trie l'arraylist par produit
                        ligneCommandes.sort(LigneCommande.ComparatorProduit);
                    }
                    //si il s'agit d'un responsable
                    else{
                        //on trie l'arraylist par producteur
                        ligneCommandes.sort(LigneCommande.ComparatorNomProducteur);
                    }
                    //On remplie la liste pour qu'elle soit dans l'ordre sélectionné
                    remplirList();
                }
            };
            //Si on clique sur le premier radio button alors fait l'action décrite plus haut
            premierBouton.setOnClickListener(first_radio_listener);
            //Si on clique sur le deuxieme radio button alors fai l'action décrite plus haut
            deuxiemeBouton.setOnClickListener(second_radio_listener);
            statut = log.getString("statut");
            //si il s'agit d'un producteur alors..
            if(statut.equals("producteur")){
                //on récupere le producteur
                Producteur unProducteur =  lesProducteurs.getProducteurByIDUtilisateur(log.getString("idutilisateur"));
                //On récupere les lignes commandes correspondant au producteur
                ligneCommandes = lesLignesCommandes.getUneListeLigneProducteurByCommande(uneCommande,unProducteur);
                premierBouton.setText("Trier par Produit");
                deuxiemeBouton.setText("Trier par Catégorie");
                //On trie l'arrayListe par nom du produit
                ligneCommandes.sort(LigneCommande.ComparatorProduit);
            }
            //si il s'agit d'un client ou d'un résponsable alors...
            else{
                //On récupere toute les Lignes commandes d'une commande
                ligneCommandes = lesLignesCommandes.getUneListeLigneByCommande(uneCommande);
                //Si il s'agit d'un client alors..
                if(statut.equals("client")){
                    premierBouton.setText("Trier par Producteurs");
                    deuxiemeBouton.setText("Trier par Catégories");
                    //On trie l'arraylist par Nom du producteur
                    ligneCommandes.sort(LigneCommande.ComparatorNomProducteur);
                }else{ //si il s'agit d'un responsable alors...

                    premierBouton.setText("Trier par Produit");
                    deuxiemeBouton.setText("Trier par Producteur");
                    //On trie l'arrayList par nom du produit
                    ligneCommandes.sort(LigneCommande.ComparatorProduit);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //On remplie la listView
        remplirList();
    }

    //Fonction permmettant de remplir la ListeView par rapport à l'arrayListe ligneCommandes
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
        SimpleAdapter adapter = new SimpleAdapter(ConsulterAncienneLigneCommandeActivity.this, listeCommandes, android.R.layout.simple_list_item_2,
                new String[]{"ligne1" , "ligne2"},new int[]{android.R.id.text1 , android.R.id.text2});
        listViewLigneCommande.setAdapter(adapter);

        listViewLigneCommande.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ConsulterAncienneLigneCommandeActivity.this, ConsulterLaLigneCommandeActivity.class);
                intent.putExtra("idCommande", listeCommandes.get(position).get("idCommande"));
                intent.putExtra("idProduit", listeCommandes.get(position).get("idProduit"));
                intent.putExtra("ancienne", true);
                intent.putExtra("log", getIntent().getStringExtra("log"));
                startActivity(intent);
            }
        });
    }

}