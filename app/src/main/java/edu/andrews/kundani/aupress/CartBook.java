package edu.andrews.kundani.aupress;

import java.util.UUID;

public class CartBook {
    //book title
    private String mTitle;

    //book author
    private String mAuthor;

    //book ISBN number
    private String mISBN;

    //page number
    private double mListPrice;

    //random id
    private int mId;

    public CartBook() {
        //constructor
    }

    /**
     * A new book
     * @param title         Resource id for book's title
     * @param author        Resource id for book's author
     * @param isbn          Resource id for book ISBN
     * @param listPrice    Resource id for book price
     */
    public CartBook(String title, String author, String isbn, double listPrice) {
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

    public void setId (int id){
        mId = id;
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

    public int getId() {
        return mId;
    }

    @Override
    public String toString() {
        return mTitle + " " + "$" + mListPrice;
    }

}
