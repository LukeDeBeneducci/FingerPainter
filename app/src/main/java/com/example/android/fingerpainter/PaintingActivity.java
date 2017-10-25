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
    protected int redValue;
    protected int greenValue;
    protected int blueValue;
    FingerPainterView myFingerPainterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painting);

        myFingerPainterView = new FingerPainterView(this);
        LinearLayout paintingView = (LinearLayout) findViewById(R.id.paintingLayout);
        paintingView.addView(myFingerPainterView);

        brushSize = myFingerPainterView.getBrushWidth();
        brushColour = myFingerPainterView.getColour();

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
        outState.putInt("brushSize",brushSize);
        outState.putInt("brushColour",brushColour);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        this.brushSize = savedInstanceState.getInt("brushSize");
        this.brushColour = savedInstanceState.getInt("brushColour");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ACTIVITY_COLOUR_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                redValue = bundle.getInt("returningRed");
                greenValue = bundle.getInt("returningGreen");
                blueValue = bundle.getInt("returningBlue");

                brushColour = argb(255, redValue, greenValue, blueValue);
                myFingerPainterView.setColour(brushColour);
            }
        } else if (requestCode == ACTIVITY_SIZE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                //retrieve size and shape
                brushSize = bundle.getInt("returningSize");
                int returningShape = bundle.getInt("returningShape");

                //set brush size
                // brushSize = returningSize;
                myFingerPainterView.setBrushWidth(brushSize);

                // set brush shape
                if (returningShape == 0) { // square nib
                    myFingerPainterView.setBrush(Paint.Cap.SQUARE);
                } else if (returningShape == 1) { // round nib
                    myFingerPainterView.setBrush(Paint.Cap.ROUND);
                }
            }
        }
    }


    protected void onColourClicked(View v){
        Bundle colourOut = new Bundle();
        colourOut.putInt("outgoingRed", redValue);
        colourOut.putInt("outgoingGreen", greenValue);
        colourOut.putInt("outgoingBlue", blueValue);


        Intent intentColourActivity = new Intent(PaintingActivity.this, ColourActivity.class);
        intentColourActivity.putExtras(colourOut);
        startActivityForResult(intentColourActivity, ACTIVITY_COLOUR_REQUEST_CODE);

    }

    protected void onSaveClicked(View v){

    }

    protected void onLoadClicked(View v){

    }

    protected void onSizeClicked(View v){
        Bundle sizeOut = new Bundle();
        sizeOut.putInt("outgoingSize", brushSize);

        Intent intentSizeActivity = new Intent(PaintingActivity.this, SizeActivity.class);
        intentSizeActivity.putExtras(sizeOut);
        startActivityForResult(intentSizeActivity, ACTIVITY_SIZE_REQUEST_CODE);
    }

}
