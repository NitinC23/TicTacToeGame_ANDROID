package com.example.connect3;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //0 = yellow, 1 = red
    int activePlayer = 0;
    boolean gameIsActive = true;
    int[] gameState= {2,2,2,2,2,2,2,2,2};//2 means not played
    int [][] winningPosotions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropIn(View view){
        ImageView counter = (ImageView)view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter] == 2 && gameIsActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).setDuration(300);
            for(int[] winningPosotions:winningPosotions){
                if(gameState[winningPosotions[0]] == gameState[winningPosotions[1]]
                && gameState[winningPosotions[1]] == gameState[winningPosotions[2]]
                && gameState[winningPosotions[0]] != 2){
                    gameIsActive = false;
                    String winner = "Red";
                    if(gameState[winningPosotions[0]] == 0){
                        winner = "Yellow";
                    }
                    LinearLayout linearLayout = (LinearLayout)findViewById(R.id.playAgainLayout);
                    linearLayout.setVisibility(View.VISIBLE);
                    TextView winnerMessage = (TextView)findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner +" has won !");

                }else{
                    boolean gameIsOver = true;
                    for(int counterState : gameState){
                        if(counterState == 2){
                            gameIsOver = false;
                        }
                    }
                    if(gameIsOver){
                        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.playAgainLayout);
                        linearLayout.setVisibility(View.VISIBLE);
                        TextView winnerMessage = (TextView)findViewById(R.id.winnerMessage);
                        winnerMessage.setText("Its A Draw !");

                    }
                }
            }
        }
    }
    public void playItAgain(View view){
        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0;
        for(int i = 0; i < gameState.length ; i++){
            gameState[i] = 2;
        }
        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        for(int i = 0; i < gridLayout.getChildCount() ; i++){
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
