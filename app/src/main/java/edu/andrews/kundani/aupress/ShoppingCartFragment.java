package edu.andrews.kundani.aupress;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import android.support.v4.app.ListFragment;

public class ShoppingCartFragment extends ListFragment {


    private ArrayList<CartBook> mCartBooks = new ArrayList<>();

    double totalPrice;

    SQLiteDbHandler db = new SQLiteDbHandler(getActivity());

    double total = getCount();

    public ShoppingCartFragment() {
        //constructor
    }
    //book adapter
    private class BookAdapter extends ArrayAdapter<CartBook> {
        public BookAdapter (ArrayList<CartBook> books) {
            super(getActivity(), 0, books);
        }

        //view for individual book goes here...
        public View getView(int position, View convertView, ViewGroup parent) {
            //things
            if(null == convertView) {
                convertView = getActivity().getLayoutInflater().
                        inflate(R.layout.shopping_cart_item, null);
            }

            //configure view for book
            CartBook book = getItem(position);

            //set title
            TextView titleTextView =
                    (TextView) convertView.findViewById(R.id.shopping_cart_title_textView);
            titleTextView.setText(book.getBookTitle());

            //set page number
            TextView priceTextView =
                    (TextView) convertView.findViewById(R.id.shopping_cart_price_textView);
            priceTextView.setText("$" + book.getListPrice());

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
        ListView listView = (ListView) v.findViewById(android.R.id.list);

        //TextView totalTextVIew = (TextView) v.findViewById(R.id.total_textView);
        //totalTextVIew.setText("$ " + total);


        return v;
    }

    public Double getCount(){
        //initialize
        for (CartBook mCartBook: mCartBooks) {
            totalPrice += mCartBook.getListPrice();
        }
        return totalPrice;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCartBooks = ShoppingCartList.getInstance(getActivity()).getBooks();

        //double total = getCount();

        //adapter?
        BookAdapter adapter = new BookAdapter(mCartBooks);

        setListAdapter(adapter);

        //set up action bar
        setHasOptionsMenu(true);
    }

}
