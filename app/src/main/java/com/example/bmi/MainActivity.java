package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mBtnBmi;
    private Button mBtnShoeMan;
    private Button mBtnShoeWoman;
    private Button mBtnTime;
    private Button mBtnTemperature;
    private Button mBtnMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnBmi=findViewById(R.id.btn_bmi);
        mBtnShoeMan=findViewById(R.id.btn_shoe_man);
        mBtnShoeWoman=findViewById(R.id.btn_shoe_woman);
        mBtnTime=findViewById(R.id.btn_time);
        mBtnTemperature=findViewById(R.id.btn_temperature);
        mBtnMoney=findViewById(R.id.btn_money);
        setListeners();
    }

    private void setListeners(){
        Onclink onclink=new Onclink();
        mBtnBmi.setOnClickListener(onclink);
        mBtnShoeMan.setOnClickListener(onclink);
        mBtnShoeWoman.setOnClickListener(onclink);
        mBtnTime.setOnClickListener(onclink);
        mBtnTemperature.setOnClickListener(onclink);
        mBtnMoney.setOnClickListener(onclink);
    }

    private class Onclink implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = null;
            switch (view.getId()){
                case R.id.btn_bmi:
                    intent=new Intent(MainActivity.this,BmiActivity.class);
                    break;
                case R.id.btn_shoe_man:
                    intent=new Intent(MainActivity.this,ShoeManActivity.class);
                    break;
                case R.id.btn_shoe_woman:
                    intent=new Intent(MainActivity.this,ShoeWomanActivity.class);
                    break;
                case R.id.btn_time:
                    intent=new Intent(MainActivity.this,TimeActivity.class);
                    break;
                case R.id.btn_temperature:
                    intent=new Intent(MainActivity.this,TemperatureActivity.class);
                    break;
                case R.id.btn_money:
                    intent=new Intent(MainActivity.this,MoneyActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }
}