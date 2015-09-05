package pl.jolantawojcik.bazapsow;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends FragmentActivity {

    final ActionBar actionBar = getActionBar();
    private ViewPager viewPager;
    private PagerAdapter tabsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewPager = (ViewPager) findViewById(R.id.pager);
        tabsPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabsPagerAdapter);
    }
}
