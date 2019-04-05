package com.example.gfix.biorelai2;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ConsulterCommandeActivity extends Activity {

    String responseStr;
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_commande);
        new ConsulterCommandeActivity.BackTaskAuthentification().execute();

    }



    private class BackTaskAuthentification extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {

        }
        @Override
        protected Void doInBackground(Void... params){
            try {
                final EditText textLogin = findViewById(R.id.login);
                final EditText textMdp = findViewById(R.id.mdp);
                RequestBody formBody = new FormBody.Builder()
                        .build();
                Request request = new Request.Builder()
                        .url("http://10.100.0.5/~fixg/bioRelaiAndroid/commandes.php")
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
                    String[] uneListe = new String[10];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        uneListe[i]= jsonObject.getString("IDCOMMANDE");

;
                    }

                    ListView listViewCommande = (ListView) findViewById(R.id.listViewCommandes);
                    //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_item_commandes, uneListe);

                    //listViewCommande.setAdapter(arrayAdapter);
                } catch (JSONException e) {
                    Toast.makeText(ConsulterCommandeActivity.this, "Erreur !!!",
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(ConsulterCommandeActivity.this, "Aucune Commande !", Toast.LENGTH_SHORT).show();
            }
        }

    }
}