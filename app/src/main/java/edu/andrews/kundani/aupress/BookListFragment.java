package edu.andrews.kundani.aupress;

import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BookListFragment extends ListFragment {

    //list_layout of bugs
    private ArrayList<Book> mBooks;

    private Callbacks mCallbacks;

    public BookListFragment() {
        //constructor
    }

    //book adapter
    private class BookAdapter extends ArrayAdapter<Book> {
        public BookAdapter (ArrayList<Book> books) {
            super(getActivity(), 0, books);
        }

        //view for individual book goes here...
        public View getView(int position, View convertView, ViewGroup parent) {
            //things
            if(null == convertView) {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_book_list, container, false);
        //View v = inflater.inflate(R.layout.list_layout, container, false);

        //ListView
        //ListView listView = (ListView) v.findViewById(android.R.id.list);

        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBooks = BookList.getInstance(getActivity()).getBooks();

        //adapter
        BookAdapter adapter = new BookAdapter(mBooks);

        //message for books found
        //if none
        if (mBooks.isEmpty()){
            Toast.makeText(getActivity(), "No results", Toast.LENGTH_LONG).show();
        }

        //if more than 0
        else if (mBooks.size() > 0){
            Toast.makeText(getActivity(), "Found " + mBooks.size() + " Results",
                    Toast.LENGTH_LONG).show();
        }
        setListAdapter(adapter);
    }

    public void onResume() {
        super.onResume();
        //((BookAdapter)getListAdapter().notifyDataSetChanged();
    }

    public interface Callbacks {
        void onBookSelected(Book book);
    }


}
