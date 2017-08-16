package edu.andrews.kundani.aupress;

import android.support.v4.app.Fragment;


public class BookSearchActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return new BookSearchFragment();
    }


}
