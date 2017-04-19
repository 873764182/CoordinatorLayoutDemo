package pixel.demo.coordinatorlayout;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by pixel on 2017/4/18.
 * <p>
 * 自定义可以放进NestedScrollView的ViewPager
 */

public class WrapContentHeightViewPager extends ViewPager {
    private Context context = null;
    private int position = 0;
    private int miniHeight = 500;

    public WrapContentHeightViewPager(Context context) {
        super(context);
        this.context = context;
        this.init();
    }

    public WrapContentHeightViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.init();
    }

    protected void init() {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        wm.getDefaultDisplay().getSize(point);
        miniHeight = point.y / 2;

        this.setOffscreenPageLimit(3);
        this.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                WrapContentHeightViewPager.this.position = position;
                WrapContentHeightViewPager.this.requestLayout();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        try {
            View child = getChildAt(position);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int height = child.getMeasuredHeight();
            if (height < miniHeight) {
                height = miniHeight;
            }
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
