package edu.andrews.kundani.aupress;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;


public class ShoppingCartActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {

        return new ShoppingCartFragment();
    }


}
