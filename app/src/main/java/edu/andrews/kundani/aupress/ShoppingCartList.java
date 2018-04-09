package edu.andrews.kundani.aupress;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

import static edu.andrews.kundani.aupress.MainActivity.TAG;

public class ShoppingCartList {

    private Context mAppContext;
    double totalPrice = 0;

    ArrayList<CartBook> mCartBooks = new ArrayList<>();

    /**Instance variable**/
    private static ShoppingCartList anInstance;

    private ShoppingCartList(Context appContext) {
        mAppContext = appContext;

         SQLiteDbHandler db = new SQLiteDbHandler(mAppContext);

        try{
            //get books from database
            mCartBooks = db.allCartBooks();

        } catch (Exception exception) {
            mCartBooks = new ArrayList<CartBook>();
            Log.e(TAG, "Error --> "+exception.getMessage());

        }
    }

    //create an instance of list
    public static ShoppingCartList getInstance (Context c) {
        if(anInstance == null)
            anInstance = new ShoppingCartList(c.getApplicationContext());
        return anInstance;
    }

    //get array of books and return list_layout
    public ArrayList<CartBook> getBooks() {
        return mCartBooks;
    }

    //get the total price
    public Double getCount(){
        //initialize
        for (CartBook mCartBook: mCartBooks) {
            totalPrice += mCartBook.getListPrice();
        }
        return totalPrice;
    }

    public int getSize(){
        return mCartBooks.size();
    }

}
