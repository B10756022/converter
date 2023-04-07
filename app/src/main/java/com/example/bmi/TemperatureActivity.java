package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TemperatureActivity extends AppCompatActivity {
    EditText a;

   private RadioGroup mrg1,mrg2;
    private RadioButton r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14;
    double r, rr,fa,C,F,K,RA;
    String G,GG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        setTitle("溫度轉換器");
        a = findViewById(R.id.editText4);
        r1=findViewById(R.id.r1);
        r2=findViewById(R.id.r2);
        r3=findViewById(R.id.r3);
        r4=findViewById(R.id.r4);

        r8=findViewById(R.id.r8);
        r9=findViewById(R.id.r9);
        r10=findViewById(R.id.r10);
        r11=findViewById(R.id.r11);


        mrg1=findViewById(R.id.Rg1);
        mrg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == r1.getId()) {
                    G="°C";
                } else if (checkedId == r2.getId()) {
                    G="°F";

                } else if (checkedId == r3.getId()) {
                    G="K";
                } else if (checkedId == r4.getId()) {
                    G="R";
                }
            }
        });

        mrg2 = findViewById(R.id.Rg2);
        mrg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if ( checkedId == r8.getId()) {
                    GG="°C";

                } else if (checkedId == r9.getId()) {
                    GG="°F";

                } else if (checkedId == r10.getId()) {
                    GG="K";

                } else if (checkedId == r11.getId()) {
                    GG="R";

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
                       case "°C":
                           C = fa;//攝氏
                           F=C*9/5+32;
                           K= (float) (C+273.15);
                           RA= (float) (C* 9/5+491.67);
                           break;
                       case "°F":
                           F = fa ;//華氏
                           C=(F-32)*5/9;
                           K= (float) ((F+459.67)*5/9);
                           RA= (float) (F+459.67);
                           break;
                       case "K":
                           K = fa ;//凱氏
                           C= (float) (K-273.15);
                           F= (float) (K*9/5-459.67);
                           RA= (float) (F+459.67);
                           break;
                       case "R":
                           RA = fa ;//蘭金
                           C= (float) ((RA-491.679)*5/9);
                           F= (float) (RA-459.67);
                           K=(float) ((F+459.67)*5/9);
                           break;
                       default:
                           Toast.makeText(getApplicationContext(),"請選擇單位",Toast.LENGTH_SHORT).show();//context要得到APP的主UI才能顯示
                           break;
                   }
                   switch (GG)
                   {
                       case "°C":
                           rr = C;
                           break;
                       case "°F":
                           rr = F;
                           break;
                       case "K":
                           rr = K;
                           break;
                       case "R":
                           rr = RA;
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


