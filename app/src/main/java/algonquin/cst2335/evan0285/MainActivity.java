package algonquin.cst2335.evan0285;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.w( "MainActivity", "In onCreate() - Loading Widgets" );

        Log.d( TAG, "Message");

        Button loginButton = findViewById(R.id.loginButton);
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String emailAddress = prefs.getString("LoginName", "");
        SharedPreferences.Editor editor = prefs.edit();

        editTextEmail.setText(emailAddress);




        loginButton.setOnClickListener( clk-> {
            Intent nextPage = new Intent( MainActivity.this, SecondActivity.class);

            nextPage.putExtra( "EmailAddress", editTextEmail.getText().toString() );

            editor.putString( "LoginName", editTextEmail.getText().toString());
            editor.apply();


            startActivity(nextPage);



        } );







    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.w("MainActivity", "onStart() - The application is now visible on screen.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w("MainActivity", "onStop() - The application is no longer visible.");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w("MainActivity", "onDestroy() - Any memory used by the application is freed.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w("MainActivity", "onPause()- The application no longer responds to user input");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w("MainActivity", "onResume() - The application is now responding to user input");
    }
}