package edu.andrews.kundani.aupress;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by resident_bluejay on 8/14/17.
 */

public class BookDetailsActivity extends FragmentActivity
        /*implements BookDetailsFragment.Callbacks*/ {

    /**viewPager component that allow users to browse bugs by swiping*/
   // private ViewPager mViewPager;

    /**Array of bugs*/
    //private ArrayList<Book> mBooks;

   /* @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //create the viewPager
        mViewPager = new ViewPager(this);
        //Viewpager needs a resource ID
        mViewPager.setId(R.id.viewPager);
        //set the view for this activity to be the viewPager
        //Previously it used the activity_fragment layout
        setContentView(mViewPager);

        //get the list of bugs
        mBugs = BookList.getInstance(this).getBooks();

        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            //Create a new BugDetailsFragment for book at position i in list
            @Override
            public Fragment getItem (int i) {
                Bug bug = mBugs.get(i);
                //Create a new instance of the BugDetailsFragment with the Bug Id as an argument
                return BugDetailsFragment.newInstance(bug.getId());
            }

            @Override
            public int getCount() {
                return mBugs.size();
            }
        });

        //BUgListFragment now launches BugDetailsActivity with a specific bug id.
        //Get the intent sent to this activity from the BugListFragment
        UUID bugId = (UUID) getIntent()
                .getSerializableExtra(BugDetailsFragment.EXTRA_BUG_ID);

        //Search through the list of bugs until we find the bug with the same id as the one
        //extracted from the intent
        for(int i = 0; i < mBugs.size(); i++) {
            if (mBugs.get(i).getId().equals(bugId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

        //if there is an App Bar (aka Action bar, display the title of the current bug there
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected (int i) {
                Bug bug = mBugs.get(i);
                if (bug.getTitle() != null) {
                    setTitle(bug.getTitle());
                }
            }

            @Override
            public void onPageScrollStateChanged (int i) {

            }

        });
    }*/

}
