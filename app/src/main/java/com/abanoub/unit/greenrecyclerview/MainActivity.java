package com.abanoub.unit.greenrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NumbersAdapter.ListItemClickListener{

    private static final int NUM_LIST_ITEMS = 150;

    private RecyclerView mNumbersList;

    private NumbersAdapter mAdapter;

    /*
     * If we hold a reference to our Toast, we can cancel it (if it's showing)
     * to display a new Toast. If we didn't do this, Toasts would be delayed
     * in showing up if you clicked many list items in quick succession.
     */
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * Using findViewById, we get a reference to our RecyclerView from xml. This allows us to
         * do things like set the adapter of the RecyclerView and toggle the visibility.
         */
        mNumbersList = findViewById(R.id.rv_numbers);

        /*
         * A LinearLayoutManager is responsible for measuring and positioning item views within a
         * RecyclerView into a linear list. This means that it can produce either a horizontal or
         * vertical list depending on which parameter you pass in to the LinearLayoutManager
         * constructor. By default, if you don't specify an orientation, you get a vertical list.
         * In our case, we want a vertical list.
         */
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mNumbersList.setLayoutManager(manager);

        /*
         * Use this setting to improve performance if you know that changes in content do not
         * change the child layout size in the RecyclerView
         */
        mNumbersList.setHasFixedSize(true);

        /*
         * The NumbersAdapter is responsible for displaying each item in the list.
         */
        mAdapter = new NumbersAdapter(NUM_LIST_ITEMS, this);
        mNumbersList.setAdapter(mAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_refresh:
                /*
                 * When you click the reset menu item, we want to start all over
                 * and display the pretty gradient again. There are a few similar
                 * ways of doing this, with this one being the simplest of those
                 * ways. (in our humble opinion)
                 */
                mAdapter = new NumbersAdapter(NUM_LIST_ITEMS, this);
                mNumbersList.setAdapter(mAdapter);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This is where we receive our callback from
     * {@link com.abanoub.unit.greenrecyclerview.NumbersAdapter.ListItemClickListener}
     *
     * This callback is invoked when you click on an item in the list.
     *
     * @param index Index in the list of the item that was clicked.
     */
    @Override
    public void onListItemClick(int index) {
        if (mToast != null){
            mToast.cancel();
        }
        mToast = Toast.makeText(this, "Clicked on Item number: " + index, Toast.LENGTH_LONG);
        mToast.show();
    }
}
