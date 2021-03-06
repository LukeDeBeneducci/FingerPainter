package com.example.android.fingerpainter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import static android.graphics.Color.argb;
import static com.example.android.fingerpainter.R.id.blueBar;
import static com.example.android.fingerpainter.R.id.greenBar;
import static com.example.android.fingerpainter.R.id.redBar;
import static com.example.android.fingerpainter.R.id.sizeBar;

public class ColourActivity extends AppCompatActivity {

    private int returnRed;
    private int returnGreen;
    private int returnBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour);

        // retrieve incoming colours
        Bundle incomingBundle = getIntent().getExtras();
        returnRed = incomingBundle.getInt("outgoingRed");
        returnGreen = incomingBundle.getInt("outgoingGreen");
        returnBlue = incomingBundle.getInt("outgoingBlue");

        // set seekbars to incoming values
        SeekBar redBar = (SeekBar) findViewById(R.id.redBar);
        redBar.setProgress(returnRed);
        SeekBar greenBar = (SeekBar) findViewById(R.id.greenBar);
        greenBar.setProgress(returnGreen);
        SeekBar blueBar = (SeekBar) findViewById(R.id.blueBar);
        blueBar.setProgress(returnBlue);

        // set the sample text colour to the incoming values
        final TextView sampleColour = (TextView) findViewById(R.id.colourSample);
        int rgbBrushColour = argb(255, returnRed, returnGreen, returnBlue);
        sampleColour.setTextColor(rgbBrushColour);

        // check redbar for changes, update text colour, and update returning red colour variable.
        redBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                //returning green value = value of progress bar
                returnRed = progress;
                // combine red, green, blue, and alpha for a single colour integer
                int rgbBrushColour = argb(255, returnRed, returnGreen, returnBlue);
                // set the sample colour to the combined colour value
                sampleColour.setTextColor(rgbBrushColour);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // check greenbar for changes, update text colour, and update returning green colour variable.
        greenBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                //returning green value = value of progress bar
                returnGreen = progress;
                // combine red, green, blue, and alpha for a single colour integer
                int rgbBrushColour = argb(255, returnRed, returnGreen, returnBlue);
                // set the sample colour to the combined colour value
                sampleColour.setTextColor(rgbBrushColour);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // check bluebar for changes, update text, and update returing blue colour variable.
        blueBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                //returning blue value = value of progress bar
                returnBlue = progress;
                // combine red, green, blue, and alpha for a single colour integer
                int rgbBrushColour = argb(255, returnRed, returnGreen, returnBlue);
                // set the sample colour to the combined colour value
                sampleColour.setTextColor(rgbBrushColour);
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

    public void onReturnClicked(View v){
        //bundle the returning colour values
        Bundle colourBundle = new Bundle();
        colourBundle.putInt("returningRed", returnRed);
        colourBundle.putInt("returningGreen", returnGreen);
        colourBundle.putInt("returningBlue", returnBlue);

        //start an intent with the colour bundle to return to the main painting activity
        Intent colourResult = new Intent();
        colourResult.putExtras(colourBundle);
        setResult(Activity.RESULT_OK, colourResult);

        //finish this activity
        finish();
    }
}
