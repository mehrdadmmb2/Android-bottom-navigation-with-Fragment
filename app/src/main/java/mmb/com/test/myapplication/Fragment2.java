package mmb.com.test.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment2 extends Fragment {

    private static Fragment2 fragment;


    public static Fragment2 newInstance() {
        if (fragment == null)
            fragment = new Fragment2();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment2, container, false);

        Log.d("mehrdad", "onCreateView:F2 ");
        return v;

    }

}
