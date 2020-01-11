package com.example.pietjesbak;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    private TextView ma_DisplayPlayer1, ma_TextViewTotalPlayer1, ma_DisplayPlayer2, ma_TextViewTotalPlayer2,
            ma_textViewLinesPlayer1, ma_textViewLinesPlayer2;

    private ImageView ma_Dice1, ma_Dice2, ma_Dice3;

    private CheckBox ma_Checkbox_Dice1, ma_Checkbox_Dice2, ma_Checkbox_Dice3;

    private Button ma_RollDicesBtn, ma_StoefBtn,ma_ApeBtn,ma_SixNineBtn, ma_SandBtn, ma_SevenBtn;

    public static final Random RANDOM = new Random();

    Boolean Player1Turn = true, Player1Stoef = false, Player2Stoef = false;

    boolean checker69[] = {false, false, false};

    int ScoreTotal = 0, AmountRolls = 3, LinesPlayer1 = 9, LinesPlayer2 = 9  , RanDiceVal1,
            RanDiceVal2, RanDiceVal3, Player1SubTot, Player2SubTot ;

    String ScoreTotalText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ma_DisplayPlayer1 = findViewById(R.id.ma_textViewPlayer1);
        ma_TextViewTotalPlayer1 = findViewById(R.id.ma_textViewTotalPlayer1);
        ma_DisplayPlayer2 = findViewById(R.id.ma_textViewPlayer2);
        ma_TextViewTotalPlayer2 = findViewById(R.id.ma_textViewTotalPlayer2);
        ma_textViewLinesPlayer1 = findViewById(R.id.ma_textViewLinesPlayer1);
        ma_textViewLinesPlayer2 = findViewById(R.id.ma_textViewLinesPlayer2);

        ma_Dice1 = findViewById(R.id.ma_Dice1);
        ma_Dice2 = findViewById(R.id.ma_Dice2);
        ma_Dice3 = findViewById(R.id.ma_Dice3);

        ma_Checkbox_Dice1 = findViewById(R.id.ma_checkbox_Dice1);
        ma_Checkbox_Dice2 = findViewById(R.id.ma_checkbox_Dice2);
        ma_Checkbox_Dice3 = findViewById(R.id.ma_checkbox_Dice3);

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
            ma_DisplayPlayer1.setTextColor(getResources().getColor(R.color.colorActivePlayer));
            ma_DisplayPlayer2.setText(strPlayer2);
        }

        ma_RollDicesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // See if you can throw all dices on first throw
                if(AmountRolls == 3 && (ma_Checkbox_Dice1.isChecked() || ma_Checkbox_Dice2.isChecked()
                || ma_Checkbox_Dice3.isChecked())){
                    Toast.makeText(MainActivity.this, "You need to throw all the dices!",
                            Toast.LENGTH_SHORT).show();
                } else{
                    AmountRolls -= 1;
                    RollDice();
                    calcScore();
                    //TODO: calculate and print the score -> function?
                    //TODO: how about RollDice for test buttons?
                }
                RollCheck();
            }
        });
        ma_StoefBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Player1Turn == true){
                    Player1Stoef = true;
                    AmountRolls = 0;
                    RollCheck();
                }else{
                    Player2Stoef = true;
                    AmountRolls = 0;
                    RollCheck();
                }
            }
        });
        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
                /*
                 * The following method, "handleShakeEvent(count):" is a stub //
                 * method you would use to setup whatever you want done once the
                 * device has been shook.
                 */
//                handleShakeEvent(count);
                // See if you can throw all dices on first throw
                if(AmountRolls == 3 && (ma_Checkbox_Dice1.isChecked() || ma_Checkbox_Dice2.isChecked()
                        || ma_Checkbox_Dice3.isChecked())){
                    Toast.makeText(MainActivity.this, "You need to throw all the dices!",
                            Toast.LENGTH_SHORT).show();
                } else{
                    AmountRolls -= 1;
                    RollDice();
                    calcScore();
                    //TODO: calculate and print the score -> function?
                    //TODO: how about RollDice for test buttons?
                }
                RollCheck();
            }
        });
