package pixel.demo.coordinatorlayout.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by pixel on 2017/4/19.
 */

public class WrapperListView2 extends RecyclerView {

    private OnListViewScrollChangedListener scrollChangedListener;

    public void setScrollChangedListener(OnListViewScrollChangedListener scrollChangedListener) {
        this.scrollChangedListener = scrollChangedListener;
    }

    public WrapperListView2(Context context) {
        super(context);
    }

    public WrapperListView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
    }
}
