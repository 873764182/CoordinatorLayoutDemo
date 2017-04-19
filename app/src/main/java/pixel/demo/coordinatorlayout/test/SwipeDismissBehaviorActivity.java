package pixel.demo.coordinatorlayout.test;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import pixel.demo.coordinatorlayout.R;

public class SwipeDismissBehaviorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_dismiss_behavior);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Snackbar", Snackbar.LENGTH_SHORT).setAction("Snackbar 1", null).show();
            }
        });
    }
}
