package com.example.bmi;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import java.text.DecimalFormat;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    //取得Firebase連結
    private DatabaseReference database;
    private Button mBrecord;
    private Button mBmi;
    private TextView result;// 取得 顯示結果 物件
    private TextView dia;// 取得 顯示診斷 物件
    private EditText editTextDate;
    private String date="";
    private String bmiResult;
    private String t;//今天日期
    String tmp;//選擇日期
    EditText h;
    EditText w;
    double fh;
    double fw;
    double hh;
    double bmiNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setTitle("BMI轉換器");
        editTextDate=findViewById(R.id.etDate);
        mBrecord=findViewById(R.id.record);
        mBmi=findViewById(R.id.bmi);
        h=findViewById(R.id.heigh);
        w=findViewById(R.id.weigh);
        database = FirebaseDatabase.getInstance().getReference("BMI");//取得資料庫連結
        result = findViewById(R.id.tvBmi);// 取得 顯示結果 物件
        dia = findViewById(R.id.tvResult);// 取得 顯示診斷 物件
        Date curDate=new Date(System.currentTimeMillis());//獲取當前時間
        SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMdd");
        t= formatter.format(curDate);
        //四捨五入
        final DecimalFormat df=new DecimalFormat("##0.00");

        mBrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(HomeActivity.this , RecordActivity.class);
                startActivity(intent);
            }
        });




        mBmi.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View arg0) {
                //判斷條件 身高 跟 體重 都有輸入值才執行
                if ( !("".equals(h.getText().toString())
                        || "".equals(w.getText().toString())) )
                {
                    //新增一個訊息框
                    new AlertDialog.Builder(HomeActivity.this)
                            .setTitle("計算並記錄")
                            .setMessage("請問是否進行計算並記錄？（資料已存在則將更新）")
                            .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    bmi_result();
                                    if(date == "")
                                    {

                                        date= t;

                                        editTextDate.setText(date);
                                    }

                                    //紀錄
                                    final ContactsInfo contact1 = new ContactsInfo("體重："+df.format(fw), "身高："+df.format(hh),
                                            "BMI："+df.format(bmiNum), "診斷結果："+bmiResult, date);

                                    //將contact1放人子目錄 /bmifirebaseproject-172bf/date

                                    database.child(date).setValue(contact1);
                                    date = "";
                                }

                            })
                            .setNegativeButton("否",null)
                            .setNeutralButton("僅計算",new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    bmi_result();
                                }
                            })
                            .create()
                            .show();
                }
                else
                    Toast.makeText(getApplicationContext(),"請輸入數值",Toast.LENGTH_SHORT).show();//context要得到APP的主UI才能顯示
            }
            //BMI副程式
            public void bmi_result(){
                hh = fh = Float.parseFloat(h.getEditableText().toString());      // 取得 身高輸入值
                fw = Float.parseFloat(w.getEditableText().toString());     // 取得 體重輸入值

                fh = fh/100; // 計算BMI
                fh = fh*fh;  // 計算BMI

                NumberFormat nf = NumberFormat.getInstance();   // 數字格式
                nf.setMaximumFractionDigits(2);                 // 限制小數第二位
                bmiNum = fw/fh;                                // 計算BMI
                result.setText("BMI結果：" + nf.format(fw/fh) +"");           // 顯示BMI計算結果


                // 診斷結果 顯示
                if (bmiNum<18.5)
                    bmiResult="體重過輕";
                else if (18.5 <= bmiNum && bmiNum< 24)
                    bmiResult="正常範圍";
                else if (24 <=bmiNum && bmiNum < 27)
                    bmiResult="過    重";
                else if (27 <=bmiNum && bmiNum < 30)
                    bmiResult="輕度肥胖";
                else if (30 <= bmiNum && bmiNum < 35)
                    bmiResult="中度肥胖";
                else if (bmiNum >= 35)
                    bmiResult="重度肥胖";
                dia.setText("診斷結果："+bmiResult);
            }
        });
        editTextDate.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View arg0) {

                final int mYear,mMonth,mDay;

                Calendar c=Calendar.getInstance();//Calendar今天時間

                mYear=c.get(Calendar.YEAR);
                mMonth=c.get(Calendar.MONTH);
                mDay=c.get(Calendar.DAY_OF_MONTH);
                //values和values-v21下的MyDatePickerDialogTheme改顏色
                DatePickerDialog dpd=new DatePickerDialog(HomeActivity.this,
                        R.style.MyDatePickerDialogTheme,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                tmp=setDateFormat(year,month,day);
                                editTextDate.setText(tmp);
                            }
                        },mYear,mMonth,mDay);
                //設置最大日期為今天
                DatePicker dp= dpd.getDatePicker();
                dp.setMaxDate(System.currentTimeMillis());
                dpd.show();
            }

            public String setDateFormat(int year,int monthOfYear,int dayOfMonth){
                date=String.valueOf(year);
                if(monthOfYear < 9)
                    date+="0"+String.valueOf(monthOfYear + 1);
                else
                    date+=String.valueOf(monthOfYear + 1);
                if(dayOfMonth < 10)
                    date+="0"+String.valueOf(dayOfMonth);
                else
                    date+=String.valueOf(dayOfMonth);
                return date;
            }

        });


    }
}