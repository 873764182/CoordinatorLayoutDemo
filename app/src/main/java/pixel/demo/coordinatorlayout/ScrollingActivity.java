package pixel.demo.coordinatorlayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import pixel.demo.coordinatorlayout.test.AylsonHomeActivity;
import pixel.demo.coordinatorlayout.test.BottomSheetBehaviorActivity;
import pixel.demo.coordinatorlayout.test.CoorAndDrawerActivity;
import pixel.demo.coordinatorlayout.test.FloatingActionButtonActivity;
import pixel.demo.coordinatorlayout.test.MoveViewActivity;
import pixel.demo.coordinatorlayout.test.OneTestActivity;
import pixel.demo.coordinatorlayout.test.ResumeActivity;
import pixel.demo.coordinatorlayout.test.SwipeDismissBehaviorActivity;
import pixel.demo.coordinatorlayout.test.ViewOffsetBehaviorActivity;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onViewClick(View view) {
        Button button = (Button) view;

        if (button.getText().toString().equalsIgnoreCase("BottomSheetBehaviorActivity")) {
            startActivity(new Intent(this, BottomSheetBehaviorActivity.class));
        }

        if (button.getText().toString().equalsIgnoreCase("SwipeDismissBehaviorActivity")) {
            startActivity(new Intent(this, SwipeDismissBehaviorActivity.class));
        }

        if (button.getText().toString().equalsIgnoreCase("FloatingActionButtonActivity")) {
            startActivity(new Intent(this, FloatingActionButtonActivity.class));
        }

        if (button.getText().toString().equalsIgnoreCase("ViewOffsetBehaviorActivity")) {
            startActivity(new Intent(this, ViewOffsetBehaviorActivity.class));
        }

        if (button.getText().toString().equalsIgnoreCase("MoveViewActivity")) {
            startActivity(new Intent(this, MoveViewActivity.class));
        }

        if (button.getText().toString().equalsIgnoreCase("AylsonHomeActivity")) {
            startActivity(new Intent(this, AylsonHomeActivity.class));
        }

        if (button.getText().toString().equalsIgnoreCase("ResumeActivity")) {
            startActivity(new Intent(this, ResumeActivity.class));
        }

        if (button.getText().toString().equalsIgnoreCase("CoorAndDrawerActivity")) {
            startActivity(new Intent(this, CoorAndDrawerActivity.class));
        }

        if (button.getText().toString().equalsIgnoreCase("OneTestActivity")) {
            startActivity(new Intent(this, OneTestActivity.class));
        }
    }
}
