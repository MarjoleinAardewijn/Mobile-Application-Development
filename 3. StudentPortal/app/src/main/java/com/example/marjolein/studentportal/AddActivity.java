package com.example.marjolein.studentportal;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity{

    //Local variables

    private EditText mPortalTitle;
    private EditText mPortalUrl;

    private Button portalButton;

    private List<Portal> mPortals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //Initialize local variables
        mPortalTitle = findViewById(R.id.titleText);
        mPortalUrl = findViewById(R.id.urlText);
        portalButton = findViewById(R.id.addPortalButton);

        mPortals = new ArrayList<>();

        portalButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Get the user text from textfield
                String urlText = mPortalUrl.getText().toString();
                String title = mPortalTitle.getText().toString();
                Portal newPortal = new Portal(title, urlText);

                //Check if some text has been edited
                if(!(TextUtils.isEmpty(urlText)) && !(TextUtils.isEmpty(title))){
                    //Add the text to the list (datamodel)
                    mPortals.add(newPortal);
                    // Prepare the return parameters and return
                    Intent result = new Intent();
                    result.putExtra(MainActivity.PORTAL, newPortal);
                    setResult(Activity.RESULT_OK, result);
                    finish();
                } else{
                    //Show a message to the user if the textfields are empty.
                    Snackbar.make(view, "Please enter some text in the textfields", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });
    }
}
