package edu.andrews.kundani.aupress;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Activity for one single book
 */

public class BookDetails extends AppCompatActivity {

    TextView titleTextView;
    TextView authorTextView;
    TextView isbnTextView;
    TextView priceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_book);

        titleTextView = (TextView) findViewById(R.id.titleTV);
        authorTextView = (TextView) findViewById(R.id.authorTV);
        isbnTextView = (TextView) findViewById(R.id.isbnTV);
        priceTextView = (TextView) findViewById(R.id.priceTV);
    }
}
