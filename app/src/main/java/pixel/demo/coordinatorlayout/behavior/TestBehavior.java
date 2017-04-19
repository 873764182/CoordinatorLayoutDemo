package pixel.demo.coordinatorlayout.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import pixel.demo.coordinatorlayout.R;

/**
 * Created by pixel on 2017/4/14.
 */

public class TestBehavior extends CoordinatorLayout.Behavior<View> {
    private int width;

    public TestBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics display = context.getResources().getDisplayMetrics();
        width = display.widthPixels;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        Log.e("TestBehavior", "layoutDependsOn");
        if (dependency.getId() == R.id.tagView) {
            return true;
        }
        return super.layoutDependsOn(parent, child, dependency);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        Log.e("TestBehavior", "onDependentViewChanged");
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        layoutParams.topMargin = dependency.getTop();
        layoutParams.leftMargin = width - dependency.getLeft() - child.getWidth() / 2;
        child.setLayoutParams(layoutParams);
        return true;
    }

}
