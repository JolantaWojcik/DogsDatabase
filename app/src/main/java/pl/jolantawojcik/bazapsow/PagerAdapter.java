package pl.jolantawojcik.bazapsow;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Jola on 9/5/2015.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    private String[] tabs = {"Lista psów", "Dodaj psa", "Usuń psa", "Zaginione", "Do adopcji"};

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch(index){
            case 0:
                return new DogsList();
            case 1:
                return new AddDog();
            case 2:
                return new RemoveDog();
            case 3:
                return new MissingDogs();
            case 4:
                return new Addoption();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }
}
