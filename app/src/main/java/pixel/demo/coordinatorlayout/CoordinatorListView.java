package pixel.demo.coordinatorlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import pixel.demo.coordinatorlayout.behavior.BannerBehavior;

/**
 * Created by pixel on 2017/4/18.
 */

public class CoordinatorListView extends ListView {
    private float iDownY = 0, iMoveY = 0;

    public CoordinatorListView(Context context) {
        super(context);
    }

    public CoordinatorListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            iDownY = ev.getRawY();
        }
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            if (iDownY <= 0) {
                iDownY = ev.getRawY();
            }
            iMoveY = ev.getRawY() - iDownY;
            if (iMoveY > 0) {    // 手指向下滑动
                BannerBehavior.moveFlag = 1;
            }
            if (iMoveY < 0) {   // 手指向上滑动
                BannerBehavior.moveFlag = 0;
            }
        }
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            iDownY = 0;
            iMoveY = 0;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if (isListViewReachTopEdge(this)) {
            BannerBehavior.isListViewReachTopEdge = true;
        } else {
            BannerBehavior.isListViewReachTopEdge = false;
        }
    }

    public boolean isListViewReachTopEdge(ListView listView) {
        boolean result = false;
        if (listView.getFirstVisiblePosition() == 0) {
            View topChildView = listView.getChildAt(0);
            if (topChildView == null) {
                result = true;
            } else {
                result = topChildView.getTop() == 0;
            }
        }
        return result;
    }

    public boolean isListViewReachBottomEdge(ListView listView) {
        boolean result = false;
        if (listView.getLastVisiblePosition() == (listView.getCount() - 1)) {
            View bottomChildView = listView.getChildAt(listView.getLastVisiblePosition() - listView.getFirstVisiblePosition());
            if (bottomChildView == null) {
                result = true;
            } else {
                result = (listView.getHeight() >= bottomChildView.getBottom());
            }
        }
        return result;
    }
}
