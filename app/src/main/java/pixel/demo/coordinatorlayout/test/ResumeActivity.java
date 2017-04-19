package pixel.demo.coordinatorlayout.test;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pixel.demo.coordinatorlayout.R;

public class ResumeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // 设置返回主页的按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        // TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        // ViewPager
        ViewPager mPager = (ViewPager) findViewById(R.id.viewPager);
        ResumeAdapter mPagerAdapter = new ResumeAdapter(this);
        mPager.setAdapter(mPagerAdapter);
        tabLayout.setupWithViewPager(mPager);
        // ViewPager切换时NestedScrollView滑动到顶部
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((NestedScrollView) findViewById(R.id.nestedScrollView)).scrollTo(0, 0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public class ResumeAdapter extends PagerAdapter {
        List<View> viewList = new ArrayList<>();

        public ResumeAdapter(ResumeActivity resumeActivity) {
            viewList.add(resumeActivity.getLayoutInflater().inflate(R.layout.view_pager_item, null));
            viewList.add(resumeActivity.getLayoutInflater().inflate(R.layout.content_view_offset_behavior, null));
            viewList.add(resumeActivity.getLayoutInflater().inflate(R.layout.activity_scrolling, null));
            viewList.add(resumeActivity.getLayoutInflater().inflate(R.layout.content_view_offset_behavior, null));
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
            container.addView(viewList.get(position));
            return viewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
            container.removeView(viewList.get(position));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "TAB 1";
                case 1:
                    return "TAB 2";
                case 2:
                    return "TAB 3";
                case 3:
                    return "TAB 4";
            }
            return null;
        }
    }

}
