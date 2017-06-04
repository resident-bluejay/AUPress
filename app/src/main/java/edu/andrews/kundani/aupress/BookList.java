package edu.andrews.kundani.aupress;


import android.content.Context;
import android.util.Log;
import java.util.ArrayList;
import java.util.UUID;

public class BookList {

    /**Instance variable**/
    private static BookList anInstance;

    //List of bugs
    private ArrayList<Book> mBooks;

    //Reference info on app environment?
    private Context mAppContext;

    //TAG
    private static final String TAG = "BookList";

    //filename
    private static final String FILENAME = "books.json";

    private JSONFileReader mJSONFileReader;

    private BookList (Context appContext) {
        mAppContext = appContext;

        mJSONFileReader = new JSONFileReader(mAppContext, FILENAME);

        try{
            //get books from assets
            mBooks = mJSONFileReader.loadJSONFromAsset();
        } catch (Exception exception) {
            Log.e(TAG, "Book list not found?");
        }
    }

    //comment comment comment
    public static BookList getInstance (Context c) {
        if(anInstance == null)
            anInstance = new BookList(c. getApplicationContext());
        return anInstance;
    }

    //get array of books and return list
    public ArrayList<Book> getBooks() {
        return mBooks;
    }

    //get unique id for book...
    //comment some more here
    public Book getBooks(UUID id) {
        for (Book book: mBooks) {
            if(book.getUUID().equals(id)) {
                return book;
            }
        }
        return null;
    }


}
