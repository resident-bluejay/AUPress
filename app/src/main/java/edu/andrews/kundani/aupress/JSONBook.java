package edu.andrews.kundani.aupress;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONBook {

    public static String toJSON (Book book){
        try {
            //create a new JSON Object
            JSONObject JSONObj = new JSONObject();
            JSONObj.put("title", book.getTitle());
            JSONObj.put("author", book.getAuthor());
            JSONObj.put("ISBN", book.getISBN());
            JSONObj.put("Pages", book.getPageNumber());

            //return the JSON Object
            return JSONObj.toString();
        }
        catch (JSONException exception) {
            exception.printStackTrace();
        }

        return null;
    }
}
