package com.example.gfix.biorelai2;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.OkHttpClient;

public class ConsulterLaLigneCommandeActivity extends Activity {

    String responseStr;
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ligne_commande);
        LigneCommande uneLigne = lesLignesCommandes.getLigneCommandes(lesCommandes.getUneCommandeByID(getIntent().getStringExtra("idCommande")),lesProduits.getUnProduitByID(getIntent().getStringExtra("idProduit")));
        TextView textProduit = (TextView) findViewById(R.id.textProduit);
        textProduit.setText(uneLigne.unProduit.getNomProduit());

        TextView textQuantite = (TextView) findViewById(R.id.textQuantite);
        textQuantite.setText(textQuantite.getText() + " : "+uneLigne.quantite +"g");

        ImageView uneImage = (ImageView) findViewById(R.id.imageProduit);
        Picasso.with(this).load(uneLigne.unProduit.getPhotoProduit()).into(uneImage);
        TextView textInfo = (TextView) findViewById(R.id.textInfo);
        if(getIntent().getBooleanExtra("ancienne",false)){
            if(uneLigne.QUANTITELIVREEPRODUCTEUR == uneLigne.QUANTITERECUPERECLIENT){
                textInfo.setText("La commande a été récupéré.");
            }
            else if(uneLigne.QUANTITERECUPERECLIENT >0){
                textInfo.setText("La commande a été récupéré partiellement.");
            }else{
                textInfo.setText("La commande n'a pas été récupéré.");
            }
            TextView textQuantiteFinal = (TextView) findViewById(R.id.textQuantiteFinal);
            textQuantiteFinal.setVisibility(View.INVISIBLE);



        }else{
            if(uneLigne.QUANTITELIVREEPRODUCTEUR == uneLigne.QUANTITERECUPERECLIENT){
                textInfo.setText("La commande a été récupéré");
            }
            else if(uneLigne.QUANTITERECUPERECLIENT >0){
                textInfo.setText("La commande a été récupéré partiellement.");
            }else{
                textInfo.setText("La commande n'a pas encore été récupéré");
            }
        }
        JSONObject log = null;
        ArrayList<Commande> commandes = new ArrayList<>();
        try {
            log = new JSONObject(getIntent().getStringExtra("log"));

            if(log.getString("statut").equals("client")){
                Utilisateur unUtil = lesUtilisateurs.getUnUtilisateurByIDUTI(log.getString("idutilisateur"));
            }
            else if(log.getString("statut").equals("producteur")){
                Producteur unProducteur =  lesProducteurs.getProducteurByIDUtilisateur(log.getString("idutilisateur"));
            }
            else{

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}