//        START test buttons
        ma_ApeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AmountRolls -= 1;
                RollDice(1,1,1);
                calcScore();
                RollCheck();
            }
        });
        ma_SixNineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RollDice(6,5,4);
                AmountRolls -= 1;
                calcScore();
                RollCheck();
            }
        });
        ma_SandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int RanDiceVal = randomDiceValue();

                if (RanDiceVal != 1){
                    RollDice(RanDiceVal,RanDiceVal,RanDiceVal);
                    calcScore();
                }else{
                    RanDiceVal = randomDiceValue();
                    RollDice(RanDiceVal,RanDiceVal,RanDiceVal);

                }
                AmountRolls -= 1;
                calcScore();
                RollCheck();
            }
        });
        ma_SevenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RollDice(2,2,3);
                AmountRolls -= 1;
                calcScore();
                RollCheck();
            }
        });
//      END test buttons
    }

    public void RollDice(){

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
    public void RollDice(int _RanDiceVal1,int _RanDiceVal2,int _RanDiceVal3){


        for (int i = 0; i<=3; i++){

            // Check if dice is checked, if not give random value
            if(i == 1 && !ma_Checkbox_Dice1.isChecked()){
                // Select dice on random value
                int res1 = getResources().getIdentifier("dice_" + _RanDiceVal1 ,
                        "drawable", "com.example.pietjesbak");

                // Draw dice
                ma_Dice1.setImageResource(res1);
                RanDiceVal1 = _RanDiceVal1;

            } else if( i == 2 && !ma_Checkbox_Dice2.isChecked()){
                // Select dice on random value
                int res2 = getResources().getIdentifier("dice_" + _RanDiceVal2 ,
                        "drawable", "com.example.pietjesbak");

                // Draw dice
                ma_Dice2.setImageResource(res2);
                RanDiceVal2 = _RanDiceVal2;
            }else if (i == 3 && !ma_Checkbox_Dice3.isChecked()){
                // Select dice on random value
                int res3 = getResources().getIdentifier("dice_" + _RanDiceVal3 ,
                        "drawable", "com.example.pietjesbak");

                // Draw dice
                ma_Dice3.setImageResource(res3);
                RanDiceVal3 = _RanDiceVal3;
            }
        }
    }
    public static int randomDiceValue(){
        return RANDOM.nextInt(6) +1;
    }
    public void RollCheck (){
        if( AmountRolls == 0){

            //uncheck all boxes for next player
            ma_Checkbox_Dice1.setChecked(false);
            ma_Checkbox_Dice2.setChecked(false);
            ma_Checkbox_Dice3.setChecked(false);

            //change player
            if(Player1Turn == true){
                ma_DisplayPlayer1.setTextColor(getResources().getColor(R.color.colorNeutralLight));
                ma_DisplayPlayer2.setTextColor(getResources().getColor(R.color.colorActivePlayer));
                Player1Turn = false;
                AmountRolls = 3;
            }else{
                ma_DisplayPlayer1.setTextColor(getResources().getColor(R.color.colorActivePlayer));
                ma_DisplayPlayer2.setTextColor(getResources().getColor(R.color.colorNeutralLight));
                Player1Turn = true;
                AmountRolls = 3;
                //TODO: compare score -> function?
                CompareScore();
            }
        }
    }
    public void calcScore(){
//        Check for sand
        if (RanDiceVal1 == RanDiceVal2 && RanDiceVal1 == RanDiceVal3){
            switch(RanDiceVal1){
                case 1:
                    // 3 Apes (111)
                    ScoreTotal = 799;
                    ScoreTotalText = "3 Apes (1-1-1)";
                    break;
                case 2:
                    // Sand (222)
                    ScoreTotal = 722;
                    ScoreTotalText = "Sand (2-2-2)";
                    break;
                case 3:
                    // Sand (333)
                    ScoreTotal = 733;
                    ScoreTotalText = "Sand (3-3-3)";
                    break;
                case 4:
                    // Sand (444)
                    ScoreTotal = 744;
                    ScoreTotalText = "Sand (4-4-4)";
                    break;
                case 5:
                    // Sand (555)
                    ScoreTotal = 755;
                    ScoreTotalText = "Sand (5-5-5)";
                    break;
                case 6:
                    // Sand (666)
                    ScoreTotal = 766;
                    ScoreTotalText = "Sand (6-6-6)";
                    break;
            }
            PrintScore();
        }else if(ScoreTotal == 0) {
            if (RanDiceVal1 == 4 || RanDiceVal2 == 4 || RanDiceVal3 == 4) {
                checker69[0] = true;
            }
            if (RanDiceVal1 == 5 || RanDiceVal2 == 5 || RanDiceVal3 == 5) {
                checker69[1] = true;
            }
            if (RanDiceVal1 == 6 || RanDiceVal2 == 6 || RanDiceVal3 == 6) {
                checker69[2] = true;
            }
            if (checker69[0] == true && checker69[1] == true && checker69[2] == true) {
                // Soixante-neuf is trown
                ScoreTotal = 769;
                ScoreTotalText = "Soixante-Neuf (6-5-4)";
                PrintScore();
            } else {
                switch (RanDiceVal1) {
                    case 1:
                        ScoreTotal += 100;
                        break;
                    case 2:
                        ScoreTotal += 2;
                        break;
                    case 3:
                        ScoreTotal += 3;
                        break;
                    case 4:
                        ScoreTotal += 4;
                        break;
                    case 5:
                        ScoreTotal += 5;
                        break;
                    case 6:
                        ScoreTotal += 60;
                        break;
                }
                switch (RanDiceVal2) {
                    case 1:
                        ScoreTotal += 100;
                        break;
                    case 2:
                        ScoreTotal += 2;
                        break;
                    case 3:
                        ScoreTotal += 3;
                        break;
                    case 4:
                        ScoreTotal += 4;
                        break;
                    case 5:
                        ScoreTotal += 5;
                        break;
                    case 6:
                        ScoreTotal += 60;
                        break;
                }
                switch (RanDiceVal3) {
                    case 1:
                        ScoreTotal += 100;
                        break;
                    case 2:
                        ScoreTotal += 2;
                        break;
                    case 3:
                        ScoreTotal += 3;
                        break;
                    case 4:
                        ScoreTotal += 4;
                        break;
                    case 5:
                        ScoreTotal += 5;
                        break;
                    case 6:
                        ScoreTotal += 60;
                        break;
                }
                // Rules for trowing 7
                if (ScoreTotal == 7) {
                    // End of round a 7, lines of players +1
                    if(AmountRolls == 0){
                        LinesPlayer1 += 1;
                        LinesPlayer2 += 1;

                        ma_textViewLinesPlayer1.setText("Lines: " + LinesPlayer1);
                        ma_textViewLinesPlayer2.setText("Lines: " + LinesPlayer2);
                    }

                }
                ScoreTotalText = String.valueOf(ScoreTotal);
                PrintScore();
            }
            // Reset array
            Arrays.fill(checker69, false);
        }
    }
    public void PrintScore(){
        if (Player1Turn == true){
            ma_TextViewTotalPlayer1.setText("Total: " + ScoreTotalText);
            Player1SubTot = ScoreTotal;
            ScoreTotal = 0;
        }else{
            ma_TextViewTotalPlayer2.setText("Total: " + ScoreTotalText);
            Player2SubTot = ScoreTotal;
            ScoreTotal = 0;
        }
    }
    public void CompareScore(){
        if(Player1SubTot == Player2SubTot){
            // DRAW
            AmountRolls = 3;
            Toast.makeText(MainActivity.this, "DRAW", Toast.LENGTH_SHORT).show();
        }else if (Player1SubTot > Player2SubTot){
            // player 1 wins
            if(Player1SubTot == 722 || Player1SubTot == 733 || Player1SubTot == 744 ||
                    Player1SubTot == 755 || Player1SubTot == 766){
                // Winning with Sand
                LinesPlayer1 -= 2;
            }else if (Player1SubTot == 769){
                // Winning with 69
                LinesPlayer1 -= 3;
            }else if(Player1SubTot == 799){
                // Winning with apes
                if(LinesPlayer1 >= 9){
                    // When lines are over 9 -> other person should win
                }else{
                    LinesPlayer1 = 0;
                }
            }else{
                if (Player1Stoef == true) {
                    LinesPlayer1 -= 2;
                    // Change player stoef back to false
                    Player1Stoef = false;
                }else{
                    LinesPlayer1 -= 1;
                }
            }
            // Check lines for winner
            if(LinesPlayer1 > 0){
                ma_textViewLinesPlayer1.setText("Lines: " + LinesPlayer1);
//                Toast.makeText(MainActivity.this, ma_DisplayPlayer1.getText() + " wins this round! " + LinesPlayer1 + " more lines", Toast.LENGTH_SHORT).show();
                RoundWinnerAlert((String) ma_DisplayPlayer1.getText(), LinesPlayer1);
                ma_TextViewTotalPlayer1.setText("Total: 0");
                ma_TextViewTotalPlayer2.setText("Total: 0");
            }else{
//                Toast.makeText(MainActivity.this, ma_DisplayPlayer1.getText() + " wins the GAME! ", Toast.LENGTH_SHORT).show();
                GameWinnerAlert((String) ma_DisplayPlayer1.getText());
            }


        }else if(Player1SubTot < Player2SubTot){
            // player 2 wins
            if(Player2SubTot == 722 || Player2SubTot == 733 || Player2SubTot == 744 ||
                    Player2SubTot == 755 || Player2SubTot == 766){
                // Winning with Sand
                LinesPlayer2 -= 2;
            }else if (Player2SubTot == 769){
                // Winning with 69
                LinesPlayer2 -= 3;
            }else if(Player2SubTot == 799){
                // Winning with apes
                if(LinesPlayer2 >= 9){
                    // When lines are over 9 -> other person should win
                }else{
                    LinesPlayer2 = 0;
                }
            }else{
                if (Player2Stoef == true) {
                    LinesPlayer2 -= 2;
                    // Change player stoef back to false
                    Player2Stoef = false;
                }else{
                    LinesPlayer2 -= 1;
                }
            }
            // Check lines for winner
            if(LinesPlayer2 > 0){
                ma_textViewLinesPlayer2.setText("Lines: " + LinesPlayer2);
//                Toast.makeText(MainActivity.this, ma_DisplayPlayer2.getText() + " wins this round! " + LinesPlayer2 + " more lines", Toast.LENGTH_SHORT).show();
                RoundWinnerAlert((String) ma_DisplayPlayer2.getText(), LinesPlayer2);
                ma_TextViewTotalPlayer1.setText("Total: 0");
                ma_TextViewTotalPlayer2.setText("Total: 0");
            }else{
//                Toast.makeText(MainActivity.this, ma_DisplayPlayer2.getText() + " wins the GAME! ", Toast.LENGTH_SHORT).show();
                GameWinnerAlert((String) ma_DisplayPlayer2.getText());
            }
        }
    }
    public void RoundWinnerAlert(String roundWinner, int linesPlayers){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(roundWinner + " has won this round.");
        builder.setMessage(roundWinner +" has " + linesPlayers + " lines left.");
//        builder.setPositiveButton("Ok", null);
        builder.show();
    }
    public void GameWinnerAlert(final String gameWinner){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle( "🎉 " + gameWinner + " has won this game!!! 🎉 ");
        builder.setMessage("Winner Winner Chicken Dinner!!!");
        builder.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Restart();
            }
        });
        builder.setNegativeButton("Quit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                System.exit(0);
            }
        });
        builder.setNeutralButton("Share", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String message = gameWinner + " has won a game of Pietjesbak";
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT, message);

                startActivity(Intent.createChooser(share, "Title of the dialog the system will open"));
                Restart();
