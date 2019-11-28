package com.example.pietjesbak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button playBtn = findViewById(R.id.la_playBtn);
        Button exitBtn = findViewById(R.id.la_exitBtn);
        //Click listener for playing the game
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText setPlayer1 = findViewById(R.id.la_inputPlayer1);
                EditText setPlayer2 = findViewById(R.id.la_inputPlayer2);

                String strPlayer1 = setPlayer1.getText().toString();
                String strPlayer2 = setPlayer2.getText().toString();

                //Check if one of the input fields are empty
                if( TextUtils.isEmpty(strPlayer1) || TextUtils.isEmpty(strPlayer2) ){
                    // if player1 isEmpty -> show error for player1
                    if ( TextUtils.isEmpty(strPlayer1) ){
                        setPlayer1.setError("cannot be empty");
                    }
                    // if player2 isEmpty -> show error for player2
                    if( TextUtils.isEmpty(strPlayer2) ){
                        setPlayer2.setError("cannot be empty");
                    }
                }else{
                    //make intent to next screen

                    Context context = LoginActivity.this;

                    //Store MainActivity.class in object
                    Class destinationActivity = MainActivity.class;
                    //Create Intent to start MainActivity
                    Intent startMainActivityIntent = new Intent(context, destinationActivity);

                    //Make bundle for player names
                    Bundle playerNames = new Bundle();
                    playerNames.putString("player1", strPlayer1);
                    playerNames.putString("player2", strPlayer2);

                    //putExtras for sending bundle playerNames with intent
                    startMainActivityIntent.putExtras(playerNames);
                    //Start the activity
                    //Toast.makeText(LoginActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                    startActivity(startMainActivityIntent);
                }

            }
        });
        //Click listener for quiting the game
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }
}
