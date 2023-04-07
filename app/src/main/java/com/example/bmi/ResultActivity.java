package com.example.bmi;

import android.annotation.SuppressLint;
import android.app.AppOpsManager;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ResultActivity extends AppCompatActivity {

    //取得Firebase連結
    private DatabaseReference database;

    private TextView bmi;
    private TextView result;
    private  TextView heigh;
    private TextView weigh;
    private TextView dateTitle;
    private Button mDelete;
    private Button mPut;
    private Button mSure;
    private EditText eth;
    private EditText etw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        setTitle("BMI紀錄");
        Bundle bundle = getIntent().getExtras();
        final String date = bundle.getString("selectDate" );

        database = FirebaseDatabase.getInstance().getReference("BMI/"+date);//取得資料庫連結
        bmi = findViewById(R.id.tvBmi);
        result = findViewById(R.id.tvResult);
        heigh=findViewById(R.id.tvHeigh);
        weigh=findViewById(R.id.tvWeigh);
        dateTitle=findViewById(R.id.tvDate);

        mDelete=findViewById(R.id.deleteData);
        mPut=findViewById(R.id.putData);
        mSure=findViewById(R.id.sureData);
        eth=findViewById(R.id.heigh);
        etw=findViewById(R.id.weigh);

        dateTitle.setText(date);
        String sArr[]=new String[4];
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    result.setText(dataSnapshot.child("bmiResult").getValue().toString());
                    bmi.setText(dataSnapshot.child("bmi").getValue().toString());
                    weigh.setText(dataSnapshot.child("weigh").getValue().toString());
                    heigh.setText(dataSnapshot.child("heigh").getValue().toString());
                }
                else {
                    Toast.makeText(getApplicationContext(),"資料已被刪除",Toast.LENGTH_SHORT).show();//context要得到APP的主UI才能顯示
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(ResultActivity.this)
                        .setTitle("確認刪除")
                        .setMessage("是否刪除此筆資料？")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //刪除
                                database=FirebaseDatabase.getInstance().getReference("BMI");//取得資料庫連結
                                database.child(date).removeValue();

                            }
                        })
                        .setNegativeButton("否",null).create()
                        .show();
            }
        });

        mPut.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                if(eth.getVisibility() == View.GONE)
                {
                    eth.setVisibility(View.VISIBLE);
                    etw.setVisibility(View.VISIBLE);
                    mSure.setVisibility(View.VISIBLE);
                    etw.setText(weigh.getText().toString().substring(3,8));
                    eth.setText(heigh.getText().toString().substring(3,9));

                }
                else {
                    eth.setVisibility(View.GONE);
                    etw.setVisibility(View.GONE);
                    mSure.setVisibility(View.GONE);
                }
            }
        });

        mSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( !("".equals(eth.getText().toString())
                        || "".equals(etw.getText().toString())) )
                {
                    //計算
                    Float fh, bmiNum ;
                    String bmiResult = "";
                    Float hh = fh = Float.parseFloat(eth.getEditableText().toString());      // 取得 身高輸入值
                    Float fw = Float.parseFloat(etw.getEditableText().toString());     // 取得 體重輸入值

                    fh = fh/100; // 計算BMI
                    fh = fh*fh;  // 計算BMI

                    NumberFormat nf = NumberFormat.getInstance();   // 數字格式
                    nf.setMaximumFractionDigits(2);                 // 限制小數第二位
                    bmiNum = fw/fh;                                // 計算BMI
                    bmi.setText("BMI：" + nf.format(bmiNum) +"");           // 顯示BMI計算結果


                    // 診斷結果 顯示
                    if (bmiNum<18.5)
                        bmiResult="診斷結果：體重過輕";
                    else if (18.5 <= bmiNum && bmiNum< 24)
                        bmiResult="診斷結果：正常範圍";
                    else if (24 <=bmiNum && bmiNum < 27)
                        bmiResult="診斷結果：過    重";
                    else if (27 <=bmiNum && bmiNum < 30)
                        bmiResult="診斷結果：輕度肥胖";
                    else if (30 <= bmiNum && bmiNum < 35)
                        bmiResult="診斷結果：中度肥胖";
                    else if (bmiNum >= 35)
                        bmiResult="診斷結果：重度肥胖";
                    //紀錄
                    final DecimalFormat df=new DecimalFormat("##0.00");
                    ContactsInfo contact1 = new ContactsInfo("體重："+df.format(fw), "身高："+df.format(hh),
                            "BMI："+df.format(bmiNum), bmiResult, date);

                    //將contact1放人子目錄 /bmifirebaseproject-172bf/date
                    database.setValue(contact1);
                    result.setText(bmiResult);
                }
                else
                    Toast.makeText(getApplicationContext(),"請輸入數值",Toast.LENGTH_SHORT).show();//context要得到APP的主UI才能顯示
            }
        });
    }
}