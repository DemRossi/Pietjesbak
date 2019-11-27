package com.example.pietjesbak;

import androidx.appcompat.app.AppCompatActivity;
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
        setContentView(R.layout.activity_main);

        Button playBtn = findViewById(R.id.playBtn);
        Button exitBtn = findViewById(R.id.exitBtn);
        //Click listener for playing the game
            playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText setPlayer1 = findViewById(R.id.inputPlayer1);
                EditText setPlayer2 = findViewById(R.id.inputPlayer2);

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
                    Toast.makeText(LoginActivity.this, "Correct", Toast.LENGTH_SHORT)
                            .show();
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
