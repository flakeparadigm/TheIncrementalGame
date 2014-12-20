package com.flakeparadigm.theincrementalgame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends Activity {

    private int clickCount = 0;
    private int incrementFactor = 1;
    private TextView countView, welcomeMessage;
    private ProgressBar progress;
    private Button autoButton;
    private AlertDialog quitDialog;

    // autoincrementing
    private Handler h = new Handler();
    int delay = 1000; //milliseconds
    boolean doIncrement = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // start counter & progress bar
        countView = (TextView) findViewById(R.id.click_count);
        progress = (ProgressBar) findViewById(R.id.progress_bar);
        updateCount();

        // get subtitle view
        welcomeMessage = (TextView) findViewById(R.id.subtitle_view);

        // create quit dialog
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

        // get auto increment button
        autoButton = (Button) findViewById(R.id.auto_button);
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

    public void autoIncrementToggle(View view) {
        if(doIncrement) {
            autoButton.setText(R.string.auto_click_off);
            doIncrement = false;

        } else {
            // create auto incrementer
            h.postDelayed(new Runnable(){
                public void run(){
                    if( doIncrement ) {
                        increment(null);
                        h.postDelayed(this, delay);
                    }
                }
            }, delay);

            autoButton.setText(R.string.auto_click_on);
            doIncrement = true;
        }
    }
    public void increment(View view) {
        clickCount += incrementFactor;
        updateCount();

        if( clickCount == 100 ) {
            incrementFactor *= 2;
            if( incrementFactor == 2 ) {
                welcomeMessage.setText(R.string.zlsa_text);
            } else {
                welcomeMessage.setText(R.string.secret_prestige_text);
            }
        }
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

        int percent;
        if( clickCount > 0 ) {
            percent = (int) (Math.log(clickCount) * 20);
        } else {
            percent = 0;
        }

        progress.setProgress(percent);
    }

}
