package pixel.demo.coordinatorlayout.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pixel on 2017/4/19.
 */

public class WrapperListView extends ListView {

    private final List<OnScrollListener> onScrollListeners = new ArrayList<>();

    public WrapperListView(Context context) {
        super(context);
    }

    public WrapperListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setOnScrollListener(OnScrollListener l) {
        if (l != null) {
            onScrollListeners.add(l);
        }

        super.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                for (OnScrollListener scrollListener : onScrollListeners) {
                    scrollListener.onScrollStateChanged(view, scrollState);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                for (OnScrollListener scrollListener : onScrollListeners) {
                    scrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
                }
            }
        });
    }

}
