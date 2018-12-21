package com.example.marjolein.gamebacklogmanager;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddActivity extends AppCompatActivity {
    private EditText mGameTitle;
    private EditText mGamePlatform;
    private EditText mGameNotes;
    private Spinner mStatusSpinner;

    private List<Game> mGames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mGameTitle = findViewById(R.id.addTitle);
        mGamePlatform = findViewById(R.id.addPlatform);
        mGameNotes = findViewById(R.id.addNotes);
        mStatusSpinner = findViewById(R.id.spinnerStatus);

        // add items to spinner
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(AddActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.status_dropdown));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mStatusSpinner.setAdapter(myAdapter);

        mGames = new ArrayList<>();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabSave);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Date today = new Date();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                String dateToString = format.format(today);

                // Get the user text from textfields
                String gameTitle = mGameTitle.getText().toString();
                String gamePlatform = mGamePlatform.getText().toString();
                String gameNotes = mGameNotes.getText().toString();
                String gameStatus = mStatusSpinner.getSelectedItem().toString();
                String date = dateToString.toString();

                Game newGame = new Game(gameTitle, gamePlatform, gameNotes, gameStatus, date);

                //Check if some text has been edited
                if(!(TextUtils.isEmpty(gameTitle)) && !(TextUtils.isEmpty(gamePlatform))
                        && !(TextUtils.isEmpty(gameNotes)) && !(TextUtils.isEmpty(gameStatus))
                        && !(TextUtils.isEmpty(date))){
                    //Add the text to the list (datamodel)
//                    mGames.add(newGame);
//                    new MainActivity.GameAsyncTask(MainActivity.TASK_INSERT_GAMES).execute(newGame);
                    //Prepare the return parameters and return
                    Intent result = new Intent();
                    result.putExtra(MainActivity.GAME, newGame);
                    setResult(Activity.RESULT_OK, result);
                    finish();
                } else{
                    //Show a message to the user if the textfields are empty.
                    Snackbar.make(view, "Please enter some text in the textfields", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });

    }

    // method to make the back button in the toolbar go back to the previous activity.
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
