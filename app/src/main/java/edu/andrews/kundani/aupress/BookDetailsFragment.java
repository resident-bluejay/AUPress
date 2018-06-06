package edu.andrews.kundani.aupress;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Activity for one single book
 */

public class BookDetailsFragment extends AppCompatActivity {

    //text view references
    TextView titleTextView;
    TextView authorTextView;
    TextView isbnTextView;
    TextView priceTextView;
    TextView linkTextView;
    TextView viewCartTextView;
    Button addToCartButton;

    /** ViewPager component that allows users to browse books by swiping */
    private ViewPager mViewPager;

    /**Tag to log messages**/
    public static final String TAG = "BookDetailsFragment";

    CartBook book;
    Bundle cartBundle = new Bundle();
    ArrayList<CartBook> shoppingCartBooks = new ArrayList<CartBook>();
    SQLiteDbHandler db = new SQLiteDbHandler(this);

    ShoppingCartList mShoppingCartList;

    //double total = mShoppingCartList.getInstance(this).getCount();

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
        double mPrice= bundle.getDouble("EXTRA_LIST_PRICE");

        //create new book instance
        //this is what we will add to the shopping cart
        book = new CartBook(mTitle, mAuthor, mISBN, mPrice);

        titleTextView = (TextView) findViewById(R.id.titleTV);
        titleTextView.setText(mTitle);

        authorTextView = (TextView) findViewById(R.id.authorTV);
        authorTextView.setText(mAuthor);

        isbnTextView = (TextView) findViewById(R.id.isbnTV);
        isbnTextView.setText(mISBN);

        priceTextView = (TextView) findViewById(R.id.priceTV);
        priceTextView.setText("   $" + mPrice);

        linkTextView = (TextView) findViewById(R.id.linkTV);
        linkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browse = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://universitypress.andrews.edu/"));
                startActivity(browse);
            }
        });

        addToCartButton = (Button) findViewById(R.id.cart_button);
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add book to array/database
                db.addCartBook(book);
                Log.i(TAG, "user added: " + book.getBookTitle()
                +"Price: " + book.getListPrice() );
            }
        });

        //configure button that will display the shopping cart
        viewCartTextView = (TextView) findViewById(R.id.cart_text_veiw);
        viewCartTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent cart = new Intent(BookDetailsFragment.this,
                        ShoppingCartActivity.class);
                startActivity(cart);
            }
        });


    }
}
