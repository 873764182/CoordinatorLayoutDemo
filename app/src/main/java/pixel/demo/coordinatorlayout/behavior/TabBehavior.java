package pixel.demo.coordinatorlayout.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

import pixel.demo.coordinatorlayout.R;

/**
 * Created by pixel on 2017/4/17.
 */

public class TabBehavior extends CoordinatorLayout.Behavior<View> {

    public TabBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency.getId() == R.id.llBanner;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        child.setY(dependency.getY() + dependency.getHeight());
        return true;
    }
}
