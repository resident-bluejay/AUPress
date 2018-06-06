package edu.andrews.kundani.aupress;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
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

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    /**Tag to log messages**/
    public static final String TAG = "MainActivity";

    /**Reference to search view**/
    SearchView bookSearchView;

    /**Reference to  keyword-search button**/
    ImageButton dropDownButton;

    /**Reference to cart textview */
    TextView cartItemsView;
    //index to indicate specific search type
    int searchIndex;

    //number of items in the shopping cart
    int cart_number;

    SQLiteDbHandler db = new SQLiteDbHandler(this);

    ArrayList<CartBook> mCartBooks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCartBooks = db.allCartBooks();
        cart_number = mCartBooks.size();

        //drop down selection
        dropDownButton = (ImageButton) findViewById(R.id.dropDownImageButton);
        dropDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dropdown options
                CharSequence options[] = new CharSequence[]{"title", "author", "keyword", "all"};


                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Search by...");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    //for testing purposes
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(TAG, "user selected: " + which);
                        //get user option
                        searchIndex = which;
                    }
                });
                builder.show();
            }

        });

        //configure searchview
        bookSearchView = (SearchView) findViewById(R.id.diffSearchView);
        bookSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {

                //send [key] and query to be searched
                Intent intent = new Intent(MainActivity.this, BookSearchActivity.class);
                Bundle extras = new Bundle();
                extras.putString("query", query);
                extras.putInt("searchIndex", searchIndex);
                intent.putExtras(extras);
                startActivity(intent);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                Log.i(TAG, "User searched for "+ newText);
                return false;
            }
        });

        //button that leads to shopping cart from the main screen
        cartItemsView = (TextView) findViewById(R.id.cart_items_view);
        cartItemsView.setText("Cart: " + Integer.toString(cart_number) + " items");
        cartItemsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent cart = new Intent(MainActivity.this,
                        ShoppingCartActivity.class);
                startActivity(cart);
            }
        });
    }
}


