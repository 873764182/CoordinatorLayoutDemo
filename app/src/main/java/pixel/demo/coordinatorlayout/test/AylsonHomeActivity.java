package pixel.demo.coordinatorlayout.test;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import pixel.demo.coordinatorlayout.R;

public class AylsonHomeActivity extends AppCompatActivity {
    private LinearLayout mLlBanner;
    private LinearLayout mLlTab;
    private ViewPager mVpList;
    private LinearLayout mLlMenu;

    private final List<View> viewList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aylson_home);

        mLlBanner = (LinearLayout) findViewById(R.id.llBanner);
        mLlTab = (LinearLayout) findViewById(R.id.llTab);
        mVpList = (ViewPager) findViewById(R.id.vpList);
        mLlMenu = (LinearLayout) findViewById(R.id.llMenu);

        viewList.add(getLayoutInflater().inflate(R.layout.view_pager_item, mVpList, false));
        viewList.add(getLayoutInflater().inflate(R.layout.view_pager_item, mVpList, false));
        viewList.add(getLayoutInflater().inflate(R.layout.view_pager_item, mVpList, false));

        mVpList.setAdapter(new PagerAdapter());

//        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) viewList.get(0).findViewById(R.id.swipeRefreshLayout);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        swipeRefreshLayout.setRefreshing(false);
//                    }
//                }, 2000);
//            }
//        });
        ListView listView = (ListView) viewList.get(0).findViewById(R.id.listView);
        String[] strings = new String[20];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = "ArrayAdapter " + i;
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, strings);
        listView.setAdapter(arrayAdapter);
    }

    private class PagerAdapter extends android.support.v4.view.PagerAdapter {

        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));
        }
    }

}
