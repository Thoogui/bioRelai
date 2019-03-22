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

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends Activity {
    String responseStr;
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button buttonQuitter = (Button) findViewById(R.id.quitter);
        buttonQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final Button buttonConnection = (Button) findViewById(R.id.connecter);
        buttonConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BackTaskAuthentification().execute();

            }
        });
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
                        .add("login", String.valueOf(textLogin))
                        .add("mdp", String.valueOf(textMdp))
                        .build();
                Request request = new Request.Builder()
                        .url("http://192.168.56.1/mesProjetsPHP/etudiantsAndroid/authentification.php")
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
                    JSONObject log = new JSONObject(responseStr);
                    String nb = log.getString("nbrInt");
                    if(nb.equals('1')){
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        intent.putExtra("nbrInt", log.toString());
                        startActivity(intent);
                    }else{

                    }
                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this, "Erreur de connexion !!!!!",
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Login ou mot de passe non valide !", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
