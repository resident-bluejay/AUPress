package edu.andrews.kundani.aupress;


import android.content.Context;
import android.util.Log;
import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;

public class BookList {

    /**Instance variable**/
    private static BookList anInstance;

    //List of books
    private ArrayList<Book> mBooks;

    //Reference info on app environment?
    private Context mAppContext;

    //TAG
    private static final String TAG = "BookList";

    //filename
    private static final String FILENAME = "sample.json";


    private JSONFileReader mJSONFileReader;

    private BookList (Context appContext) {
        mAppContext = appContext;

        mJSONFileReader = new JSONFileReader(mAppContext, FILENAME);

        try{
            //get books from assets
            mBooks = mJSONFileReader.loadJSONFromAsset();
        } catch (Exception exception) {
            mBooks = new ArrayList<Book>();
            Log.e(TAG, "Error --> "+exception.getMessage());

        }
    }

    //create an instance of list
    public static BookList getInstance (Context c) {
        if(anInstance == null)
            anInstance = new BookList(c.getApplicationContext());
        return anInstance;
    }

    //get array of books and return list_layout
    public ArrayList<Book> getBooks() {
        return mBooks;
    }

    //get unique id for book...
    //comment some more here
    public Book getBook(UUID id) {
        for (Book book: mBooks) {
            if(book.getUUID().equals(id)) {
                return book;
            }
        }
        return null;
    }

    //get book with certain keywords
    public ArrayList<Book> getResults(String query) {
        //create a new array to store books with certain keywords
        ArrayList<Book> newArray = new ArrayList<Book>();
        for(Book book: mBooks) {
            //find books by keyword
            if(book.getKeywords().toLowerCase(Locale.getDefault()).contains(query)) {
                newArray.add(book);
            }
            //find book by title
            else if(book.getBookTitle().toLowerCase(Locale.getDefault()).contains(query))
            {
                newArray.add(book);
            }
        }
        return newArray;
    }

    public ArrayList<Book> getAuthorResults(String query){
        ArrayList<Book> authorArray = new ArrayList<Book>();
        for(Book book: mBooks) {
            //find books
            if(book.getAuthor().toLowerCase(Locale.getDefault()).contains(query)) {
                authorArray.add(book);
            }
        }
        return authorArray;


    }

    public ArrayList<Book> getTitleResults(String query) {
        ArrayList<Book> titleArray = new ArrayList<Book>();
        for (Book book : mBooks) {
            //find books
            if (book.getBookTitle().toLowerCase(Locale.getDefault()).contains(query)) {
                titleArray.add(book);
            }
        }
        return titleArray;
    }


}
