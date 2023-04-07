package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ShoeWomanActivity extends AppCompatActivity {
    EditText a;

   private RadioGroup mrg1,mrg2;
    private RadioButton us1,us2,eur1,eur2,jp1,jp2,uk1,uk2,cm1,cm2,c;

    double r, rr,fa;

    String G,GG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoe_woman);
        setTitle("女鞋碼轉換器");
        a = findViewById(R.id.editText4);

        us1=findViewById(R.id.us1);
        us2=findViewById(R.id.us2);
        eur1=findViewById(R.id.eur1);
        eur2=findViewById(R.id.eur2);
        jp1=findViewById(R.id.jp1);
        jp2=findViewById(R.id.jp2);
        uk1=findViewById(R.id.uk1);
        uk2=findViewById(R.id.uk2);
        cm1=findViewById(R.id.cm1);
        cm2=findViewById(R.id.cm2);


        mrg1=findViewById(R.id.Rg1);
        mrg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == us1.getId()) {
                    G="美國碼(US)";
                } else if (checkedId == eur1.getId()) {
                    G="歐洲碼(EUR)";
                } else if (checkedId == jp1.getId()) {
                    G="日本碼(JP)";
                } else if (checkedId == uk1.getId()) {
                    G="英國碼(UK)";
                } else if (checkedId == cm1.getId()) {
                    G="足長cm";
                }
            }
        });

        mrg2 = findViewById(R.id.Rg2);
        mrg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == us2.getId()) {
                    GG="美國碼(US)";
                } else if (checkedId == eur2.getId()) {
                    GG="歐洲碼(EUR)";
                } else if (checkedId == jp2.getId()) {
                    GG="日本碼(JP)";
                } else if (checkedId == uk2.getId()) {
                    GG="英國碼(UK)";
                } else if (checkedId == cm2.getId()) {
                    GG="足長cm";
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
                       case "美國碼(US)":
                           r = fa-1.5;//美國碼(US)
                           break;
                       case "歐洲碼(EUR)":
                           r = fa-31.5;//歐洲碼(EUR)
                           break;
                       case "日本碼(JP)":
                           r = fa -18.5;//日本碼(JP)
                           break;
                       case "英國碼(UK)":
                           r = fa ;//英國碼(UK)
                           break;
                       case "足長cm":
                           r = fa -18.5;//足長cm
                           break;
                       default:
                           Toast.makeText(getApplicationContext(),"請選擇單位",Toast.LENGTH_SHORT).show();//context要得到APP的主UI才能顯示
                           break;
                   }
                   switch (GG)
                   {
                       case "美國碼(US)":
                           rr = r+1.5;//美國碼(US)
                           break;
                       case "歐洲碼(EUR)":
                           rr = r+31.5;//歐洲碼(EUR)
                           break;
                       case "日本碼(JP)":
                           rr = r +18.5;//日本碼(JP)
                           break;
                       case "英國碼(UK)":
                           rr = r ;//英國碼(UK)
                           break;
                       case "足長cm":
                           rr = r +18.5;//足長cm
                           break;
                       default:
                           Toast.makeText(getApplicationContext(),"請選擇單位",Toast.LENGTH_SHORT).show();//context要得到APP的主UI才能顯示
                           break;
                   }

                   TextView result = findViewById(R.id.tv1);
                   result.setText(fa+G+ "換算為：\n" + rr+GG);
                   //輸入數值+美國碼(us) "換算為" 新尺寸+"英國碼(UK)"
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


