package pixel.demo.coordinatorlayout.test;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import pixel.demo.coordinatorlayout.R;

public class BottomSheetBehaviorActivity extends AppCompatActivity {

    protected NestedScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_behavior);

        scrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        Button fab = (Button) findViewById(R.id.button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(scrollView);
                bottomSheetBehavior.setState(
                        bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED ? BottomSheetBehavior.STATE_EXPANDED : BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
    }

}
