package pl.jolantawojcik.bazapsow;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends FragmentActivity {

    private ViewPager viewPager;
    private PagerAdapter tabsPagerAdapter;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.pager);
        tabsPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabsPagerAdapter);

        PagerTabStrip strip = PagerTabStrip.class.cast(findViewById(R.id.pager_strip));
       // strip.setDrawFullUnderline(false);
    }
}
