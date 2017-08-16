package edu.andrews.kundani.aupress;

import android.support.v4.app.Fragment;

/**
 * Created by resident_bluejay on 8/16/17.
 */

public class AuthorSearchActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new AuthorSearchFragment();
    }
}
