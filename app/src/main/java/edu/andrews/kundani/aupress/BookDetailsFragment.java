package edu.andrews.kundani.aupress;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Activity for one single book
 */

public class BookDetailsFragment extends AppCompatActivity {

    //text view references
    TextView titleTextView;
    TextView authorTextView;
    TextView isbnTextView;
    TextView priceTextView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_book);

        //get information from the search fragment bundle
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String mTitle = bundle.getString("EXTRA_TITLE");
        String mAuthor= bundle.getString("EXTRA_AUTHOR");
        String mISBN= bundle.getString("EXTRA_ISBN");
        Double mPrice= bundle.getDouble("EXTRA_LIST_PRICE");

        titleTextView = (TextView) findViewById(R.id.titleTV);
        titleTextView.setText(mTitle);

        authorTextView = (TextView) findViewById(R.id.authorTV);
        authorTextView.setText(mAuthor);

        isbnTextView = (TextView) findViewById(R.id.isbnTV);
        isbnTextView.setText(mISBN);

        priceTextView = (TextView) findViewById(R.id.priceTV);
        priceTextView.setText("$" + mPrice);
    }
}
