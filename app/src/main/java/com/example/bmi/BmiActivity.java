package com.example.bmi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class BmiActivity extends AppCompatActivity {
    private Button mBtnhome;
    private Button mBtnrecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        setTitle("BMI轉換器");
        mBtnhome=findViewById(R.id.home);
        mBtnrecord=findViewById(R.id.record);
        mBtnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(BmiActivity.this , HomeActivity.class);
                startActivity(intent);
            }
        });

        mBtnrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(BmiActivity.this , RecordActivity.class);
                startActivity(intent);
            }
        });



    }
}