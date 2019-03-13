package mmb.com.test.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewAdapter extends FragmentPagerAdapter {


    public ViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0)
            return Fragment1.newInstance();
        if (position == 1)
            return Fragment2.newInstance();
        if (position == 2)
            return Fragment3.newInstance();

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
