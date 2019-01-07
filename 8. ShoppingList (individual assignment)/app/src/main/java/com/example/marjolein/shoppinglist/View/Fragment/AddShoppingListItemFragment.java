package com.example.marjolein.shoppinglist.View.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.marjolein.shoppinglist.Model.ShoppingList.ShoppingListItem;
import com.example.marjolein.shoppinglist.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddShoppingListItemFragment extends Fragment {

    EditText mTitle;
    Button btnSave;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_shopping_list_item,container,false);
        // init the EditText
        mTitle = view.findViewById(R.id.et_add_title);

        setButton(view);

        return view;
    }

    /**
     * Set the button for saving the data.
     * @param view for linking the button to the View
     */
    private void setButton(View view){
        btnSave = view.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the current date
                Date today = new Date();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                String date = format.format(today);

                // get the text from the text field
                String title = mTitle.getText().toString();

                // init an new object
                ShoppingListItem newShoppingListItem = new ShoppingListItem(title, date);

                // Check if text fields are empty
                if(!(TextUtils.isEmpty(title)) && !(TextUtils.isEmpty(date))){
                    ShoppingListFragment mFragment = new ShoppingListFragment();
                    // create a FragmentTransaction to begin the transaction and replace the Fragment
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();

                    // prepare the return parameters and return (Bundle == Intent)
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(ShoppingListFragment.RESULT_KEY, newShoppingListItem);
                    mFragment.setArguments(bundle);
                    // replace the FrameLayout with new Fragment
                    transaction.replace(R.id.fragment_layout, mFragment);
                    // save the changes
                    transaction.commit();
                } else{
                    //Show a message to the user if the text fields are empty.
                    Snackbar.make(view, R.string.item_required, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });
    }

}
