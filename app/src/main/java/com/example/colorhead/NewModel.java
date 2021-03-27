package com.example.colorhead;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class NewModel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_model);

        readRGBData();
    }

    private ArrayList<Sample> data = new ArrayList<Sample>();
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
               data.add(dataSample);

               Log.d("MyActivity", "Just created: " + dataSample.print());
           }
       } catch (IOException e) {
           Log.wtf("MyActivity", "Error reading data file on line" + line, e);
           e.printStackTrace();
       }
    }
}