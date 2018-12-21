package com.example.marjolein.gamebacklogmanager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GameAdapter.GameClickListener{

    private List<Game> mGames;
    private RecyclerView mRecyclerView;
    private GameAdapter mAdapter;

    //Constants used when calling the update activity
    public static final String GAME = "Game";
    public static final int REQUEST_CODE_INSERT = 1234;
    public static final int REQUEST_CODE_UPDATE = 5678;
    private int mModifyPosition;

    public final static int TASK_GET_ALL_GAMES = 0;
    public final static int TASK_DELETE_GAMES = 1;
    public final static int TASK_UPDATE_GAMES = 2;
    public final static int TASK_INSERT_GAMES = 3;

    static AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        db = AppDatabase.getInstance(this);
        new GameAsyncTask(TASK_GET_ALL_GAMES).execute();

        mGames = new ArrayList<>();

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        updateUI();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAdd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, REQUEST_CODE_INSERT);
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

         /*
           Add a touch helper to the RecyclerView to recognize when a user swipes to delete a list entry.
           An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
           and uses callbacks to signal when a user is performing these actions.
        */
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                        return false;
                    }

                    //Called when a user swipes left or right on a ViewHolder
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        //Get the index corresponding to the selected position
                        int position = (viewHolder.getAdapterPosition());
                        new GameAsyncTask(TASK_DELETE_GAMES).execute(mGames.get(position));
                    }
                };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    public void onGameDbUpdate(List list){
        mGames = list;
        updateUI();
    }

    private void updateUI(){
        if(mAdapter == null){
            mAdapter = new GameAdapter(mGames, this);
            mRecyclerView.setAdapter(mAdapter);
        } else{
            mAdapter.swapList(mGames);
        }
    }

    @Override
    public void gameOnClick(int i){
        Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
        mModifyPosition = i;
        intent.putExtra(GAME, mGames.get(i));
        startActivityForResult(intent, REQUEST_CODE_UPDATE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_CODE_INSERT){
            if(resultCode == RESULT_OK){
                Game newGame = data.getParcelableExtra(MainActivity.GAME);
                // New timestamp: timestamp of update
                new GameAsyncTask(TASK_INSERT_GAMES).execute(newGame);
            }
        }
        if(requestCode == REQUEST_CODE_UPDATE){
            if(resultCode == RESULT_OK){
                Game updateGame = data.getParcelableExtra(MainActivity.GAME);
                // New timestamp: timestamp of update
                new GameAsyncTask(TASK_UPDATE_GAMES).execute(updateGame);
            }
        }
    }

    public class GameAsyncTask extends AsyncTask<Game, Void, List>{
        private int taskCode;

        public GameAsyncTask(int taskCode){
            this.taskCode = taskCode;
        }

        @Override
        protected List doInBackground(Game... games){
            switch(taskCode){
                case TASK_DELETE_GAMES:
                    db.gameDao().deleteGames(games[0]);
                    break;
                case TASK_UPDATE_GAMES:
                    db.gameDao().updateGames(games[0]);
                    break;
                case TASK_INSERT_GAMES:
                    db.gameDao().insertGames(games[0]);
                    break;
            }

            //To return a new List with the updates data, we get all the data from the database again.
            return db.gameDao().getAllGames();
        }

        @Override
        protected void onPostExecute(List list){
            super.onPostExecute(list);
            onGameDbUpdate(list);
        }
    }
}
