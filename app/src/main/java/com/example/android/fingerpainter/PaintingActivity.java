package com.example.android.fingerpainter;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import static android.graphics.Color.argb;


public class PaintingActivity extends AppCompatActivity {

    static final int ACTIVITY_SIZE_REQUEST_CODE = 1;
    static final int ACTIVITY_COLOUR_REQUEST_CODE = 2;
    protected int brushColour;
    protected int brushSize;
    protected int brushShape;
    protected int redValue;
    protected int greenValue;
    protected int blueValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painting);

        //find the paintingview linear layout
        LinearLayout paintingView = (LinearLayout) findViewById(R.id.paintingLayout);

        // create the fingerpainterview programatically
        FingerPainterView myFingerPainterView = new FingerPainterView(this);
        myFingerPainterView.setId(R.id.myFingerPainterViewId);
        paintingView.addView(myFingerPainterView);

        //set brush size and colour
        brushSize = myFingerPainterView.getBrushWidth();
        brushColour = myFingerPainterView.getColour();

        //myFingerPainterView.load(intent.getData());

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

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        //find fingerpainterview via its ID
        FingerPainterView myFingerPainterView = (FingerPainterView) findViewById(R.id.myFingerPainterViewId);

        //save outgoing variables
        brushSize = myFingerPainterView.getBrushWidth();
        brushColour = myFingerPainterView.getColour();
        outState.putInt("brushSize", brushSize);
        outState.putInt("brushColour", brushColour);
        outState.putInt("brushShape", brushShape);
        outState.putInt("redValue", redValue);
        outState.putInt("greenValue", greenValue);
        outState.putInt("blueValue", blueValue);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        //find fingerpainterview via its ID
        FingerPainterView myFingerPainterView = (FingerPainterView) findViewById(R.id.myFingerPainterViewId);

        //retrieve saved variables
        this.brushSize = savedInstanceState.getInt("brushSize");
        this.brushColour = savedInstanceState.getInt("brushColour");
        this.brushShape = savedInstanceState.getInt("brushShape");
        this.redValue = savedInstanceState.getInt("redValue");
        this.greenValue = savedInstanceState.getInt("greenValue");
        this.blueValue = savedInstanceState.getInt("blueValue");

        // set brushshape, colour, and size
        if (brushShape == 0) { // square nib
            myFingerPainterView.setBrush(Paint.Cap.SQUARE);
        } else if (brushShape == 1) { // round nib
            myFingerPainterView.setBrush(Paint.Cap.ROUND);
        }
        myFingerPainterView.setBrushWidth(brushSize);
        myFingerPainterView.setColour(brushColour);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //find fingerpainterview via its ID
        FingerPainterView myFingerPainterView = (FingerPainterView) findViewById(R.id.myFingerPainterViewId);

        // check if activity has returned from colour or size
        if (requestCode == ACTIVITY_COLOUR_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();

                //retrieve colours
                int returningRed = bundle.getInt("returningRed");
                int returningGreen = bundle.getInt("returningGreen");
                int returningBlue = bundle.getInt("returningBlue");

                //set global values
                redValue = returningRed;
                greenValue = returningGreen;
                blueValue = returningBlue;
                brushColour = argb(255, redValue, greenValue, blueValue);

                // set brush colour
                myFingerPainterView.setColour(brushColour);
            }
        } else if (requestCode == ACTIVITY_SIZE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                //retrieve size and shape
                int returningSize = bundle.getInt("returningSize");
                int returningShape = bundle.getInt("returningShape");

                //set global values
                brushSize = returningSize;
                brushShape = returningShape;

                //set brush size
                myFingerPainterView.setBrushWidth(brushSize);

                // set brush shape
                if (brushShape == 0) { // square nib
                    myFingerPainterView.setBrush(Paint.Cap.SQUARE);
                } else if (brushShape == 1) { // round nib
                    myFingerPainterView.setBrush(Paint.Cap.ROUND);
                }
            }
        }
    }


    protected void onColourClicked(View v){
        // bundle outgoing colours
        Bundle colourOut = new Bundle();
        colourOut.putInt("outgoingRed", redValue);
        colourOut.putInt("outgoingGreen", greenValue);
        colourOut.putInt("outgoingBlue", blueValue);

        //start new colour intent
        Intent intentColourActivity = new Intent(PaintingActivity.this, ColourActivity.class);
        intentColourActivity.putExtras(colourOut);
        startActivityForResult(intentColourActivity, ACTIVITY_COLOUR_REQUEST_CODE);

    }

    protected void onSaveClicked(View v){

    }

    protected void onSizeClicked(View v){
        // bundle outgoing size
        Bundle sizeOut = new Bundle();
        sizeOut.putInt("outgoingSize", brushSize);

        // start new size intent
        Intent intentSizeActivity = new Intent(PaintingActivity.this, SizeActivity.class);
        intentSizeActivity.putExtras(sizeOut);
        startActivityForResult(intentSizeActivity, ACTIVITY_SIZE_REQUEST_CODE);
    }

}
