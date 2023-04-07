package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class TimeActivity extends AppCompatActivity {
    EditText a;

   private RadioGroup mrg1,mrg2;
    private RadioButton r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14;
    float r, rr,fa;
    String G,GG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        setTitle("時間轉換器");
        a = findViewById(R.id.editText4);
        r1=findViewById(R.id.r1);
        r2=findViewById(R.id.r2);
        r3=findViewById(R.id.r3);
        r4=findViewById(R.id.r4);
        r5=findViewById(R.id.r5);
        r6=findViewById(R.id.r6);
        r7=findViewById(R.id.r7);
        r8=findViewById(R.id.r8);
        r9=findViewById(R.id.r9);
        r10=findViewById(R.id.r10);
        r11=findViewById(R.id.r11);
        r12=findViewById(R.id.r12);
        r13=findViewById(R.id.r13);
        r14=findViewById(R.id.r14);

        mrg1=findViewById(R.id.Rg1);
        mrg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == r1.getId()) {
                    G="秒";
                } else if (checkedId == r2.getId()) {
                    G="分";
                } else if (checkedId == r3.getId()) {
                    G="小時";
                } else if (checkedId == r4.getId()) {
                    G="天";
                } else if (checkedId == r5.getId()) {
                    G="周";
                } else if (checkedId == r6.getId()) {
                    G="月";
                } else if (checkedId == r7.getId()) {
                    G="年";
                }
            }
        });

        mrg2 = findViewById(R.id.Rg2);
        mrg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if ( checkedId == r8.getId()) {
                    GG="秒";

                } else if (checkedId == r9.getId()) {
                    GG="分";

                } else if (checkedId == r10.getId()) {
                    GG="小時";

                } else if (checkedId == r11.getId()) {
                    GG="天";

                } else if (checkedId == r12.getId()) {
                    GG="周";

                } else if (checkedId == r13.getId()) {
                    GG="月";

                } else if (checkedId == r14.getId()) {
                    GG="年";

                }
            }
        });

    }
       public void shosToast(View view){


           if(G!="" && GG!="")
           {
               try {
                   fa = Float.parseFloat(a.getEditableText().toString());
               }
               catch (Exception e)
               {
                   Toast.makeText(getApplicationContext(),"請輸入數值或選擇單位",Toast.LENGTH_SHORT).show();//context要得到APP的主UI才能顯示
               }
               if(fa>0)
               {
                   switch (G)
                   {
                       case "秒":
                           r = fa;
                           break;
                       case "分":
                           r = fa*60;
                           break;
                       case "小時":
                           r = fa *3600;
                           break;
                       case "天":
                           r = fa*86400 ;
                           break;
                       case "周":
                           r = fa *604800;
                           break;
                       case "月":
                           r = fa *2419200;
                           break;
                       case "年":
                           r = fa *2930400;
                           break;
                       default:
                           Toast.makeText(getApplicationContext(),"請選擇單位",Toast.LENGTH_SHORT).show();//context要得到APP的主UI才能顯示
                           break;
                   }
                   switch (GG)
                   {
                       case "秒":
                           rr = r;
                           break;
                       case "分":
                           rr = r/60;
                           break;
                       case "小時":
                           rr = r /3600;
                           break;
                       case "天":
                           rr = r/86400 ;
                           break;
                       case "周":
                           rr = r /604800;
                           break;
                       case "月":
                           rr = r /2419200;
                           break;
                       case "年":
                           rr = r /2930400;
                           break;
                       default:
                           Toast.makeText(getApplicationContext(),"請選擇單位",Toast.LENGTH_SHORT).show();//context要得到APP的主UI才能顯示
                           break;
                   }
                   TextView result = findViewById(R.id.tv1);
                   result.setText(fa+G+ "換算為：" + rr+GG);
               }
               else
                   Toast.makeText(getApplicationContext(),"請輸入數值",Toast.LENGTH_SHORT).show();//context要得到APP的主UI才能顯示
               G=GG="";
               fa=0;
           }
           else
               Toast.makeText(getApplicationContext(),"請輸入數值或選擇單位",Toast.LENGTH_SHORT).show();//context要得到APP的主UI才能顯示

           mrg1.clearCheck();
           mrg2.clearCheck();
       }
}


