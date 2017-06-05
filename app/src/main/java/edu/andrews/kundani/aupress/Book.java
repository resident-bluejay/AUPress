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
    private int mISBN;

    //page number
    private int mPageNumber;


    public Book () {
        //random unique ID for book?????
        mUUID = UUID.randomUUID();
    }

    /**
     * A new book
     * @param title         Resource id for book's title
     * @param author        Resource id for book's author
     * @param isbn          Resource id for book ISBN
     * @param pageNumber    Resource id for page number
     */
    public Book(String title, String author, int isbn, int pageNumber) {
        mTitle = title;
        mAuthor = author;
        mISBN = isbn;
        mPageNumber = pageNumber;
    }

    //setters
    public void setTitle(String title) {
        mTitle = title;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public void setISBN(int isbn) {
        mISBN = isbn;
    }

    public void setPageNumber(int pageNumber) {
        mPageNumber = pageNumber;
    }

    //getters
    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public int getISBN() {
        return mISBN;
    }

    public int getPageNumber() {
        return mPageNumber;
    }

    public UUID getUUID() {
        return mUUID;
    }

    /**JSON attribute for bug id */
    private static final String JSON_ID = "id";
    /** JSON sttribute for the bug title */
    private static final String JSON_TITLE = "title";
    /** JSON attribute for the bug's description */
    private static final String JSON_AUTHOR = "author";
    /** JSON attribute for bug date */
    private static final String JSON_ISBN = "ISBN";
    /** JSON attribute for bug solved status */
    private static final String JSON_PAGE_NUMBER = "pages";

    /**
     * Initialize a new bug from a JSON object
     * @param json is the JSON object for a bug
     * @throws JSONException
     */
    public Book(JSONObject json) throws JSONException {
        mUUID = UUID.fromString(json.getString(JSON_ID));
        mTitle = json.getString(JSON_TITLE);
        mAuthor = json.getString(JSON_AUTHOR);
        mISBN = json.getInt(JSON_ISBN);
        mPageNumber = json.getInt(JSON_PAGE_NUMBER);
    }

    public static JSONObject toJSON (Book book){
        try {
            //create a new JSON Object
            JSONObject JSONObj = new JSONObject();
            JSONObj.put("title", book.getTitle());
            JSONObj.put("author", book.getAuthor());
            JSONObj.put("ISBN", book.getISBN());
            JSONObj.put("Pages", book.getPageNumber());

            //return the JSON Object
            return JSONObj;
        }
        catch (JSONException exception) {
            exception.printStackTrace();
        }

        return null;
    }
}
