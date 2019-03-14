package mmb.com.test.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import cz.msebera.android.httpclient.Header;

public class PagerActivity extends AppCompatActivity {

    mViewPager viewpager;

    public boolean fragment1GetData = false;
    public boolean fragment2GetData = false;
    public boolean fragment3GetData = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        viewpager = findViewById(R.id.viewpager);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewpager.setEnableSwipe(false);
        viewpager.setOffscreenPageLimit(3);
        ViewAdapter adapter = new ViewAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(1);
        navigation.setSelectedItemId(R.id.navigation_dashboard);


    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewpager.setCurrentItem(0, false);
                    if (!fragment1GetData)
                        getDataForFragment1();
                    break;
                case R.id.navigation_dashboard:
                    viewpager.setCurrentItem(1, false);
                    if (!fragment2GetData)
                        getDataForFragment2();
                    break;
                case R.id.navigation_notifications:
                    viewpager.setCurrentItem(2, false);
                    if (!fragment3GetData)
                        getDataForFragment3();
                    break;
            }

            return true;
        }
    };

    public void getDataForFragment1() {
        fragment1GetData = true;

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://www.aparadt.com//etc/api/mostviewedvideos", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(PagerActivity.this, "F1 Fail", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new F1Model("F1 Error" + responseString));
                fragment1GetData = false;

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                EventBus.getDefault().post(new F1Model("F1 Passed"));

            }
        });
    }

    public void getDataForFragment2() {
        fragment2GetData = true;

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://www.aparat.com//etc/api/mostviewedvideos", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(PagerActivity.this, "F1 Fail", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new F2Model("F2 Error " + responseString));
                fragment2GetData = false;

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                EventBus.getDefault().post(new F2Model("F2 Passed"));
            }
        });
    }

    public void getDataForFragment3() {
        fragment3GetData = true;

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://www.aparat.com//etc/api/mostviewedvideos", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(PagerActivity.this, "F1 Fail", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new F3Model("F3 Error " + responseString));
                fragment3GetData = false;

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                EventBus.getDefault().post(new F3Model("F3 Passed"));

            }
        });
    }

    @Subscribe
    public void refreshData(TryModel model) {
        switch (model.getTryAgain()) {
            case "f1":
                getDataForFragment1();
                break;
            case "f2":
                getDataForFragment2();
                break;
            case "f3":
                getDataForFragment3();
                break;
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
