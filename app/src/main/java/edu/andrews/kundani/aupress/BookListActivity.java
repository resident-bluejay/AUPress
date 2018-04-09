package edu.andrews.kundani.aupress;

//import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


public class BookListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return new BookListFragment();
    }

}
