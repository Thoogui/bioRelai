package com.example.gfix.biorelai2;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class menuActivity extends Activity {

    String responseStr;
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        new menuActivity.BackTaskProducteur().execute();



        final Button btnCommande = (Button) findViewById(R.id.btnCommande);
        btnCommande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    JSONObject utilisateur = new JSONObject(getIntent().getStringExtra("log"));
                    Intent intent = new Intent(menuActivity.this, ConsulterCommandeActivity.class);
                    intent.putExtra("log", utilisateur.toString());
                    startActivity(intent);
                }
                catch (JSONException e){
                    Toast.makeText(menuActivity.this,"Erreur !",Toast.LENGTH_SHORT).show();
                }
            }
        });
        try{
            JSONObject log = new JSONObject(getIntent().getStringExtra("log"));
            final TextView textBj = findViewById(R.id.txtBonjour);
            String identification = "Bonjour "+ log.getString("nomutilisateur")+", vous êtes le plus beau des " + log.getString("statut") +"s";
            textBj.setText(identification);
        }
        catch (JSONException e){
            Toast.makeText(menuActivity.this,"Erreur !",Toast.LENGTH_SHORT).show();
        }

    }


    private class BackTaskCategorie extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {

        }
        @Override
        protected Void doInBackground(Void... params){
            try {
                RequestBody formBody = new FormBody.Builder()
                        .build();
                Request request = new Request.Builder()
                        .url("http://10.0.2.2/bio/categorie.php")
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
                        Categorie uneCateg = new Categorie(jsonObject.getString("IDCATEGORIE"),jsonObject.getString("NOMCATEGORIE"));
                        lesCategories.ajouterCategorie(uneCateg);
                    }
                    new menuActivity.BackTaskCommande().execute();
                } catch (JSONException e) {
                    Toast.makeText(menuActivity.this, "Erreur !!!",
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(menuActivity.this, "Aucune Commande !", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private class BackTaskProducteur extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {

        }
        @Override
        protected Void doInBackground(Void... params){
            try {
                RequestBody formBody = new FormBody.Builder()
                        .build();
                Request request = new Request.Builder()
                        .url("http://10.0.2.2/bio/producteurs.php")
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
                        Producteur unProducteur = new Producteur(jsonObject.getString("idproducteur"),jsonObject.getString("IDUTILISATEUR"),jsonObject.getString("NOMUTILISATEUR"),jsonObject.getString("PRENOMUTILISATEUR"),jsonObject.getString("mail"),jsonObject.getString("ADRESSEPRODUCTEUR"),jsonObject.getString("COMMUNEPRODUCTEUR"),jsonObject.getString("CODEPOSTALPRODUCTEUR"),jsonObject.getString("DESCRIPTIFPRODUCTEUR"),jsonObject.getString("PHOTOPRODUCTEUR"));
                        lesProducteurs.ajouterProducteur(unProducteur);
                    }
                    new menuActivity.BackTaskCategorie().execute();
                    new menuActivity.BackTaskCommande().execute();
                } catch (JSONException e) {
                    Toast.makeText(menuActivity.this, "Erreur !!!",
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(menuActivity.this, "Aucune Commande !", Toast.LENGTH_SHORT).show();
            }
        }

    }


    private class BackTaskCommande extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {

        }
        @Override
        protected Void doInBackground(Void... params){
            try {
                RequestBody formBody = new FormBody.Builder()
                        .add("dailyOnly","1")
                        .build();
                Request request = new Request.Builder()
                        .url("http://10.0.2.2/bio/commandes.php")
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
                        Commande uneCommande = new Commande(jsonObject.getString("IDCOMMANDE"),jsonObject.getString("IDADHERENT"),jsonObject.getString("DATECOMMANDE"));
                        lesCommandes.ajouterCommande(uneCommande);
                    }
                } catch (JSONException e) {
                    Toast.makeText(menuActivity.this, "Erreur !!!",
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(menuActivity.this, "Aucune Commande !", Toast.LENGTH_SHORT).show();
            }
        }

    }
}