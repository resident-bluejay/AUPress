package edu.andrews.kundani.aupress;

import android.support.v7.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Puts book information in JSON format?????
 */
public class JSONFileReader extends AppCompatActivity{

    public ArrayList<Book> loadJSONFromAsset() {
        ArrayList<Book> bookList = new ArrayList<>();
        String json = null;

        try {
            //open book.json file
            //InputStream stream = getActivity().getAssets().open("books.json");
            InputStream stream = getApplicationContext().getAssets().open("books.json");
            int size = stream.available();
            //initialize new byte array???????
            byte[] buffer = new byte[size];
            //read file
            stream.read(buffer);
            stream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
        //return json;
        //}

        try {
            JSONObject object = new JSONObject(json);
            JSONArray jsonArray = object.getJSONArray("Books");
            //ArrayList<HashMap<String,String>>

            //get book values
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jObject = jsonArray.getJSONObject(i);
                Book book = new Book();
                book.setTitle(jObject.getString("Title"));
                book.setAuthor(jObject.getString("Author"));
                book.setISBN(jObject.getInt("ISBN"));
                book.setPageNumber(jObject.getInt("Pages"));

                //Add values to ArrayList
                bookList.add(book);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bookList;
    }
}
