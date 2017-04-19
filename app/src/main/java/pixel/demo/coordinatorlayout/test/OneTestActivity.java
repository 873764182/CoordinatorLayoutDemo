package pixel.demo.coordinatorlayout.test;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import pixel.demo.coordinatorlayout.R;

public class OneTestActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private CoordinatorLayout mCoordinatorLayout;
    private AppBarLayout mAppBarLayout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private ImageView mImageView;
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private NestedScrollView mNestedScrollView;
    private ViewPager mViewPager;
    private LinearLayout mLlDrawerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_test);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        mImageView = (ImageView) findViewById(R.id.imageView);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mNestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mLlDrawerView = (LinearLayout) findViewById(R.id.llDrawerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);

        setSupportActionBar(mToolbar);
        mViewPager.setAdapter(new ViewPagerAdapter());
        mTabLayout.setupWithViewPager(mViewPager);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close);
        drawerToggle.syncState();
        mDrawerLayout.addDrawerListener(drawerToggle);

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
    }

    private class ViewPagerAdapter extends PagerAdapter {
        private List<View> viewList = new ArrayList<>();

        public ViewPagerAdapter() {
            viewList.add(getLayoutInflater().inflate(R.layout.view_paget_item_1, null));
            viewList.add(getLayoutInflater().inflate(R.layout.view_paget_item_2, null));
            viewList.add(getLayoutInflater().inflate(R.layout.view_paget_item_3, null));

            ListView listView = (ListView) viewList.get(1).findViewById(R.id.listView);
            final String[] strings = new String[40];
            for (int i = 0; i < strings.length; i++) {
                strings[i] = "item " + i;
            }
            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(OneTestActivity.this, android.R.layout.simple_expandable_list_item_1, strings);
            listView.setAdapter(arrayAdapter);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    strings[0] = "测试";
                    arrayAdapter.notifyDataSetChanged();
                }
            }, 5 * 1000);
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
            return "标签-" + position;
        }
    }

}
