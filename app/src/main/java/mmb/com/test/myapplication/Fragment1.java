package mmb.com.test.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Mehrdad on 12/4/2018.
 */

public class Fragment1 extends Fragment {

    private static Fragment1 fragment;


    public static Fragment1 newInstance() {
        if (fragment == null)
            fragment = new Fragment1();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment1, container, false);
        Log.d("mehrdad", "onCreateView:F1 ");
        return v;


    }




}
