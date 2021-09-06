package co.edu.udea.compumovil.gr05_20211.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AddPoi extends AppCompatActivity {


    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_poi);

        preferences = this.getSharedPreferences("sessions", Context.MODE_PRIVATE);
        editor = preferences.edit();

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
}