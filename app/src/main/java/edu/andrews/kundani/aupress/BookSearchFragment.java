package edu.andrews.kundani.aupress;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;


public class BookSearchFragment extends ListFragment {
    //list_layout of bugs
    private ArrayList<Book> mBooks;

    /**
     * Tag to log messages
     **/
    public static final String TAG = "BookSearchFragment";

    //search query
    String query;

    //search key
    int search_key;

    public BookSearchFragment() {
        //constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();

        query = bundle.getString("query");
        search_key = bundle.getInt("searchIndex");

        doSearch(query, search_key);

        //set up action bar
        setHasOptionsMenu(true);
    }

    /**
     *
     * @param q The search word/s
     * @param k key that determines which category to search
     */
    public void doSearch(String q, int k) {

        //search by title
        if(k == 0)
            mBooks = BookList.getInstance(getActivity()).getTitleResults(q);
        //search by author
        else if(k == 1)
            mBooks = BookList.getInstance(getActivity()).getAuthorResults(q);
        //search by keyword
        else if(k == 2)
            mBooks = BookList.getInstance(getActivity()).getResults(q);
        //get all books
        else
            mBooks = BookList.getInstance(getActivity()).getBooks();

        //adapter
        BookAdapter adapter = new BookAdapter(mBooks);
        setListAdapter(adapter);

        //message for books found
        //if none return "No results'
        if (mBooks.isEmpty()) {
            Toast.makeText(getActivity(), "No results", Toast.LENGTH_SHORT).show();
        }

        //if more than 0
        else if (mBooks.size() > 0) {
            Toast.makeText(getActivity(), "Found " + mBooks.size() + " Results",
                    Toast.LENGTH_LONG).show();
        }

    }

    /**
     * Action bar
     **/
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.options_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        //hint
        searchView.setQueryHint("Search books");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                doSearch(query, search_key);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.i(TAG, "User searched for" + newText);
                return false;
            }
        });

    }

    /**
     * book adapter
     **/
    private class BookAdapter extends ArrayAdapter<Book> {
        public BookAdapter(ArrayList<Book> books) {
            super(getActivity(), 0, books);
        }

        //view for individual book goes here...
        public View getView(int position, View convertView, ViewGroup parent) {
            //things
            if (null == convertView) {
                convertView = getActivity().getLayoutInflater().
                        inflate(R.layout.list_item_book, null);
            }

            //configure view for book
            Book book = getItem(position);

            //set title
            TextView titleTextView =
                    (TextView) convertView.findViewById(R.id.book_list_item_titleTextView);
            titleTextView.setText(book.getBookTitle());

            //set author
            TextView authorTextView =
                    (TextView) convertView.findViewById(R.id.book_list_item_authorTextView);
            authorTextView.setText(book.getAuthor());

            //set ISBN
            TextView isbnTextView =
                    (TextView) convertView.findViewById(R.id.book_list_item_isbnTextView);
            isbnTextView.setText(book.getISBN());

            //set page number
            TextView pageNumTextView =
                    (TextView) convertView.findViewById(R.id.book_list_item_priceTextView);
            pageNumTextView.setText("$" + book.getListPrice());

            //return the view
            return convertView;
        }
    }


    /**
     * Creates book list view
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return the list view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_book_list, container, false);

        //ListView
        //ListView listView = (ListView) v.findViewById(android.R.id.list);

        return v;
    }

    @Override
    public void onListItemClick(ListView listView, View v, int position, long id) {
        super.onListItemClick(listView, v, position, id);


        Book book = (Book) (getListAdapter().getItem(position));

        String selection = book.getBookTitle();
        Toast.makeText(getActivity(), "You selected " + selection, Toast.LENGTH_SHORT).show();

        Log.d(TAG, "Clicked on:" + selection);

        //put book info in Bundle to send to BookDetailsFragment
        Bundle extras = new Bundle();
        extras.putString("EXTRA_TITLE", book.getBookTitle());
        extras.putString("EXTRA_AUTHOR", book.getAuthor());
        extras.putString("EXTRA_ISBN", book.getISBN());
        extras.putDouble("EXTRA_LIST_PRICE", book.getListPrice());

        Intent i = new Intent(getActivity(), BookDetailsFragment.class);
        //send bundle
        i.putExtras(extras);

        startActivity(i);
    }

    public void onResume() {
        super.onResume();
    }

}


