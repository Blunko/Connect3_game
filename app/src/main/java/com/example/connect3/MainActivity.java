package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int couleurJeton; /// french
    int[] gstate = new int[9];
    boolean won;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        couleurJeton = 0;
        for(int i = 0; i < 9; i++){
            gstate[i] = 100;
        }
        won = false;
    }

    public void userClicked(View view){
        if(!won) {
            ImageView turn = (ImageView) view;
            int tag;
            tag = Integer.parseInt(turn.getTag().toString());
            if(gstate[tag] == 0 | gstate[tag] ==1){
                Toast.makeText(this, "Someone has selected that location.", Toast.LENGTH_SHORT).show();
            }
            else {
                turn.setTranslationY(-1500);
                gstate[tag] = couleurJeton;
                if (couleurJeton == 0) {
                    turn.setImageResource(R.drawable.unnamed);
                    couleurJeton = 1;
                } else {
                    turn.setImageResource(R.drawable.jaune);
                    couleurJeton = 0;
                }
                turn.animate().translationYBy(1500).setDuration(300);
                checkWon();
            }
        }
    }

    public void checkWon(){
        for(int k = 0; k <3 && won == false; k++){
            if(gstate[k] == 0 && gstate[k+3] == 0 && gstate[k+6] == 0){
                setStatus(0);
            }
            else if(gstate[k] == 1 && gstate[k+3] == 1 && gstate[k+6] == 1){
                setStatus(1);
            }
        }

        for(int k =0; k < 9 && !won; k = k + 3){
            if(gstate[k] == 0 && gstate[k+1] == 0 && gstate[k+2] == 0){
                setStatus(0);
            }
            else if(gstate[k] == 1 && gstate[k+1] == 1 && gstate[k+2] == 1){
                setStatus(1);
            }
        }

        if(!won){
            if((gstate[0] == 0 && gstate[4] == 0 && gstate[8] == 0) | (gstate[2] == 0 && gstate[4] == 0 && gstate[6] == 0)){
                setStatus(0);
            }
            else if((gstate[0] == 1 && gstate[4] == 1 && gstate[8] == 1)| (gstate[2] == 1 && gstate[4] == 1 && gstate[6] == 1)){
                setStatus(1);
            }
        }
    }

    public void setStatus(int calc){
        if(calc == 0 | calc == 1){
            won = true;
            Log.i("Status", "Won the game!");
        }
        if(won == true){
            Won(calc);
        }
    }

    public void Won(int who){
        who++;
        Toast.makeText(this, "Player " + who + " won!", Toast.LENGTH_SHORT).show();
    }

    public void reset(View view){

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);

        }
        couleurJeton = 0;
        for(int i = 0; i < 9; i++){
            gstate[i] = 100;
        }
        won = false;
    }
}