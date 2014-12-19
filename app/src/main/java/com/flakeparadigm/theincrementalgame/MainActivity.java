package com.flakeparadigm.theincrementalgame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity {

    private int clickCount = 0;
    private int incrementFactor = 1;
    private TextView countView;
    private AlertDialog quitDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countView = (TextView) findViewById(R.id.click_count);
        updateCount();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.quit_message);
        builder.setTitle(R.string.quit_title);
        builder.setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                decrement();
            }
        });

        quitDialog = builder.create();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    public void increment(View view) {
        clickCount += incrementFactor;
        updateCount();
    }
    public void quitButton(View view) {
        quitDialog.show();
    }
    public void decrement() {
        clickCount--;
        updateCount();
    }

    private void updateCount() {
        countView.setText(""+clickCount);
    }

}
