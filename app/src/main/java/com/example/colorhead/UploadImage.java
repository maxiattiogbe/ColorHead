package com.example.colorhead;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class UploadImage extends AppCompatActivity {

    private Button uploadImageButton;
    private ImageView imageView;
    private TextView textView;
    private Bitmap bitmap;
  private ArrayList<Sample> data2 = new ArrayList<Sample>();

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);
        readRGBData();
        uploadImageButton = (Button) findViewById(R.id.uploadImageButton);
        imageView = (ImageView) findViewById(R.id.uploadImageImageView);
        textView = (TextView) findViewById(R.id.resultText);

        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache(true);

        //Make ImageView responsive to touch
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent){
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_MOVE){
                  bitmap = imageView.getDrawingCache();
                  int pixel = bitmap.getPixel((int) motionEvent.getX(), (int) motionEvent.getY());

                  int r = Color.red(pixel);
                  int g = Color.green(pixel);
                  int b = Color.blue(pixel);

                    Model pHModel = new Model();

                     //Putting the data ArrayList into the model
                    pHModel.setData(data2);

                    //Fit cubic (degree 3) polynomials (one each for red, green, and blue color intensities) to the data
                    pHModel.fitData(3,0,14);

                    //Alternative multivariable polynomial fit from Microsoft Excel Regression
                  ArrayList<String> varListTest2 = new ArrayList<String>();
                  varListTest2.add("R"); // red value variable
                  varListTest2.add("G"); // green value variable
                  varListTest2.add("B"); // blue value variable

                  //Regression coefficients from Microsoft Excel
                  ArrayList<Double> coeffTest2 = new ArrayList<Double>();
                  coeffTest2.add(8.8863758); // constant term
                  coeffTest2.add(-0.048618192); // R coefficient
                  coeffTest2.add(0.061236249); // G coefficient
                  coeffTest2.add(-0.05633453); // B coefficient

                  ArrayList<Double> varValsTest2 = new ArrayList<Double>();
                  varValsTest2.add((double)r);
                  varValsTest2.add((double)g);
                  varValsTest2.add((double)b);

                  pHModel.getMultiVarPoly().setVarList(varListTest2);
                  pHModel.getMultiVarPoly().setCoefficients(coeffTest2);

                  // pH estimate from solving a system of three cubic equations
                  //double pH = pHModel.estimate(r,g,b,0,14);

                  // pH estimate from plugging R,G,B values from the selected pixel into the multivariable polynomial
                  double pH = pHModel.getMultiVarPoly().evaluate(varValsTest2);
                    textView.setText("Estimated pH = " + Math.round(pH*100.0)/100.0); // round pH to two decimal places
                }
                return true;
            }
        });

        //Handle Upload Button click
        uploadImageButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v){
            // check runtime permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
              if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED){
                  //permission not granted, request it.
                String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                //show popup for runtime permission
                requestPermissions(permissions,PERMISSION_CODE);
              } else{
                // permission already granted
                pickImageFromGallery();
              }
            } else {
              //system os is less than lollipop
              pickImageFromGallery();
            }
          }
        });
    }

  //Based on info from: https://stackoverflow.com/a/19976110
  private void readRGBData(){
    InputStream is = getResources().openRawResource(R.raw.raw_vinegar_and_ammonia_training_data);
    BufferedReader reader = new BufferedReader(
            new InputStreamReader(is, Charset.forName("UTF-8"))
    );

    String line = "";
    try {
      //Step over headers
      reader.readLine();

      while ( (line = reader.readLine()) != null) {
        //Split by ','
        String[] tokens = line.split(",");

        //Read the data
        Sample dataSample = new Sample("pH", Double.parseDouble(tokens[3]),Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
        data2.add(dataSample);

        Log.d("MyActivity", "Just created: " + dataSample.print());
      }
    } catch (IOException e) {
      Log.wtf("MyActivity", "Error reading data file on line" + line, e);
      e.printStackTrace();
    }
  }

  private void pickImageFromGallery() {
      //intent to pick image
      Intent intent = new Intent(Intent.ACTION_PICK);
      intent.setType("image/*");
      startActivityForResult(intent,IMAGE_PICK_CODE);
  }

  //handle the result of runtime permissions
  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    switch(requestCode){
      case PERMISSION_CODE:{
        if(grantResults.length > 0 && grantResults[0] ==
              PackageManager.PERMISSION_GRANTED){
          //permission was granted
          pickImageFromGallery();
        } else {
          //permission was denied
          Toast.makeText(this, "Permission denied.", Toast.LENGTH_SHORT).show();
        }
      }
    }
  }

  // handle result of picked image
  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE){
      //set image to image view
      imageView.setImageURI(data.getData());
    }
  }

}