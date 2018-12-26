package com.example.marjolein.bucketlist.View;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marjolein.bucketlist.Model.BucketListItem;
import com.example.marjolein.bucketlist.R;

public class AddBucketListItemActivity extends AppCompatActivity {

    EditText mTitle, mDescription;
    Button mButtonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bucket_list_item);

        mTitle = findViewById(R.id.et_add_title);
        mDescription = findViewById(R.id.et_add_description);
        mButtonSave = findViewById(R.id.btn_save);

        saveData();
    }

    private void saveData(){
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = mTitle.getText().toString();
                String description = mDescription.getText().toString();

                BucketListItem newBucketListItem = new BucketListItem(title, description);

                if (!(TextUtils.isEmpty(title)) && !(TextUtils.isEmpty(description))) {
                    Intent result = new Intent();
                    result.putExtra(MainActivity.RESULT_KEY, newBucketListItem);
                    setResult(Activity.RESULT_OK, result);
                    finish();
                } else {
                    //Show a message to the user if the textfields are empty.
                    Snackbar.make(v, R.string.item_required, Snackbar.LENGTH_LONG).setAction("Action", null).show();
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
