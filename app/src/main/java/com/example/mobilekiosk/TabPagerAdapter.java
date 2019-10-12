package com.example.mobilekiosk;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    // Count number of tabs
    private int tabCount;

    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        // Returning the current tabs
        switch (position) {
            case 0:
                newmenuFrag tabFragment1 = new newmenuFrag();
                return tabFragment1;
            case 1:
                singlemenuFrag tabFragment2 = new singlemenuFrag();
                return tabFragment2;
            case 2:
                setmenuFrag tabFragment3 = new setmenuFrag();
                return tabFragment3;
            case 3:
                sidemenuFrag tabFragment4 = new sidemenuFrag();
                return tabFragment4;
            case 4:
                drinkmenuFrag tabFragment5 = new drinkmenuFrag();
                return tabFragment5;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
