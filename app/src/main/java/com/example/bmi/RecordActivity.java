package com.example.bmi;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class RecordActivity extends AppCompatActivity {


    private FirebaseDatabase database = FirebaseDatabase.getInstance();//取得資料庫連結

    private DatabaseReference myRef = database.getReference("BMI");
    String[] strGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        setTitle("BMI紀錄");


        //查詢顯示
        final ListView listView = findViewById(R.id.list);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                strGroup = new String[20];//暫定20筆資料
                int i=0;
                adapter.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren() ){
                    strGroup[i]=ds.child("date").getValue().toString();
                    adapter.add(strGroup[i]);
                    i++;
                }
                if(adapter.isEmpty())
                    listView.setVisibility(View.GONE);
                else
                    listView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                // 利用索引值取得點擊的項目內容
                String text = strGroup[index];
                // 整理要顯示的文字
                String result = text;
                //跳轉
                Intent intent = new Intent();
                intent.setClass(RecordActivity.this , ResultActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("selectDate", text);//傳值到下一個
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }
}
