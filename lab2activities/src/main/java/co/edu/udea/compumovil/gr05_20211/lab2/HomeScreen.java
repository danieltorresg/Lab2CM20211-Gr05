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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class HomeScreen extends AppCompatActivity {

    TextView tName;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    FloatingActionButton addPoi;

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
        return true;
    }

    @Override
    public void onRestart() {
        super.onRestart();
        //When BACK BUTTON is pressed, the activity on the stack is restarted
        //Do what you want on the refresh procedure here

        consultPois();


    }

    public void consultPois(){
        UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
        PoiDao poiDao = userDatabase.poiDao();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //Register POI
                listOfPois = poiDao.getPois(preferences.getString("userId", ""));
                Log.d("name" , "----------------------------------------------");
                if (listOfPois.size() != 0){
                    for (int i = 0 ; i < listOfPois.size() ; i++){
                        Log.d("name" , listOfPois.get(i).getName());
                        Log.d("desc" , listOfPois.get(i).getDescription());
                        Log.d("picture" , listOfPois.get(i).getPicture());
                        Log.d("rating" , listOfPois.get(i).getRating().toString());
                        Log.d("temperature" , listOfPois.get(i).getTemperature());
                    }
                }
                Log.d("name" , "----------------------------------------------");
            }
        }).start();

    }
}