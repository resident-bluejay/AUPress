package edu.andrews.kundani.aupress;

//import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


public class BookListActivity extends SingleFragmentActivity
/*implements BookListFragment.Callbacks*/{

    /**public void onBookSelected(Book book) {
        if (findViewById(R.id.detailFragmentContainer) == null) {
            // start an instance of bugDetailsActivity
            Intent i = new Intent(this, BookDetailsActivity.class);
            i.putExtra(BookDetailsFragment.EXTRA_BUG_ID, book.getId());
            startActivityForResult(i, 0);
        }//one fragment view

        //two fragment view
        else {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            Fragment oldDetail = fm.findFragmentById(R.id.detailFragmentContainer);
            Fragment newDetail = BookDetailsFragment.newInstance(book.getId());

            if (oldDetail != null) {
                ft.remove(oldDetail);
            }

            ft.add(R.id.detailFragmentContainer, newDetail);
            ft.commit();
        }
    }

    //public void onBugUpdated(Bug bug) {
       // FragmentManager fm = getSupportFragmentManager();
        //BugListFragment listFragment = (BugListFragment)
               // fm.findFragmentById(R.id.fragment_container);
        //listFragment.updateUI();
    }**/

    @Override
    protected Fragment createFragment() {
        return new BookListFragment();
    }

   // @Override
   // protected int getLayoutResId() {
        //return R.layout.activity_masterdetail;
   // }
}
