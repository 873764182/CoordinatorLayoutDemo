package pixel.demo.coordinatorlayout.test;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import pixel.demo.coordinatorlayout.R;

public class NestedRefreshActivity extends Activity {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mListView;
    private View mHeadView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_refresh);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mListView = (ListView) findViewById(R.id.listView);
        mHeadView = (View) findViewById(R.id.headView);


//        mSwipeRefreshLayout.setProgressViewOffset(true, 1000, 300);
//        mSwipeRefreshLayout.setDistanceToTriggerSync( 300);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setProgressViewEndTarget(true, mHeadView.getHeight() + 200);
            }
        }, 2000);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });

        String[] strings = new String[20];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = "item " + i;
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, strings);
        mListView.setAdapter(arrayAdapter);
    }
}
