package co.edu.udea.compumovil.gr05_20211.lab2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HomeScreen extends AppCompatActivity {

    TextView tName;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    FloatingActionButton addPoi;
    private RecyclerView reciclador;
    private GridLayoutManager glm;
    private PoisAdapter adapter;

    List<PoiEntity> listOfPois = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        preferences = this.getSharedPreferences("sessions",Context.MODE_PRIVATE);
        editor = preferences.edit();

        tName = findViewById(R.id.email);
        String email = getIntent().getStringExtra("email");
        tName.setText(email);
        addPoi = findViewById(R.id.addPoi);
        onRestart();
        addPoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Toast.makeText(HomeScreen.this, "POI added", Toast.LENGTH_SHORT).show();
                Snackbar.make(v, "POI added", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show();*/
                startActivity(new Intent(
                        HomeScreen.this, AddPoi.class));

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
            Toast.makeText(HomeScreen.this, "Finalized Session", Toast.LENGTH_SHORT).show();
            //onBackPressed();
            Intent i = getBaseContext().getPackageManager()
                    .getLaunchIntentForPackage( getBaseContext().getPackageName() );
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);

        }
        if (id == R.id.settings){
            startActivity(new Intent(
                    HomeScreen.this, settingActivity.class));
        }
        return true;
    }

    @Override
    public void onRestart() {
        super.onRestart();
        consultPois();
    }

    public void printList(){
        Log.d("begin" , "----------------------------------------------");
        if (listOfPois.size() != 0){
            for (int i = 0 ; i < listOfPois.size() ; i++){
                Log.d("name" , listOfPois.get(i).getName());
                Log.d("desc" , listOfPois.get(i).getDescription());
                //Log.d("picture" , listOfPois.get(i).getPicture());
                Log.d("rating" , listOfPois.get(i).getRating().toString());
                Log.d("temperature" , listOfPois.get(i).getTemperature());
            }
        }
        Log.d("begin" , "----------------------------------------------");
        consultPois();
    }

    public void consultPois(){
        UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
        PoiDao poiDao = userDatabase.poiDao();
        listOfPois = new ArrayList<>();
        PoiEntity poi1 = new PoiEntity("Medellin","Eterna Primavera","https://cdn.forbes.co/2020/09/Medell%C3%ADn-foto-ProColombia.jpg",4.1f,"11","25");
        PoiEntity poi2 = new PoiEntity("Cali","Pachanguero","https://www.cali.gov.co/gobierno/info/principal/media/pubInt/thumbs/thpub_700x400_155052.jpg",4.1f,"11","25");
        PoiEntity poi3 = new PoiEntity("Bogota","Capital de la republica de colombia","https://upload.wikimedia.org/wikipedia/commons/2/24/Bogot%C3%A1_Colpatria_Night.jpg",4.1f,"11","25");
        PoiEntity poi4 = new PoiEntity("New York","Ciudad en la costa este de los estados unidos","https://www.aviatur.com/source/contenidos/blog/nueva-york-lugares-turisticos-capital-del-mundo.jpg",4.1f,"11","25");
        PoiEntity poi5 = new PoiEntity("Los Angeles","Ciudad de la costa oeste de los estados unidos","https://wp-growpro.s3-eu-west-1.amazonaws.com/media/2019/02/Que-ver-en-Los-Angeles.jpg",4.1f,"11","25");
        listOfPois.add(poi1);
        listOfPois.add(poi2);
        listOfPois.add(poi3);
        listOfPois.add(poi4);
        listOfPois.add(poi5);

        new Thread(new Runnable() {
            @Override
            public void run() {
                //Register POI
                List<PoiEntity> listDB = poiDao.getPois(preferences.getString("userId", ""));
                for (int i = 0 ; i < listDB.size() ; i++){
                    listOfPois.add(listDB.get(i));

                }
                Log.d("name" , "----------------------------------------------");
                if (listOfPois.size() != 0){
                    for (int i = 0 ; i < listOfPois.size() ; i++){

                        Log.d("name" , i+"");
                        Log.d("name" , listOfPois.get(i).getName());
                        Log.d("desc" , listOfPois.get(i).getDescription());
                        //Log.d("picture" , listOfPois.get(i).getPicture());
                        Log.d("rating" , listOfPois.get(i).getRating().toString());
                        Log.d("temperature" , listOfPois.get(i).getTemperature());
                    }
                }
                Log.d("name" , "----------------------------------------------");
            }
        }).start();
        createRecycler();
    }

    public void createRecycler(){
        reciclador = (RecyclerView) findViewById(R.id.reciclador);
        glm = new GridLayoutManager(this,2);
        reciclador.setLayoutManager(glm);
        adapter = new PoisAdapter(listOfPois,this);
        reciclador.setAdapter(adapter);

    }
}