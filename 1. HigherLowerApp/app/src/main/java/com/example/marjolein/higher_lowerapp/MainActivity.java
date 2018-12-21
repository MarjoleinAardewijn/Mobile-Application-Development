package com.example.marjolein.higher_lowerapp;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int score = 0;
    private int highscore = 0;
    private int prevThrow;

    private Random mDice;
    private TextView mScoreText;
    private TextView mHighScoreText;
    private FloatingActionButton mLowerButton;
    private FloatingActionButton mHigherButton;

    private int[] mDiceImage;

    private ListView mDiceListView;
    private ImageView mDiceImageView;
    private List<String> mDiceRollHistory;
    private ArrayAdapter<String> mArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDice = new Random();

        mDiceImageView = findViewById(R.id.imageView);
        mDiceListView = findViewById(R.id.listView);
        mLowerButton = findViewById(R.id.lowerBtn);
        mHigherButton = findViewById(R.id.higherBtn);
        mHighScoreText = findViewById(R.id.highScoreText);
        mScoreText = findViewById(R.id.scoreText);

        // create array list and array adapter for the list view.
        mDiceRollHistory = new ArrayList<>();

        mArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDiceRollHistory);
        mDiceListView.setAdapter(mArrayAdapter);

        // add images to and array
        mDiceImage = new int[]{R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5, R.drawable.d6};

        // onClick listeners for clicking the lower and higher buttons.
        mLowerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lowerButtonClick();
            }
        });

        mHigherButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                higherButtonClick();
            }
        });

        // Get new random dice
        prevThrow = throwDice();

    }

    // what to do when guess is correct
    private void correct(){
        score++;
        if(highscore < score){
            highscore = score;
        }
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Correct!", Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    // what to do when guess is wrong
    private void wrong(){
        score = 0;
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Wrong!", Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    // method for clicking lower button.
    private void lowerButtonClick(){
        int newThrow = throwDice();
        // if new throw is lower than prev throw, answer is correct, else answer is wrong.
        if(newThrow < prevThrow){
            correct();
        }else if(newThrow > prevThrow){
            wrong();
        }
        updateScoreText();
        prevThrow = newThrow;
    }

    // method for clicking higher button.
    private void higherButtonClick(){
        int newThrow = throwDice();
        // if new throw is higher than prev throw, answer is correct, else answer is wrong.
        if(newThrow > prevThrow){
            correct();
        } else if(newThrow < prevThrow){
            wrong();
        }
        updateScoreText();
        prevThrow = newThrow;
    }

    // method for updating the score
    private void updateScoreText(){
        mScoreText.setText("Score: " + score);
        mHighScoreText.setText("HighScore: " + highscore);
    }

    // method for throwing the dice randomly
    private int throwDice(){
        int throwDice = mDice.nextInt(6) + 1;
        mDiceImageView.setImageResource(mDiceImage[throwDice - 1]);

        mDiceRollHistory.add("Throw is " + throwDice);
        mArrayAdapter.notifyDataSetChanged();
        return throwDice;
    }

}
