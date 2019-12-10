package com.example.pietjesbak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private TextView ma_DisplayPlayer1, ma_TextViewTotalPlayer1, ma_DisplayPlayer2, ma_TextViewTotalPlayer2;

    private ImageView ma_Dice1, ma_Dice2, ma_Dice3;

    private CheckBox ma_Checkbox_Dice1, ma_Checkbox_Dice2, ma_Checkbox_Dice3;

    private Button ma_RollDicesBtn, ma_StoefBtn,ma_ApeBtn,ma_SixNineBtn, ma_SandBtn, ma_SevenBtn;

    public static final Random RANDOM = new Random();

    Boolean Player1Turn = true;

    private int Total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ma_DisplayPlayer1 = (TextView) findViewById(R.id.ma_textViewPlayer1);
        ma_TextViewTotalPlayer1 = (TextView) findViewById(R.id.ma_textViewTotalPlayer1);
        ma_DisplayPlayer2 = (TextView) findViewById(R.id.ma_textViewPlayer2);
        ma_TextViewTotalPlayer2 = (TextView) findViewById(R.id.ma_textViewTotalPlayer2);

        ma_Dice1 = (ImageView) findViewById(R.id.ma_Dice1);
        ma_Dice2 = (ImageView) findViewById(R.id.ma_Dice2);
        ma_Dice3 = (ImageView) findViewById(R.id.ma_Dice3);

        ma_Checkbox_Dice1 = (CheckBox) findViewById(R.id.ma_checkbox_Dice1);
        ma_Checkbox_Dice2 = (CheckBox) findViewById(R.id.ma_checkbox_Dice2);
        ma_Checkbox_Dice3 = (CheckBox) findViewById(R.id.ma_checkbox_Dice3);

        ma_RollDicesBtn = findViewById(R.id.ma_rollBtn);
        ma_StoefBtn = findViewById(R.id.ma_stoefBtn);
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
                RollDice();
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

    public void RollDice(){
        int RanDiceVal1;
        int RanDiceVal2;
        int RanDiceVal3;

        for (int i = 0; i<=3; i++){

            // Check if dice is checked, if not give random value
            if(i == 1 && !ma_Checkbox_Dice1.isChecked()){
                RanDiceVal1 = randomDiceValue();

                // Select dice on random value
                int res1 = getResources().getIdentifier("dice_" + RanDiceVal1 ,
                        "drawable", "com.example.pietjesbak");

                // Draw dice
                ma_Dice1.setImageResource(res1);

            } else if( i == 2 && !ma_Checkbox_Dice2.isChecked()){
                RanDiceVal2 = randomDiceValue();

                // Select dice on random value
                int res2 = getResources().getIdentifier("dice_" + RanDiceVal2 ,
                        "drawable", "com.example.pietjesbak");

                // Draw dice
                ma_Dice2.setImageResource(res2);
            }else if (i == 3 && !ma_Checkbox_Dice3.isChecked()){
                RanDiceVal3 = randomDiceValue();

                // Select dice on random value
                int res3 = getResources().getIdentifier("dice_" + RanDiceVal3 ,
                        "drawable", "com.example.pietjesbak");

                // Draw dice
                ma_Dice3.setImageResource(res3);
            }
        }
    }
    public static int randomDiceValue(){
        return RANDOM.nextInt(6) +1;
    }
}
