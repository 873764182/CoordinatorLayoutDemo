package pixel.demo.coordinatorlayout.behavior;

import android.content.Context;
import android.graphics.Point;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

import pixel.demo.coordinatorlayout.R;

/**
 * Created by pixel on 2017/4/17.
 * <p>
 * ViewPager
 */

public class PagerBehavior extends CoordinatorLayout.Behavior<View> {
    private int windowsWidth = 0, windowsHeight = 0;
    private float scale = 0;

    public PagerBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        wm.getDefaultDisplay().getSize(point);
        windowsWidth = point.x;
        windowsHeight = point.y;

        scale = context.getResources().getDisplayMetrics().density;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency.getId() == R.id.llTab;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        child.setY(dependency.getY() + dependency.getHeight());
        return true;
    }

    @Override
    public void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams params) {
        params.width = windowsWidth;
        params.height = (int) (windowsHeight - (64 * scale));
    }

}
