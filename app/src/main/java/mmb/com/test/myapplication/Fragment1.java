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

public class Fragment1 extends Fragment {

    private static Fragment1 fragment;

    TextView txtText;
    TextView txtTry;
    ProgressBar pr;

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
        txtText = v.findViewById(R.id.txtText);
        txtTry = v.findViewById(R.id.txtTry);
        txtTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pr.setVisibility(View.VISIBLE);
                txtText.setText("wait ...");
                txtTry.setVisibility(View.GONE);
                EventBus.getDefault().post(new TryModel("f1"));
            }
        });
        pr = v.findViewById(R.id.pr);
        return v;


    }

    @Subscribe
    public void getData(F1Model model) {
        txtText.setText(model.getF1Model());
        pr.setVisibility(View.GONE);
        if (model.getF1Model().contains("ror")) {
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
