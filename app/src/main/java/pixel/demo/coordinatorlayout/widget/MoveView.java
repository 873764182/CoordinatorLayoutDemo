package pixel.demo.coordinatorlayout.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by pixel on 2017/4/14.
 */

public class MoveView extends View {

    private ViewGroup.MarginLayoutParams layoutParams = null;

    public MoveView(Context context) {
        super(context);

        layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
    }

    public MoveView(Context context, AttributeSet attrs) {
        super(context, attrs);

        layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_UP) {
            if (layoutParams == null) {
                layoutParams = new ViewGroup.MarginLayoutParams(getWidth(), getHeight());
            }
            layoutParams.leftMargin = (int) event.getRawX();
            layoutParams.topMargin = (int) event.getRawY();
            setLayoutParams(layoutParams);
            return true;
        }
        return super.onTouchEvent(event);
    }
}
