package com.example.gfix.biorelai2;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

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
                JSONObject utilisateur = new JSONObject(getIntent().getStringExtra("log"));

                RequestBody formBody = new FormBody.Builder()
                        .add("dailyOnly","1")
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
                    ArrayList<HashMap<String,String>> lesCommandes = new  ArrayList<HashMap<String,String>>();
                    HashMap<String,String> item ;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        item = new HashMap<String,String>();
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        item.put("ligne1", "Commande n°"+jsonObject.getString("IDCOMMANDE"));
                        item.put("ligne2" , "FIX");
                        lesCommandes.add(item);
                    }

                    ListView listViewCommande = (ListView) findViewById(R.id.listViewCommandes);
                    SimpleAdapter adapter = new SimpleAdapter(ConsulterCommandeActivity.this, lesCommandes, android.R.layout.simple_list_item_2,
                            new String[]{"ligne1" , "ligne2"},new int[]{android.R.id.text1 , android.R.id.text2});

                    //ArrayAdapter<String> arrayAdapterCommandes= new ArrayAdapter<String>(ConsulterCommandeActivity.this,android.R.layout.simple_list_item_2,uneListe);
                    listViewCommande.setAdapter(adapter);
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