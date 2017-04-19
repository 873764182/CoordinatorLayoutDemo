package pixel.demo.coordinatorlayout.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by pixel on 2017/4/17.
 * <p>
 * 头部Banner视图
 */

public class BannerBehavior extends CoordinatorLayout.Behavior<View> {
    public static boolean isListViewReachTopEdge = true;
    public static Integer moveFlag = 0;

    private CoordinatorLayout.LayoutParams layoutParams = null;
    private int childHeight = 0;
    private int downY = 0, moveY = 0;
    private boolean isOpen = true;

    public BannerBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev) {
        if (isListViewReachTopEdge && isOpen) {
            return true;
        }
        if (isListViewReachTopEdge && !isOpen && moveFlag == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev) {
        if (childHeight <= 0) {
            childHeight = child.getHeight();
        }
        if (layoutParams == null) {
            layoutParams = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        }
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            downY = (int) ev.getRawY();
        }
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            if (downY <= 0) {
                downY = (int) ev.getRawY();
            }
            moveY = (int) (ev.getRawY() - downY);
            if (isOpen && moveY <= 0 && Math.abs(moveY) <= childHeight) {
                layoutParams.topMargin = moveY;
                child.setLayoutParams(layoutParams);
            } else if (!isOpen && moveY >= 0 && moveY - childHeight <= 0) {
                layoutParams.topMargin = moveY - childHeight;
                child.setLayoutParams(layoutParams);
            }
        }
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            if (Math.abs(child.getTop()) > childHeight / 2) {
                layoutParams.topMargin = -childHeight;
                child.setLayoutParams(layoutParams);
                isOpen = false;

                moveFlag = 0;   // 重置ListView滑动标记
            } else {
                layoutParams.topMargin = 0;
                child.setLayoutParams(layoutParams);
                isOpen = true;

                moveFlag = 1;   // 重置ListView滑动标记
            }
        }
        if (child.getTop() >= -childHeight) {
            return true;
        }
        return super.onTouchEvent(parent, child, ev);
    }

}
