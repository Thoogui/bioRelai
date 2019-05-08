package com.example.gfix.biorelai2;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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
        TextView unText = (TextView) findViewById(R.id.textProduit);
        unText.setText(uneLigne.unProduit.getNomProduit());

        ImageView uneImage = (ImageView) findViewById(R.id.imageProduit);
        Picasso.with(this).load(uneLigne.unProduit.getPhotoProduit()).into(uneImage);

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