//                SharingToSocialMedia("com.facebook.katana");
            }
        });
        builder.show();
    }
    public void Restart(){
        LinesPlayer1 = 9;
        LinesPlayer2 = 9;
        ScoreTotal = 0;
        Player1SubTot = 0;
        Player2SubTot = 0;
        AmountRolls = 3;
        Player1Turn = true;
        ma_TextViewTotalPlayer1.setText("Total: 0");
        ma_TextViewTotalPlayer2.setText("Total: 0");
        ma_textViewLinesPlayer1.setText("Lines: " + LinesPlayer1);
        ma_textViewLinesPlayer2.setText("Lines: " + LinesPlayer2);
    }
//    public void SharingToSocialMedia(String application) {
//
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_SEND);
//        intent.setType("image/*");
////        intent.putExtra(Intent.EXTRA_STREAM, bmpUri);
//
//        boolean installed = checkAppInstall(application);
//        if (installed) {
//            intent.setPackage(application);
//            startActivity(intent);
//        } else {
//            Toast.makeText(getApplicationContext(),
//                    "Installed application first", Toast.LENGTH_LONG).show();
//        }
//    }
//    private boolean checkAppInstall(String uri) {
//        PackageManager pm = getPackageManager();
//        try {
//            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
//            return true;
//        } catch (PackageManager.NameNotFoundException e) {
//        }
//        return false;
//    }
    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }
}
