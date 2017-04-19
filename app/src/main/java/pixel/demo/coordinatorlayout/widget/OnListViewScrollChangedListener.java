package pixel.demo.coordinatorlayout.widget;

import android.widget.ListView;

/**
 * Created by pixel on 2017/4/19.
 */

public interface OnListViewScrollChangedListener {
    void onScrollChanged(ListView listView, int scrollX, int scrollY, int oldScrollX, int oldScrollY);
}
