package co.edu.udea.compumovil.gr05_20211.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class AddPoi extends AppCompatActivity {


    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    EditText name, description, picture, rating, temperature;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_poi);

        preferences = this.getSharedPreferences("sessions", Context.MODE_PRIVATE);
        editor = preferences.edit();

        name = findViewById(R.id.namePOI);
        description = findViewById(R.id.descriptionPOI);
        picture = findViewById(R.id.picturePOI);
        rating = findViewById(R.id.ratingPOI);
        temperature = findViewById(R.id.temperaturePOI);
        add = findViewById(R.id.btnAddPoi);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PoiEntity poiEntity = new PoiEntity();
                poiEntity.setName(name.getText().toString());
                poiEntity.setDescription(description.getText().toString());
                poiEntity.setPicture(picture.getText().toString());
                poiEntity.setTemperature(temperature.getText().toString());
                float ratingText = 0.0f;
                if (!rating.getText().toString().isEmpty()){
                    ratingText = Float.parseFloat(rating.getText().toString());
                }
                poiEntity.setRating(ratingText);
                poiEntity.setUserId(preferences.getString("userId", ""));

                if (validateInput(poiEntity)){
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    PoiDao poiDao = userDatabase.poiDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //Register POI
                            poiDao.registerPoi(poiEntity);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "POI Registered", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).start();

                }else {
                    Toast.makeText(getApplicationContext(), "Fill all fields", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem option){
        int id = option.getItemId();

        if (id == R.id.logoutMenu){
            editor.putBoolean("session", false);
            editor.apply();
            Toast.makeText(AddPoi.this, "Finalized Session", Toast.LENGTH_SHORT).show();
            //onBackPressed();
            Intent i = getBaseContext().getPackageManager()
                    .getLaunchIntentForPackage( getBaseContext().getPackageName() );
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
        return true;
    }

    private Boolean validateInput(PoiEntity poiEntity){
        if (poiEntity.getName().isEmpty() ||
                poiEntity.getDescription().isEmpty() ||
                poiEntity.getPicture().isEmpty()||
                poiEntity.getTemperature().isEmpty()){
            return false;
        }
        return true;
    }
}