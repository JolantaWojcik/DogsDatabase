package pl.jolantawojcik.bazapsow;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Jola on 9/5/2015.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    private String[] tabs = {"Dodaj psa", "Usuń psa", "Lista psów", "Zaginione", "Do adopcji"};

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch(index){
            case 0:
                return new AddDog();
            case 1:
                return new RemoveDog();
            case 2:
                return new DogsList();
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
