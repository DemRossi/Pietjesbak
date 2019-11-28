package com.example.pietjesbak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView ma_DisplayPlayer1;
    private TextView ma_DisplayPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ma_DisplayPlayer1 = (TextView) findViewById(R.id.ma_textViewPlayer1);
        ma_DisplayPlayer2 = (TextView) findViewById(R.id.ma_textViewPlayer2);

//        Intent intentThatStartedMainActivity = getIntent();

        Bundle playerNames = getIntent().getExtras();

        if(playerNames != null){
//            String strPlayer1 = intentThatStartedMainActivity.getStringExtra(Intent.EXTRA_TEXT);
            String strPlayer1 = playerNames.getString("player1");
            String strPlayer2 = playerNames.getString("player2");
            ma_DisplayPlayer1.setText(strPlayer1);
            ma_DisplayPlayer2.setText(strPlayer2);
        }
    }
}
