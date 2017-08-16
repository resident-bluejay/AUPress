package edu.andrews.kundani.aupress;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.app.SearchManager;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;


public class MainActivity extends AppCompatActivity {

    /**Tag to log messages**/
    public static final String TAG = "MainActivity";

    /**Reference to search view**/
    SearchView bookSearchView;

    /**Reference to  title-search button**/
    ImageButton bookSearchButton;

    /**Reference to  author-search button**/
    ImageButton authorSearchButton;

    /**Reference to  keyword-search button**/
    ImageButton keywordSearchButton;

    /**Empty query**/
    String blankQuery = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //configure searchview
        bookSearchView = (SearchView) findViewById(R.id.diffSearchView);
        bookSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                //send query to be searched
                Intent intent = new Intent(MainActivity.this, BookSearchActivity.class);
                intent.putExtra("query", query);
                startActivity(intent);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.i(TAG, "User searched for "+ newText);
                return false;
            }
        });

        //configure book search button
        bookSearchButton = (ImageButton) findViewById(R.id.bookSearchImageButton);
        bookSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent (MainActivity.this, TitleSearchActivity.class);
               // intent.putExtra("query", blankQuery);
                startActivity(intent);
            }
        });

        //configure author search image button
        authorSearchButton = (ImageButton) findViewById(R.id.authorSearchImageButton);
        authorSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent (MainActivity.this, AuthorSearchActivity.class);
                //intent.putExtra("query", blankQuery);
                startActivity(intent);
            }
        });

        //configure keyword search image button
        keywordSearchButton = (ImageButton) findViewById(R.id.keywordSearchImageButton);
        keywordSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent (MainActivity.this, BookSearchActivity.class);
                //intent.putExtra("query", blankQuery);
                startActivity(intent);
            }
        });

    }

    /**@Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);

        //set searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        //hint
        searchView.setQueryHint("Search books");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //send query to be searched
                //Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                Intent intent = new Intent(MainActivity.this, BookSearchActivity.class);
                intent.putExtra("query", query);
                startActivity(intent);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.i(TAG, "User searched for"+ newText);
                return false;
            }
        });
        return true;
    }**/


}



