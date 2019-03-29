package com.example.gfix.biorelai2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class menuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

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
}