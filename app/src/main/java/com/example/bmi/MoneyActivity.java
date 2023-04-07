package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MoneyActivity extends AppCompatActivity {
    EditText a;

    private RadioGroup mrg1,mrg2;
    private RadioButton tw1,tw2,cn1,cn2,jp1,jp2,kr1,kr2,gb1,gb2,us1,us2,eu1,eu2,c;

    double r, rr,fa;

    String G,GG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);
        setTitle("貨幣轉換器");
        a = findViewById(R.id.editText4);

        tw1=findViewById(R.id.tw1);
        tw2=findViewById(R.id.tw2);
        cn1=findViewById(R.id.cn1);
        cn2=findViewById(R.id.cn2);
        jp1=findViewById(R.id.jp1);
        jp2=findViewById(R.id.jp2);
        kr1=findViewById(R.id.kr1);
        kr2=findViewById(R.id.kr2);
        gb1=findViewById(R.id.gb1);
        gb2=findViewById(R.id.gb2);
        eu1=findViewById(R.id.eu1);
        eu2=findViewById(R.id.eu2);
        us1=findViewById(R.id.us1);
        us2=findViewById(R.id.us2);


        mrg1=findViewById(R.id.Rg1);
        mrg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == tw1.getId()) {

                    G="台幣(TWD)";
                } else if (checkedId == cn1.getId()) {

                    G="人民幣(CNY)";
                } else if (checkedId == jp1.getId()) {

                    G="日圓(JPY)";
                } else if (checkedId == kr1.getId()) {

                    G="韓元(KRW)";
                } else if (checkedId == gb1.getId()) {

                    G="英鎊(GBP)";
                } else if (checkedId == eu1.getId()) {

                    G="歐元(EUR)";
                } else if (checkedId == us1.getId()) {

                    G="美元(USD)";
                }
            }
        });

        mrg2 = findViewById(R.id.Rg2);
        mrg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == tw2.getId()) {
                    GG="台幣(TWD)";
                } else if (checkedId == cn2.getId()) {
                    GG="人民幣(CNY)";
                } else if (checkedId == jp2.getId()) {
                    GG="日圓(JPY)";
                } else if (checkedId == kr2.getId()) {
                    GG="韓元(KRW)";
                } else if (checkedId == gb2.getId()) {
                    GG="英鎊(GBP)";
                } else if (checkedId == eu2.getId()) {
                    GG="歐元(EUR)";
                } else if (checkedId == us2.getId()) {
                    GG="美元(USD)";
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
                    case "台幣(TWD)":
                        r = fa;//台幣(TWD)
                        break;
                    case "人民幣(CNY)":
                        r = fa*4;//人民幣(CNY)
                        break;
                    case "日圓(JPY)":
                        r = fa*0.274;//日圓(JPY)
                        break;
                    case "韓元(KRW)":
                        r = fa*0.024 ;//韓元(KRW)
                        break;
                    case "英鎊(GBP)":
                        r = fa*30;//英鎊(GBP)
                        break;
                    case "歐元(EUR)":
                        r = fa *35;//歐元(EUR)
                        break;
                    case "美元(USD)":
                        r = fa*32;//美元(USD)
                        break;
                    default:
                        Toast.makeText(getApplicationContext(),"請選擇單位",Toast.LENGTH_SHORT).show();//context要得到APP的主UI才能顯示
                        break;
                }
                switch (GG)
                {
                    case "台幣(TWD)":
                        rr = r;//台幣(TWD)
                        break;
                    case "人民幣(CNY)":
                        rr = r/4;//人民幣(CNY)
                        break;
                    case "日圓(JPY)":
                        rr = r/0.274;//日圓(JPY)
                        break;
                    case "韓元(KRW)":
                        rr = r/0.024 ;//韓元(KRW)
                        break;
                    case "英鎊(GBP)":
                        rr = r/30;//英鎊(GBP)
                        break;
                    case "歐元(EUR)":
                        rr = r/35;//歐元(EUR)
                        break;
                    case "美元(USD)":
                        rr = r/32;//美元(USD)
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


