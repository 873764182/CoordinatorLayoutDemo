package pixel.demo.coordinatorlayout.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by pixel on 2017/4/17.
 * <p>
 * 侧滑菜单
 */

public class MenuBehavior extends CoordinatorLayout.Behavior<View> {

    private CoordinatorLayout.LayoutParams layoutParams = null;
    private static final int CV = 50;
    private int downX = 0;
    private int moveX = 0;
    private boolean isOpen = false;
    private int childWidth = 0;
    private View child = null;

    public MenuBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams params) {
        params.leftMargin = -params.width;
    }

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev) {
        if (ev.getRawX() <= CV || (isOpen && ev.getRawX() > childWidth)) {
            return true;
        }
        return super.onInterceptTouchEvent(parent, child, ev);
    }

    @Override
    public boolean onTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev) {
        if (this.child == null) {
            this.child = child;
        }
        if (layoutParams == null) {
            layoutParams = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        }
        if (childWidth <= 0) {
            childWidth = child.getWidth();
        }
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            downX = (int) ev.getRawX();
        }
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            if (downX <= 0) {
                downX = (int) ev.getRawX();
            }
            if (downX <= CV && !isOpen) {
                moveX = (int) (ev.getRawX() - downX);
                if (moveX <= childWidth) {
                    layoutParams.leftMargin = moveX - childWidth;
                    child.setLayoutParams(layoutParams);
                }
            } else if (downX > childWidth && isOpen) {
                moveX = (int) (ev.getRawX() - downX);
                if (moveX <= 0 && Math.abs(moveX) <= childWidth) {
                    layoutParams.leftMargin = -Math.abs(moveX);
                    child.setLayoutParams(layoutParams);
                }
            }
        }
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            if ((!isOpen && Math.abs(moveX) > childWidth / 2) || (isOpen && Math.abs(moveX) < childWidth / 2)) {
                openMenu();
            } else {
                closeMenu();
            }
            if (downX > childWidth + CV * 3 && ev.getRawX() > childWidth + CV * 3) {
                closeMenu();
            }
            moveX = 0;
            downX = 0;
        }
        if (downX < CV || isOpen) {
            return true;
        } else {
            return false;
        }
    }

    public void openMenu() {
        layoutParams.leftMargin = 0;
        isOpen = true;
        child.setLayoutParams(layoutParams);
    }

    public void closeMenu() {
        layoutParams.leftMargin = -childWidth;
        isOpen = false;
        child.setLayoutParams(layoutParams);
    }

    public boolean isOpen() {
        return isOpen;
    }

}
