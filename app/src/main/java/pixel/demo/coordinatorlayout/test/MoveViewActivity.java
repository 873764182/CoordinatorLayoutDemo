package pixel.demo.coordinatorlayout.test;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import pixel.demo.coordinatorlayout.R;

/**
 * http://www.jianshu.com/p/72d45d1f7d55
 */
public class MoveViewActivity extends AppCompatActivity {
    private View mTagView;
    private View mDependentView;

    private CoordinatorLayout.LayoutParams marginLayoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_view);

        mTagView = (View) findViewById(R.id.tagView);
        mDependentView = (View) findViewById(R.id.dependentView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            marginLayoutParams = (CoordinatorLayout.LayoutParams) mTagView.getLayoutParams();
            marginLayoutParams.topMargin = (int) event.getRawY() - mTagView.getHeight() / 2;
            marginLayoutParams.leftMargin = (int) event.getRawX() - mTagView.getWidth() / 2;
            mTagView.setLayoutParams(marginLayoutParams);
        }
        return super.onTouchEvent(event);
    }
}
