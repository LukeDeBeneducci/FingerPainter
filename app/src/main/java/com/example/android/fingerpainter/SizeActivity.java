package com.example.android.fingerpainter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

public class SizeActivity extends AppCompatActivity {

    private int returnSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_size);

        // extract the previous size
        Bundle incomingBundle = getIntent().getExtras();
        returnSize = incomingBundle.getInt("outgoingSize");

        // set progress bar to previous size
        SeekBar sizeBar = (SeekBar) findViewById(R.id.sizeBar);
        sizeBar.setProgress(returnSize);

        // check the seek bar for changes in size
        sizeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                returnSize = progress;
            }


            public void onStartTrackingTouch(SeekBar seekBar) {
                    // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }





    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    // on square set
    public void onSquareClicked(View v){
        int returnShape = 0;

        // set up returning sizeBundle
        Bundle sizeBundle = new Bundle();
        sizeBundle.putInt("returningSize", returnSize);
        sizeBundle.putInt("returningShape", returnShape);

        //start returning Intent
        Intent sizeResult = new Intent();
        sizeResult.putExtras(sizeBundle);
        setResult(Activity.RESULT_OK, sizeResult);

        finish();
    }

    // on round set
    public void onRoundClicked(View v){
        int returnShape = 1;

        // set up returning sizeBundle
        Bundle sizeBundle = new Bundle();
        sizeBundle.putInt("returningSize", returnSize);
        sizeBundle.putInt("returningShape", returnShape);

        // start retuning Intent
        Intent sizeResult = new Intent();
        sizeResult.putExtras(sizeBundle);
        setResult(Activity.RESULT_OK, sizeResult);

        finish();
    }
}
