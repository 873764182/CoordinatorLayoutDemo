package pixel.demo.coordinatorlayout.test;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import pixel.demo.coordinatorlayout.R;
import pixel.demo.coordinatorlayout.widget.WrapperListViewViewPager;

public class TwoTestActivity extends Activity {
    private WrapperListViewViewPager mViewPager;
    private LinearLayout mHeadLayout;
    private RelativeLayout mTitleLayout;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_test);

        mViewPager = (WrapperListViewViewPager) findViewById(R.id.viewPager);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mHeadLayout = (LinearLayout) findViewById(R.id.headLayout);
        mTitleLayout = (RelativeLayout) findViewById(R.id.titleLayout);

        mViewPager.setAdapter(new PagerAdapter());
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setOnListViewScrollListener(new WrapperListViewViewPager.OnListViewScrollListener() {

            @Override
            public void onScrollChanged(int position, ListView listView, int scrollY) {
                int hiddenHeight = mHeadLayout.getHeight() - mTabLayout.getHeight();
                if (scrollY <= hiddenHeight) {
                    mHeadLayout.setY(-scrollY);
                } else {
                    mHeadLayout.setY(-(mHeadLayout.getHeight() - mTabLayout.getHeight()));
                }
            }
        });
    }

    protected class PagerAdapter extends android.support.v4.view.PagerAdapter {
        private final List<View> viewList = new ArrayList<>();

        public PagerAdapter() {
            viewList.add(getLayoutInflater().inflate(R.layout.pager_view_1, mViewPager, false));
            viewList.add(getLayoutInflater().inflate(R.layout.pager_view_2, mViewPager, false));
            viewList.add(getLayoutInflater().inflate(R.layout.pager_view_3, mViewPager, false));

            String[] strings = new String[20];
            for (int i = 0; i < strings.length; i++) {
                strings[i] = "item " + i;
            }
            ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(TwoTestActivity.this, android.R.layout.simple_expandable_list_item_1, strings);
            ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(TwoTestActivity.this, android.R.layout.simple_expandable_list_item_1, strings);
            ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(TwoTestActivity.this, android.R.layout.simple_expandable_list_item_1, strings);

            ListView listView1 = ((ListView) viewList.get(0).findViewById(R.id.wrapperListView));
            ListView listView2 = ((ListView) viewList.get(1).findViewById(R.id.wrapperListView));
            ListView listView3 = ((ListView) viewList.get(2).findViewById(R.id.wrapperListView));

            listView1.addHeaderView(getLayoutInflater().inflate(R.layout.view_list_view_head, listView1, false));
            listView2.addHeaderView(getLayoutInflater().inflate(R.layout.view_list_view_head, listView2, false));
            listView3.addHeaderView(getLayoutInflater().inflate(R.layout.view_list_view_head, listView3, false));

            listView1.setAdapter(arrayAdapter1);
            listView2.setAdapter(arrayAdapter2);
            listView3.setAdapter(arrayAdapter3);

            for (View view : viewList) {
                final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
                swipeRefreshLayout.setProgressViewEndTarget(true, 1000);
                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        }, 1000);
                    }
                });
            }
        }

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
            View view = viewList.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "标签 " + position;
        }
    }
}
