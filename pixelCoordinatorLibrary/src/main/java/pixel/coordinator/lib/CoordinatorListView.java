package pixel.coordinator.lib;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by pixel on 2017/4/14.
 */

public class CoordinatorListView extends FrameLayout {
    private Context mContext;

    public CoordinatorListView(Context context) {
        super(context);
        onInitialize(context);
    }

    public CoordinatorListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        onInitialize(context);
    }

    protected void onInitialize(Context context) {
        this.mContext = context;

    }

}
