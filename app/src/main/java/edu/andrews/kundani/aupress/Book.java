package edu.andrews.kundani.aupress;


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
}
