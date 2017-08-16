package edu.andrews.kundani.aupress;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ListViewAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    private List<Book> sortedBookList = null;
    private ArrayList<Book> arraylist;

    public ListViewAdapter(Context context, List<Book> bookList) {
        mContext = context;
        this.sortedBookList = bookList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Book>();
        this.arraylist.addAll(bookList);
    }

    //view holder for individual book
    public class ViewHolder {
        TextView titleTextview;
        TextView authorTextView;
        TextView isbnTextView;
        TextView pageNumTextView;

    }

    @Override
    public int getCount() {
        return sortedBookList.size();
    }

    @Override
    public Book getItem(int position) {
        return sortedBookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //single item view
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_item_book, null);

            // Locate the TextViews
            holder.titleTextview = (TextView) view.findViewById(R.id.book_list_item_titleTextView);
            holder.authorTextView = (TextView) view.findViewById(R.id.book_list_item_authorTextView);
            holder.isbnTextView = (TextView) view.findViewById(R.id.book_list_item_isbnTextView);
            holder.pageNumTextView = (TextView) view.findViewById(R.id.book_list_item_priceTextView);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set information into appropriate textViews
        holder.titleTextview.setText(sortedBookList.get(position).getBookTitle());
        holder.authorTextView.setText(sortedBookList.get(position).getAuthor());
        holder.isbnTextView.setText(sortedBookList.get(position).getISBN());
        holder.pageNumTextView.setText("$"+ sortedBookList.get(position).getListPrice());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        sortedBookList.clear();
        if (charText.length() == 0) {
            sortedBookList.addAll(arraylist);
        } else {
            for (Book book : arraylist) {
                //use keywords to filter titles
                if (book.getKeywords().toLowerCase(Locale.getDefault()).contains(charText)) {
                    sortedBookList.add(book);
                }
            }
        }
        notifyDataSetChanged();
    }
}
