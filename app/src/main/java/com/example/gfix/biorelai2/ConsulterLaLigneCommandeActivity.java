package com.example.gfix.biorelai2;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ConsulterLaLigneCommandeActivity extends Activity {

    private LigneCommande uneLigne;
    String responseStr;
    OkHttpClient client = new OkHttpClient();
    String statut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ligne_commande);
        //On récupere l'objet de la ligne commande
        uneLigne = lesLignesCommandes.getLigneCommandes(lesCommandes.getUneCommandeByID(getIntent().getStringExtra("idCommande")),lesProduits.getUnProduitByID(getIntent().getStringExtra("idProduit")));
        TextView textProduit = (TextView) findViewById(R.id.textProduit);
        //On affiche le nom du produit
        textProduit.setText(uneLigne.getUnProduit().getNomProduit());

        TextView textQuantite = (TextView) findViewById(R.id.textQuantite);
        //On affiche la quantite
        textQuantite.setText(textQuantite.getText() + " : "+uneLigne.getQuantite() +"g");

        ImageView uneImage = (ImageView) findViewById(R.id.imageProduit);
        //On affiche l'image du produit
        Picasso.with(this).load(uneLigne.getUnProduit().getPhotoProduit()).into(uneImage);
        TextView textInfo = (TextView) findViewById(R.id.textInfo);

        EditText editQuantiteRecup = (EditText) findViewById(R.id.editQuantiteRecup);
        EditText editQuantiteLivre = (EditText) findViewById(R.id.editQuantiteLivre);
        Button valider = (Button) findViewById(R.id.btnAction);



        //si il s'agit d'une ancienne commande alors...
        if(getIntent().getBooleanExtra("ancienne",false)){
            //si la commande a été entierement recuperer alors on affiche le message correspondant

            if(uneLigne.getQUANTITELIVREEPRODUCTEUR().equals(uneLigne.getQUANTITERECUPERECLIENT())){
                textInfo.setText("La commande a été récupéré.");
            }
            //Si le client a récupéré au moins quelque chose alors la commande a été recupere partiellement
            else if(uneLigne.getQUANTITERECUPERECLIENT() >0){
                textInfo.setText("La commande a été récupéré partiellement.");
            }else{
                if(uneLigne.getQUANTITELIVREEPRODUCTEUR() == 0){
                    textInfo.setText("Le producteur "+uneLigne.getUnProduit().getUnProducteur().getNomProducteur()+" a été absent lors de cette commande");
                }else{
                    textInfo.setText("Le client "+uneLigne.getUneCommande().getAdherent().getNOMUTILISATEUR()+" a été absent lors de cette commande");
                }

            }

             editQuantiteLivre.setEnabled(false);
            editQuantiteRecup.setEnabled(false);
            valider.setVisibility(View.INVISIBLE);



        }else{ //si il s'agit d'une commande du jour alors...
            //Si toute la commande a été récuperer alors..
            if(uneLigne.getQUANTITELIVREEPRODUCTEUR().equals(uneLigne.getQUANTITERECUPERECLIENT()) && uneLigne.getQuantite().equals(uneLigne.getQUANTITERECUPERECLIENT())){
                //On affiche le message
                textInfo.setText("La commande a été récupéré.");
                editQuantiteLivre.setEnabled(false);
                editQuantiteRecup.setEnabled(false);
                valider.setVisibility(View.INVISIBLE);
            }
            else if(uneLigne.getQUANTITERECUPERECLIENT() >0){
                textInfo.setText("La commande a été récupéré partiellement.");
            }
            else{
                textInfo.setText("La commande n'a pas encore été récupéré.");
            }
        }

        JSONObject log = null;
        try {
            log = new JSONObject(getIntent().getStringExtra("log"));

            statut = log.getString("statut");
            //si il s'agit d'un client alors...
            if(log.getString("statut").equals("client")){
                Utilisateur unUtil = lesUtilisateurs.getUnUtilisateurByIDUTI(log.getString("idutilisateur"));
                editQuantiteRecup.setText(uneLigne.getQUANTITERECUPERECLIENT().toString());
                editQuantiteLivre.setText(uneLigne.getQUANTITELIVREECLIENT().toString());
            }
            //si il s'agit d'un producteur alors...
            else if(log.getString("statut").equals("producteur")){
                Producteur unProducteur =  lesProducteurs.getProducteurByIDUtilisateur(log.getString("idutilisateur"));
                editQuantiteRecup.setText(uneLigne.getQUANTITERECUPEREPRODUCTEUR().toString());
                editQuantiteLivre.setText(uneLigne.getQUANTITELIVREEPRODUCTEUR().toString());
            }
            //si il s'agit d'un responsable alors...
            else{
                editQuantiteLivre.setEnabled(false);
                editQuantiteRecup.setEnabled(false);
                editQuantiteRecup.setText(uneLigne.getQUANTITERECUPERECLIENT().toString());
                editQuantiteLivre.setText(uneLigne.getQUANTITELIVREEPRODUCTEUR().toString());
                if(!uneLigne.getVUERESPONSABLE()){
                    valider.setText("Valider les modifications");
                }
                else{
                    valider.setVisibility(View.INVISIBLE);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editQuantiteRecup = (EditText) findViewById(R.id.editQuantiteRecup);
                EditText editQuantiteLivre = (EditText) findViewById(R.id.editQuantiteLivre);
                if(statut.equals("client")){
                    uneLigne.setQUANTITERECUPERECLIENT(Double.parseDouble(editQuantiteRecup.getText().toString()));
                    uneLigne.setQUANTITELIVREECLIENT(Double.parseDouble(editQuantiteLivre.getText().toString()));
                    uneLigne.setVUERESPONSABLE(false);
                }
                else if(statut.equals("producteur")){
                    uneLigne.setQUANTITERECUPEREPRODUCTEUR(Double.parseDouble(editQuantiteRecup.getText().toString()));
                    uneLigne.setQUANTITELIVREEPRODUCTEUR(Double.parseDouble(editQuantiteLivre.getText().toString()));
                    uneLigne.setVUERESPONSABLE(false);
                }
                else{
                    uneLigne.setVUERESPONSABLE(true);
                }
                new ConsulterLaLigneCommandeActivity.BackTaskEnregistrer().execute();
            }
        });

    }

    private class BackTaskEnregistrer extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {

        }
        @Override
        protected Void doInBackground(Void... params){
            try {
                RequestBody formBody = new FormBody.Builder()
                        .add("idCommande",uneLigne.getUneCommande().getIdCommande())
                        .add("idProduit",uneLigne.getUnProduit().getIdProduit())
                        .add("QUANTITE",uneLigne.getQuantite().toString())
                        .add("QUANTITELIVREECLIENT",uneLigne.getQuantiteLivreClient().toString())
                        .add("QUANTITERECUPERECLIENT",uneLigne.getQUANTITERECUPERECLIENT().toString())
                        .add("QUANTITELIVREEPRODUCTEUR",uneLigne.getQUANTITELIVREEPRODUCTEUR().toString())
                        .add("QUANTITERECUPEREPRODUCTEUR",uneLigne.getQUANTITERECUPEREPRODUCTEUR().toString())
                        .add("VUERESPONSABLE",uneLigne.getVUERESPONSABLEChiffre())
                        .build();
                Request request = new Request.Builder()
                        .url("http://10.0.2.2/bio/updateLigneCommande.php")
                        .post(formBody)
                        .build();
                Response response = client.newCall(request).execute();
                responseStr = response.body().string();
            }
            catch (Exception e) {
                Log.d("Test", "Erreur de connexion !!!!");
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            if (responseStr.compareTo("false") != 0) {
                try {
                    JSONArray jsonArray = new JSONArray(responseStr);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Produit unProduit = new Produit(jsonObject.getString("IDPRODUIT"),lesProducteurs.getProducteurByID(jsonObject.getString("IDPRODUCTEUR")),lesCategories.getCategorieByID(jsonObject.getString("IDCATEGORIE")),jsonObject.getString("NOMPRODUIT"),jsonObject.getString("DESCRIPTIFPRODUIT"),jsonObject.getString("PHOTOPRODUIT"));
                        lesProduits.ajouterProduit(unProduit);
                    }
                } catch (JSONException e) {
                    Toast.makeText(ConsulterLaLigneCommandeActivity.this, "Erreur Produit!!!",
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(ConsulterLaLigneCommandeActivity.this, "Aucune Commande !", Toast.LENGTH_SHORT).show();
            }
        }

    }

}