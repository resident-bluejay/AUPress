package edu.andrews.kundani.aupress;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


public class SearchActivity extends AppCompatActivity {

    private ListView list;
    private ListViewAdapter adapter;
    private ArrayList<Book> mBookArrayList;
    Context mAppContext;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        String query = getIntent().getStringExtra("query");

        mBookArrayList = BookList.getInstance(getApplicationContext()).getBooks();

        list = (ListView) findViewById(R.id.bookListView);

        adapter = new ListViewAdapter(this, mBookArrayList);

        list.setAdapter(adapter);

        adapter.filter(query);
        }

    //Set up actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        //search view
        MenuItem searchItem = menu.findItem(R.id.search);

        //set searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setQueryHint("Search books");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String newQuery) {
                list.setAdapter(adapter);
                //search book with given keyword
                adapter.filter(newQuery);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.i("User searched for",  ""+ newText);
                return false;
            }
        });

        return true;
    }

    //handle back button
    @Override
    public void onBackPressed() {
        startActivity(new Intent(SearchActivity.this, MainActivity.class));
    }

}



