package edu.andrews.kundani.aupress;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class SQLiteDbHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ShoppingCart";
    private static final String TABLE_NAME = "Books";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_ISBN = "isbn";
    private static final String KEY_PRICE = "price";

    private static final String[] COLUMNS = {KEY_ID, KEY_TITLE, KEY_AUTHOR,
                                                KEY_ISBN, KEY_PRICE };

    public SQLiteDbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //create table columns for books
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE BOOKS ( "
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "title TEXT, "
                + "author TEXT, "
                + "isbn TEXT, "
                + "price REAL )";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    /*
    Deletes book from shopping cart database
     */
    public void onDelete(CartBook book) {
        //get database reference
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "title = ?", new String[] {
            String.valueOf(book.getBookTitle())
        });
        db.close();
    }

    /*
    Get only one book from the shopping cart
     */
    public CartBook getCartBooK(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, COLUMNS,
                "id = ?",
                new String [] { String.valueOf(id)},
                null,
                null,
                null,
                null
                );

        if(cursor != null)
            cursor.moveToFirst();

        CartBook cartBook = new CartBook();
        cartBook.setId(Integer.parseInt(cursor.getString(0)));
        cartBook.setTitle(cursor.getString(1));
        cartBook.setAuthor(cursor.getString(2));
        cartBook.setISBN(cursor.getString(3));
        cartBook.setListPrice(Double.parseDouble(cursor.getString(4)));

        return cartBook;

    }

    /*
    Returns all books in the shopping cart in an array list
     */

    public ArrayList<CartBook> allCartBooks() {

        ArrayList<CartBook> mCartBooks = new ArrayList<CartBook>();

        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        CartBook cartBook = null;

        if(cursor.moveToFirst()) {
            do{
                cartBook = new CartBook();
                cartBook.setId(Integer.parseInt(cursor.getString(0)));
                cartBook.setTitle(cursor.getString(1));
                cartBook.setAuthor(cursor.getString(2));
                cartBook.setISBN(cursor.getString(3));
                cartBook.setListPrice(Double.parseDouble(cursor.getString(4)));

                mCartBooks.add(cartBook);
            } while (cursor.moveToNext());
        }

        return mCartBooks;
    }

    /**
     * Adds some book into database
     * @param cartBook
     */
    public void addCartBook (CartBook cartBook) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, cartBook.getBookTitle());
        values.put(KEY_AUTHOR, cartBook.getAuthor());
        values.put(KEY_ISBN, cartBook.getISBN());
        values.put(KEY_PRICE, cartBook.getListPrice());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    /**
     * Updates shopping cart
     * @param cartBook a book in the shopping cart
     * @return updated database
     */
    private int updateCartBook (CartBook cartBook) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, cartBook.getBookTitle());
        values.put(KEY_AUTHOR, cartBook.getAuthor());
        values.put(KEY_ISBN, cartBook.getISBN());
        values.put(KEY_PRICE, cartBook.getListPrice());

        int i = db.update(TABLE_NAME,
                values,
                "id = ?",
                new String[] {String.valueOf(cartBook.getId())});
        db.close();

        return i;

    }

}

