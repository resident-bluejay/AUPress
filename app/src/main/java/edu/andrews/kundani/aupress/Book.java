package edu.andrews.kundani.aupress;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

public class Book {

    //Unique ID for a book
    private UUID mUUID;

    //book title
    private String mTitle;

    //book author
    private String mAuthor;

    //book ISBN number
    private String mISBN;

    //page number
    private double mListPrice;

    //keywords
    private String mKeywords;


    public Book () {
        //random unique ID for book?????
        mUUID = UUID.randomUUID();
    }

    /**
     * A new book
     * @param title         Resource id for book's title
     * @param author        Resource id for book's author
     * @param isbn          Resource id for book ISBN
     * @param listPrice    Resource id for book price
     */
    public Book(String title, String author, String isbn, int listPrice) {
        mTitle = title;
        mAuthor = author;
        mISBN = isbn;
        mListPrice = listPrice;
    }

    //setters
    public void setTitle(String title) {
        mTitle = title;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public void setISBN(String isbn) {
        mISBN = isbn;
    }

    public void setListPrice(double listPrice) {
        mListPrice = listPrice;
    }

    //getters
    public String getBookTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getISBN() {
        return mISBN;
    }

    public double getListPrice() {
        return mListPrice;
    }

    public String getKeywords() {
        return mKeywords;
    }

    public UUID getUUID() {
        return mUUID;
    }

    /** JSON attribute for the book title */
    private static final String JSON_TITLE = "title";
    /** JSON attribute for the book's author */
    private static final String JSON_AUTHOR = "author(s)/editor(s)";
    /** JSON attribute for book isbn */
    private static final String JSON_ISBN = "isbn";
    /** JSON attribute for book list_layout price */
    private static final String JSON_LIST_PRICE = "list_price";
    /**JSON attribute for key search words */
    private static final String JSON_KEYWORDS = "keywords";

    /**
     * Initialize a new bug from a JSON object
     * @param json is the JSON object for a bug
     * @throws JSONException
     */
    public Book(JSONObject json) throws JSONException {
       // mUUID = UUID.fromString(json.getString(JSON_ID));
        mTitle = json.getString(JSON_TITLE);
        mAuthor = json.getString(JSON_AUTHOR);
        mISBN = json.getString(JSON_ISBN);
        mListPrice = json.getDouble(JSON_LIST_PRICE);
        mKeywords = json.getString(JSON_KEYWORDS);
    }

    public static JSONObject toJSON (Book book){
        try {
            //create a new JSON Object
            JSONObject JSONObj = new JSONObject();
            JSONObject bookObject = JSONObj.getJSONObject("Sample");
            bookObject.put("title", book.getBookTitle());
            bookObject.put("author(s)/editor(s)", book.getAuthor());
            bookObject.put("isbn", book.getISBN());
            bookObject.put("list_price", book.getListPrice());
            bookObject.put("keywords", book.getKeywords());

            //return the JSON Object
            return JSONObj;
        }
        catch (JSONException exception) {
            exception.printStackTrace();
        }

        return null;
    }
}
