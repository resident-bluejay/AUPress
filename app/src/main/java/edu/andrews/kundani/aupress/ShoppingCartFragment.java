package edu.andrews.kundani.aupress;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import android.support.v4.app.ListFragment;

/*
Hosts the fragment that displays books in the shopping cart
 */

public class ShoppingCartFragment extends ListFragment {


    //arraylist of books
    private ArrayList<CartBook> mCartBooks = new ArrayList<>();

    //reference to the price
    double totalPrice = 0;

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
            //inflate view
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

            //set price number
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
        View v = inflater.inflate(R.layout.shopping_cart_frame_layout, container, false);

        //ListView
        ListView listView = (ListView) v.findViewById(android.R.id.list);


        double total = getCount();

        DecimalFormat df = new DecimalFormat();

        //checkout button
        TextView totalTextVIew = (TextView) v.findViewById(R.id.btnAddMovie);
        totalTextVIew.setText("TOTAL: $ " + df.format(total));

        return v;
    }

    /*
    returns the total price for the books in shopping cart
     */
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

        //get an instance on SHoopingCartList
        mCartBooks = ShoppingCartList.getInstance(getActivity()).getBooks();

        //adapter
        BookAdapter adapter = new BookAdapter(mCartBooks);
        setListAdapter(adapter);

        //set up action bar
        setHasOptionsMenu(true);

    }

}
