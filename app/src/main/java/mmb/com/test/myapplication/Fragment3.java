package mmb.com.test.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Mehrdad on 12/4/2018.
 */

public class Fragment3 extends Fragment {

    private static Fragment3 fragment;


    public static Fragment3 newInstance() {
        if (fragment == null)
            fragment = new Fragment3();
        return fragment;
    }

    TextView txtText;
    TextView txtTry;
    ProgressBar pr;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment3, container, false);
        txtText = v.findViewById(R.id.txtText);
        txtTry = v.findViewById(R.id.txtTry);
        pr = v.findViewById(R.id.pr);
        txtTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pr.setVisibility(View.VISIBLE);
                txtText.setText("wait ...");
                txtTry.setVisibility(View.GONE);
                EventBus.getDefault().post(new TryModel("f3"));
            }
        });
        Log.d("mehrdad", "onCreateView:F3 ");
        return v;

    }

    @Subscribe
    public void getData(F3Model model) {
        txtText.setText(model.getF3Model());
        pr.setVisibility(View.GONE);
        if (model.getF3Model().contains("ror")) {
            txtTry.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


}
