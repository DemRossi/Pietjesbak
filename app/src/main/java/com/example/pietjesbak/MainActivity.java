package com.example.pietjesbak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private TextView ma_DisplayPlayer1;
    private TextView ma_DisplayPlayer2;
    private Button ma_RollDicesBtn,ma_ApeBtn,ma_SixNineBtn, ma_SandBtn, ma_SevenBtn;
    private ImageView ma_Dice1, ma_Dice2, ma_Dice3;
    public static final Random RANDOM = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ma_DisplayPlayer1 = (TextView) findViewById(R.id.ma_textViewPlayer1);
        ma_DisplayPlayer2 = (TextView) findViewById(R.id.ma_textViewPlayer2);

        ma_Dice1 = (ImageView) findViewById(R.id.ma_Dice1);
        ma_Dice2 = (ImageView) findViewById(R.id.ma_Dice2);
        ma_Dice3 = (ImageView) findViewById(R.id.ma_Dice3);

        ma_RollDicesBtn = findViewById(R.id.ma_rollBtn);
        ma_ApeBtn = findViewById(R.id.ma_apeBtn);
        ma_SixNineBtn = findViewById(R.id.ma_sixNineBtn);
        ma_SandBtn = findViewById(R.id.ma_sandBtn);
        ma_SevenBtn = findViewById(R.id.ma_sevenBtn);

        Bundle playerNames = getIntent().getExtras();

        if(playerNames != null){
//            String strPlayer1 = intentThatStartedMainActivity.getStringExtra(Intent.EXTRA_TEXT);
            String strPlayer1 = playerNames.getString("player1");
            String strPlayer2 = playerNames.getString("player2");
            ma_DisplayPlayer1.setText(strPlayer1);
            ma_DisplayPlayer2.setText(strPlayer2);
        }

        ma_RollDicesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Take random value
                int RanDiceVal1 = randomDiceValue();
                int RanDiceVal2 = randomDiceValue();
                int RanDiceVal3 = randomDiceValue();

                // Select dice on random value
                int res1 = getResources().getIdentifier("dice_" + RanDiceVal1 ,
                        "drawable", "com.example.pietjesbak");
                int res2 = getResources().getIdentifier("dice_" + RanDiceVal2 ,
                        "drawable", "com.example.pietjesbak");
                int res3 = getResources().getIdentifier("dice_" + RanDiceVal3 ,
                        "drawable", "com.example.pietjesbak");

                // Draw dice
                ma_Dice1.setImageResource(res1);
                ma_Dice2.setImageResource(res2);
                ma_Dice3.setImageResource(res3);
            }
        });


//        START test buttons
        ma_ApeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int RanDiceVal1 = 1;
                int RanDiceVal2 = 1;
                int RanDiceVal3 = 1;

                int res1 = getResources().getIdentifier("dice_" + RanDiceVal1 ,
                        "drawable", "com.example.pietjesbak");
                int res2 = getResources().getIdentifier("dice_" + RanDiceVal2 ,
                        "drawable", "com.example.pietjesbak");
                int res3 = getResources().getIdentifier("dice_" + RanDiceVal3 ,
                        "drawable", "com.example.pietjesbak");

                ma_Dice1.setImageResource(res1);
                ma_Dice2.setImageResource(res2);
                ma_Dice3.setImageResource(res3);
            }
        });
        ma_SixNineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int RanDiceVal1 = 6;
                int RanDiceVal2 = 5;
                int RanDiceVal3 = 4;

                int res1 = getResources().getIdentifier("dice_" + RanDiceVal1 ,
                        "drawable", "com.example.pietjesbak");
                int res2 = getResources().getIdentifier("dice_" + RanDiceVal2 ,
                        "drawable", "com.example.pietjesbak");
                int res3 = getResources().getIdentifier("dice_" + RanDiceVal3 ,
                        "drawable", "com.example.pietjesbak");

                ma_Dice1.setImageResource(res1);
                ma_Dice2.setImageResource(res2);
                ma_Dice3.setImageResource(res3);
            }
        });
        ma_SandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int RanDiceVal1 = randomDiceValue();

                int res1 = getResources().getIdentifier("dice_" + RanDiceVal1 ,
                        "drawable", "com.example.pietjesbak");
                int res2 = getResources().getIdentifier("dice_" + RanDiceVal1 ,
                        "drawable", "com.example.pietjesbak");
                int res3 = getResources().getIdentifier("dice_" + RanDiceVal1 ,
                        "drawable", "com.example.pietjesbak");

                ma_Dice1.setImageResource(res1);
                ma_Dice2.setImageResource(res2);
                ma_Dice3.setImageResource(res3);
            }
        });
        ma_SevenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int RanDiceVal1 = 2;
                int RanDiceVal2 = 2;
                int RanDiceVal3 = 3;

                int res1 = getResources().getIdentifier("dice_" + RanDiceVal1 ,
                        "drawable", "com.example.pietjesbak");
                int res2 = getResources().getIdentifier("dice_" + RanDiceVal2 ,
                        "drawable", "com.example.pietjesbak");
                int res3 = getResources().getIdentifier("dice_" + RanDiceVal3 ,
                        "drawable", "com.example.pietjesbak");

                ma_Dice1.setImageResource(res1);
                ma_Dice2.setImageResource(res2);
                ma_Dice3.setImageResource(res3);
            }
        });
//      END test buttons
    }

    public static int randomDiceValue(){
        return RANDOM.nextInt(6) +1;
    }
}
