package mmb.com.test.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class PagerActivity extends AppCompatActivity {

    mViewPager viewpager;

    public static boolean fragment1GetData = false;
    public static boolean fragment2GetData = false;
    public static boolean fragment3GetData = false;

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
                    break;
                case R.id.navigation_dashboard:
                    viewpager.setCurrentItem(1, false);
                    break;
                case R.id.navigation_notifications:
                    viewpager.setCurrentItem(2, false);
                    break;
            }

            return true;
        }
    };

}
