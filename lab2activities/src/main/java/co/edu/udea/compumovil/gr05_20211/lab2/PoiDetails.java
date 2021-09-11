package co.edu.udea.compumovil.gr05_20211.lab2;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PoiDetails extends AppCompatActivity {

    TextView name,description,temperature,rating;
    Button buttonBack;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_details);
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        temperature = findViewById(R.id.temperature);
        rating = findViewById(R.id.rating);
        buttonBack = findViewById(R.id.volver);
        name.setText(getIntent().getStringExtra("name"));
        description.setText(getIntent().getStringExtra("description"));
        temperature.setText(getIntent().getStringExtra("temperature"));
        rating.setText(getIntent().getStringExtra("rating"));
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
