package edu.andrews.kundani.aupress;

import android.support.v4.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import junit.framework.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by resident_bluejay on 6/4/17.
 */

public class BookListFragment extends ListFragment {

    //list of bugs
    private ArrayList<Book> mBooks;

    private Callbacks mCallbacks;

    public BookListFragment() {
        //constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_book_list, container, false);

        //ListView
        //ListView listView = (ListView) v.findViewById(android.R.id.list);
        //allow user to select multiple bugs in the list
        //li

        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getActivity().setTitle("Books");
        mBooks = BookList.getInstance(getActivity()).getBooks();

        //adapter?
        //BookAdapter adapter = new BookAdapter(mBooks);
       // setListAdapter(adapter);
    }

    /*@Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Book book = (Book) (getListAdapter()).getItem(position);

        mCallbacks.onBugSelected(bug)
    }*/

    public void onResume() {
        super.onResume();
        //((BookAdapter)getListAdapter()0.notifyDataSetChanged();
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
            titleTextView.setText(book.getTitle());

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
                    (TextView) convertView.findViewById(R.id.book_list_item_pageNumTextView);
            pageNumTextView.setText(book.getPageNumber());

            //return the view
            return convertView;
        }
    }

    public interface Callbacks {
        void onBookSelected(Book book);
    }

    //private Callbacks mCallbacks;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (Callbacks)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    public void updateUI() {
        ((BookAdapter)getListAdapter()).notifyDataSetChanged();
    }

}