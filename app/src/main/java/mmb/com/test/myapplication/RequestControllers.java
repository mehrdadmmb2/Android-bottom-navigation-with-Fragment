package mmb.com.test.myapplication;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.greenrobot.eventbus.EventBus;

import cz.msebera.android.httpclient.Header;

public class RequestControllers {

    private AsyncHttpClient client = new AsyncHttpClient();
    private String url = "";

    void getDataFragment1() {

        PagerActivity.fragment1GetData = true;

        url = "https://www.aparadt.com//etc/api/mostviewedvideos";
        client.get(url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                EventBus.getDefault().post(new F1Model("F1 From Controller error"));
                PagerActivity.fragment1GetData = false;
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                EventBus.getDefault().post(new F1Model("F1 From Controller Passed"));
            }
        });
    }

     void getDataForFragment3() {
        PagerActivity.fragment3GetData = true;

        client.get("https://www.aparat.com//etc/api/mostviewedvideos", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                EventBus.getDefault().post(new F3Model("F3 Error From Controller" + responseString));
                PagerActivity.fragment3GetData = false;

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                EventBus.getDefault().post(new F3Model("F3 Passed From Controller"));

            }
        });
    }

     void getDataForFragment2() {
        PagerActivity. fragment2GetData = true;
        client.get("https://www.aparat.com//etc/api/mostviewedvideos", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                EventBus.getDefault().post(new F2Model("F2 Error From Controller" + responseString));
                PagerActivity.fragment2GetData = false;

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                EventBus.getDefault().post(new F2Model("F2 Passed From Controller"));
            }
        });
    }

}
