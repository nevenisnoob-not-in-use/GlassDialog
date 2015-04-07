package jp.co.drecom.customview;

import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import jp.co.drecom.glassdialog.*;


public class MainActivity extends ActionBarActivity{
    private RelativeLayout mainLayout;
    private GlassDialog mGlassDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);
//        mainLayout.setOnClickListener(this);
        mGlassDialog = (GlassDialog) findViewById(R.id.glass);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_UP) {
            //TODO
            //generate the glass dialog
            Log.v("TAG", "action up position: x is " + event.getX() + " y is " + event.getY());

            mGlassDialog.startAnim(event);

        }
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            Log.v("TAG", "action down position: x is " + event.getX() + " y is " + event.getY());

        }
        return super.onTouchEvent(event);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

//    @Override
//    public void onClick(View v) {
//        Toast.makeText(this,"onClicked", Toast.LENGTH_SHORT).show();
//
//    }
}
