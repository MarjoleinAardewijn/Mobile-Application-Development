package com.example.marjolein.gamebacklogmanager;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateActivity extends AppCompatActivity {

    private EditText mEditTitle, mEditPlatform, mEditNotes;
    private Spinner mStatusSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mEditTitle = findViewById(R.id.editTitle);
        mEditPlatform = findViewById(R.id.editPlatform);
        mEditNotes = findViewById(R.id.editNotes);
        mStatusSpinner =  findViewById(R.id.spinnerStatus);

        // add items to spinner
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(UpdateActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.status_dropdown));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mStatusSpinner.setAdapter(myAdapter);

        //Obtain the parameters provided by MainActivity
        final Game gameUpdate = getIntent().getParcelableExtra(MainActivity.GAME);
        mEditTitle.setText(gameUpdate.getGameTitle());
        mEditPlatform.setText(gameUpdate.getGamePlatform());
        mEditNotes.setText(gameUpdate.getGameNotes());

        for(int i = 0; i < mStatusSpinner.getCount(); i++){
            if (mStatusSpinner.getItemAtPosition(i).equals(gameUpdate.getGameStatus())) {
                mStatusSpinner.setSelection(i);
                break;
            }
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabEdit);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Date today = new Date();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                String dateToString = format.format(today);

                String title = mEditTitle.getText().toString();
                String platform = mEditPlatform.getText().toString();
                String notes = mEditNotes.getText().toString();
                String status = mStatusSpinner.getSelectedItem().toString();
                String date = dateToString.toString();

                Game newGame = new Game(title, platform, notes, status, date);

                //Check if some text has been edited
                if(!(TextUtils.isEmpty(title)) && !(TextUtils.isEmpty(platform))
                        && !(TextUtils.isEmpty(notes)) && !(TextUtils.isEmpty(status))
                        && !(TextUtils.isEmpty(date))){
                    gameUpdate.setGameTitle(title);
                    gameUpdate.setGamePlatform(platform);
                    gameUpdate.setGameNotes(notes);
                    gameUpdate.setGameStatus(status);
                    gameUpdate.setGameDate(date);
                    //Prepare the return parameter and return
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(MainActivity.GAME, gameUpdate);
                    setResult(Activity.RESULT_OK, resultIntent);
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
