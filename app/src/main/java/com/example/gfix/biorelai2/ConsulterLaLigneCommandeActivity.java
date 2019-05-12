package com.example.gfix.biorelai2;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ligne_commande);
        uneLigne = lesLignesCommandes.getLigneCommandes(lesCommandes.getUneCommandeByID(getIntent().getStringExtra("idCommande")),lesProduits.getUnProduitByID(getIntent().getStringExtra("idProduit")));
        TextView textProduit = (TextView) findViewById(R.id.textProduit);
        textProduit.setText(uneLigne.getUnProduit().getNomProduit());

        TextView textQuantite = (TextView) findViewById(R.id.textQuantite);
        textQuantite.setText(textQuantite.getText() + " : "+uneLigne.getQuantite() +"g");

        ImageView uneImage = (ImageView) findViewById(R.id.imageProduit);
        Picasso.with(this).load(uneLigne.getUnProduit().getPhotoProduit()).into(uneImage);
        TextView textInfo = (TextView) findViewById(R.id.textInfo);
        if(getIntent().getBooleanExtra("ancienne",false)){
            if(uneLigne.getQUANTITELIVREEPRODUCTEUR() == uneLigne.getQUANTITERECUPERECLIENT()){
                textInfo.setText("La commande a été récupéré.");
            }
            else if(uneLigne.getQUANTITERECUPERECLIENT() >0){
                textInfo.setText("La commande a été récupéré partiellement.");
            }else{
                if(uneLigne.getQUANTITELIVREEPRODUCTEUR() == 0){
                    textInfo.setText("Le producteur "+uneLigne.getUnProduit().getUnProducteur().getNomProducteur()+" a été absent lors de cette commande");
                }else{
                    textInfo.setText("Le client "+uneLigne.getUneCommande().getAdherent().getNOMUTILISATEUR()+" a été absent lors de cette commande");
                }

            }
            TextView textQuantiteFinal = (TextView) findViewById(R.id.textQuantiteFinal);
            textQuantiteFinal.setVisibility(View.INVISIBLE);
            EditText editQuantite = (EditText) findViewById(R.id.editQuantiteFinal);
            editQuantite.setVisibility(View.INVISIBLE);
            Button valider = (Button) findViewById(R.id.btnAction);
            valider.setVisibility(View.INVISIBLE);



        }else{
            if(uneLigne.getQUANTITELIVREEPRODUCTEUR() == uneLigne.getQUANTITERECUPERECLIENT() && uneLigne.getQuantite() == uneLigne.getQUANTITERECUPERECLIENT()){
                textInfo.setText("La commande a été récupéré.");
                TextView textQuantiteFinal = (TextView) findViewById(R.id.textQuantiteFinal);
                textQuantiteFinal.setVisibility(View.INVISIBLE);
                EditText editQuantite = (EditText) findViewById(R.id.editQuantiteFinal);
                editQuantite.setVisibility(View.INVISIBLE);
                Button valider = (Button) findViewById(R.id.btnAction);
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

    private class BackTaskProduit extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {

        }
        @Override
        protected Void doInBackground(Void... params){
            try {
                RequestBody formBody = new FormBody.Builder()
                        .add("idCommande",uneLigne.getUneCommande().getIdCommande())
                        .add("idProduit",uneLigne.getUnProduit().getIdProduit())
                        .add("quantiteClient",uneLigne.getQUANTITERECUPERECLIENT().toString())
                        .add("QUANTITELIVREEPRODUCTEUR",uneLigne.getQUANTITELIVREEPRODUCTEUR().toString())
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