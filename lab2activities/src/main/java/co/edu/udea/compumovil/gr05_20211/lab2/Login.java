package co.edu.udea.compumovil.gr05_20211.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText userId, password;
    Button login, register;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences = this.getSharedPreferences("sessions",Context.MODE_PRIVATE);
        editor = preferences.edit();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userId = findViewById(R.id.userId);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        if (checkSession()){
            String email = this.preferences.getString("email", "");
            startActivity(new Intent(
                    Login.this, HomeScreen.class)
                    .putExtra("email", email));
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userIdText = userId.getText().toString();
                final String passwordText = password.getText().toString();
                if (userIdText.isEmpty() || passwordText.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Fill all fields", Toast.LENGTH_SHORT).show();

                }else {
                    //Perfomr Query
                    UserDatabase userDatabase = UserDatabase.getUserDatabase((getApplicationContext()));
                    final UserDao userDao = userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            UserEntity userEntity = userDao.login(userIdText, passwordText);
                            if (userEntity == null){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                String email = userEntity.email;
                                String userId = userEntity.userId;
                                saveSession(email, userId);
                                startActivity(new Intent(
                                    Login.this, HomeScreen.class)
                                    .putExtra("email", email));

                            }
                        }
                    }).start();
                }

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        Login.this, Register.class));

            }
        });
    }

    private boolean checkSession(){
        return this.preferences.getBoolean("session", false);
    }

    private void saveSession(String email, String userId){
        editor.putBoolean("session", true);
        editor.putString("email", email);
        editor.putString("userId", userId);
        editor.apply();

    }
}