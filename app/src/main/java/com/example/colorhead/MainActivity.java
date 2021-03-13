package com.example.colorhead;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public CardView instructionsCard, uploadImageCard, helpCard, appInfoCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instructionsCard = (CardView) findViewById(R.id.instructionsCardView);
        uploadImageCard = (CardView) findViewById(R.id.uploadImageCardView);
        helpCard = (CardView) findViewById(R.id.helpCardView);
        appInfoCard = (CardView) findViewById(R.id.appInfoCardView);

        instructionsCard.setOnClickListener(this);
        uploadImageCard.setOnClickListener(this);
        helpCard.setOnClickListener(this);
        appInfoCard.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent i;

        switch(v.getId()){
            case R.id.instructionsCardView:
                i = new Intent(this,Instructions.class);
                startActivity(i);
                break;
            case R.id.uploadImageCardView:
                i = new Intent(this,UploadImage.class);
                startActivity(i);
                break;
            case R.id.helpCardView:
                i = new Intent(this,Help.class);
                startActivity(i);
                break;
            case R.id.appInfoCardView:
                i = new Intent(this,AppInfo.class);
                startActivity(i);
                break;
        }
    }
}