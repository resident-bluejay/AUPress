package edu.andrews.kundani.aupress;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Reads book information in JSON format?????
 */
public class JSONFileReader extends AppCompatActivity{

    private Context mContext;
    private String mFilename;

    public JSONFileReader (Context c, String f) {
        mContext = c;
        mFilename = f;

    }

    public ArrayList<Book> loadJSONFromAsset() throws IOException, JSONException {
        ArrayList<Book> bookList = new ArrayList<Book>();
        String json = null;
        BufferedReader reader = null;

        try {
            //open sample.json file
            reader = new BufferedReader(new InputStreamReader(mContext.getAssets().open(
                    "sample.json"), "UTF-8"));

            StringBuilder jsonString = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                //next line
                jsonString.append(line);
            }

            //read json object as string
            JSONObject jsonObject = (JSONObject) new JSONTokener(jsonString.toString()).nextValue();
            JSONArray jsonArray = jsonObject.getJSONArray("Sample");
            for (int i = 0; i < jsonArray.length(); i++){
                bookList.add(new Book(jsonArray.getJSONObject(i)));

            }

        } catch (FileNotFoundException e) {
            //e.printStackTrace();
        } finally {
            if (reader != null)
                reader.close();
        }
        return bookList;
    }

    public void saveBooks (ArrayList<Book> books) throws JSONException, IOException {
        //build an array in JSON
        JSONArray array = new JSONArray();
        for (Book book : books)
            array.put(book.toJSON(book));

        //write the file to disk
        Writer writer = null;
        try {
            OutputStream out = mContext.openFileOutput(mFilename,
                    Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}