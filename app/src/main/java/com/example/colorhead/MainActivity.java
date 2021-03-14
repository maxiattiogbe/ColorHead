package com.example.colorhead;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


   public CardView instructionsCardView, uploadImageCardView, helpCardView, appInfoCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instructionsCardView = (CardView) findViewById(R.id.instructionsCardView);
        uploadImageCardView = (CardView) findViewById(R.id.uploadImageCardView);
        helpCardView = (CardView) findViewById(R.id.helpCardView);
        appInfoCardView = (CardView) findViewById(R.id.appInfoCardView);

        instructionsCardView.setOnClickListener(this);
        uploadImageCardView.setOnClickListener(this);
        helpCardView.setOnClickListener(this);
        appInfoCardView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch(v.getId()){
            case R.id.instructionsCardView:
                i = new Intent(this, Instructions.class);
                startActivity(i);
                break;

            case R.id.uploadImageCardView:
                i = new Intent(this, UploadImage.class);
                startActivity(i);
                break;

            case R.id.helpCardView:
                i = new Intent(this, Help.class);
                startActivity(i);
                break;

            case R.id.appInfoCardView:
                i = new Intent(this, AppInfo.class);
                startActivity(i);
                break;
        }
    }

}

