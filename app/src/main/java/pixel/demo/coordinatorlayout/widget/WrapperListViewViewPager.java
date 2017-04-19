package pixel.demo.coordinatorlayout.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by pixel on 2017/4/19.
 */

public class WrapperListViewViewPager extends ViewPager {

    private OnListViewScrollListener onListViewScrollListener;
    private int mPosition = 0;
    private boolean isInit = false;
    private final ListView[] listViews = new ListView[8];
    private boolean isCanBack = false;  // 是否可以执行回调

    public void setOnListViewScrollListener(OnListViewScrollListener onListViewScrollListener) {
        this.onListViewScrollListener = onListViewScrollListener;
    }

    public WrapperListViewViewPager(Context context) {
        super(context);
        this.initialize();
    }

    public WrapperListViewViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initialize();
    }

    protected void initialize() {
        this.setOffscreenPageLimit(Integer.MAX_VALUE);

        this.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                try {
                    if (ViewPager.SCROLL_STATE_IDLE == state && onListViewScrollListener != null) { // 滚动结束
                        isCanBack = false;
                        onListViewScrollListener.onScrollChanged(mPosition, listViews[mPosition], (Integer) listViews[mPosition].getTag());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!isInit) {
            for (int i = 0; i < getChildCount(); i++) {
                final ListView listView = getListView(getChildAt(i));
                if (listView != null) {
                    listView.setOnScrollListener(new ListViewScrollListener(listView, onListViewScrollListener));
                    listView.setTag(0);
                    listViews[i] = listView;
                }
            }
        }
        isInit = true;
    }

    private class ListViewScrollListener implements AbsListView.OnScrollListener {
        private SparseArray recordSp = new SparseArray(0);
        private int mCurrentfirstVisibleItem = 0;
        private OnListViewScrollListener onListViewScrollListener;
        private ListView listView;

        public ListViewScrollListener(ListView listView, OnListViewScrollListener onListViewScrollListener) {
            this.listView = listView;
            this.onListViewScrollListener = onListViewScrollListener;
        }

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            if (scrollState > 0) {
                isCanBack = true;
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {    // firstVisibleItem 可见的第一个下标 visibleItemCount可见item总数 totalItemCount列表总数
            mCurrentfirstVisibleItem = firstVisibleItem;
            View firstView = view.getChildAt(0);
            if (null != firstView) {
                ItemRecod itemRecord = (ItemRecod) recordSp.get(firstVisibleItem);
                if (null == itemRecord) {
                    itemRecord = new ItemRecod();
                }
                itemRecord.height = firstView.getHeight();
                itemRecord.top = firstView.getTop();
                recordSp.append(firstVisibleItem, itemRecord);
            }

            if (onListViewScrollListener != null && isCanBack) {
                int scrollY = getScrollY();
                listView.setTag(scrollY);
                onListViewScrollListener.onScrollChanged(mPosition, listView, scrollY);
            }
        }

        private int getScrollY() {
            int height = 0;
            for (int i = 0; i < mCurrentfirstVisibleItem; i++) {
                ItemRecod itemRecod = (ItemRecod) recordSp.get(i);
                height += itemRecod.height;
            }
            ItemRecod itemRecod = (ItemRecod) recordSp.get(mCurrentfirstVisibleItem);
            if (null == itemRecod) {
                itemRecod = new ItemRecod();
            }
            return height - itemRecod.top;
        }

        class ItemRecod {
            int height = 0;
            int top = 0;
        }
    }

    // 递归查找子view里的listview,如果子view里有多个listview只会获取第一个.
    protected ListView getListView(View view) {
        ListView listView = null;
        if (view instanceof ListView) {    // 子View直接是ListView
            return (ListView) view;
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = ((ViewGroup) view);
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View childView = viewGroup.getChildAt(i);
                listView = getListView(childView);
            }
        }
        return listView;
    }

    public interface OnListViewScrollListener {
        void onScrollChanged(int position, ListView listView, int scrollY);
    }

}
