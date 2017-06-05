package edu.andrews.kundani.aupress;

//import android.app.Fragment;
import android.support.v4.app.Fragment;


public class BookListActivity extends SingleFragmentActivity
/*implements BookListFragment.Callbacks*/{

    @Override
    protected Fragment createFragment() {
        return new BookListFragment();
    }

   // @Override
   // protected int getLayoutResId() {
        //return R.layout.activity_masterdetail;
   // }
}
