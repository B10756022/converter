package com.example.bmi;

import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter{

    private String[][] data;//資料
    private LayoutInflater inflater;//加載Layout
    private int indentionBase;//縮排

    //優化避免重載
    //宣告會用到的Item
    static class Holder{
        LinearLayout rlBorder;
        TextView setDateBMI;
        TextView setHW;
    }

    //初始化
    public MyAdapter(String[][] data, LayoutInflater inflater){
        this.data=data;
        this.inflater=inflater;
        indentionBase=100;
    }

    //取得數量
    @Override
    public int getCount() {
        return data.length;
    }

    //取得Item
    @Override
    public Object getItem(int i) {
        return data[i];
    }

    //沒有ID所以隨便回傳一個值
    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        //當ListView被拖拉時會不斷觸發getView，避免重複加載須加上此
        if (view == null) {
            holder = new Holder();
            view = inflater.inflate(R.layout.style_listview, null);
            holder.setDateBMI = view.findViewById(R.id.tvName);
            holder.setHW = view.findViewById(R.id.tvLocal);
            holder.rlBorder = view.findViewById(R.id.llBorder);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        //1=DateBMI 2=HW
        //不同類型用不同Style的表現方式
        if (data[i][0].equals("1")){
            //holder.setHW.setText("★");
            holder.setDateBMI.setText(data[i][1]);
            holder.rlBorder.setBackgroundColor(Color.parseColor("#FFDBC9"));

        }else{
            holder.setHW.setText("");
            holder.setDateBMI.setText(data[i][1]);
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) holder.rlBorder.getLayoutParams();
            lp.setMargins(indentionBase,0, 0,0);//縮牌
        }
        return view;
    }
}
