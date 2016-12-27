package com.lan.myselfdefine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by X_S on 2016/10/29.
 */

public class SpinnerActivity extends AppCompatActivity {

    private List<String> list;
    private MyAdapter myAdapter;
    private ListView lv;
    private PopupWindow popupWindow;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        final EditText mET_spinner= (EditText) findViewById(R.id.mET_spinner);
        ImageButton mIB_down = (ImageButton) findViewById(R.id.mIB_down);

        lv = new ListView(this);
        initData();
        myAdapter = new MyAdapter();
        lv.setAdapter(myAdapter);


        lv.setDividerHeight(0);
        lv.setVerticalScrollBarEnabled(false);
        lv.setBackgroundResource(R.mipmap.listview_background);

        mIB_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                popupWindow = new PopupWindow(lv, mET_spinner.getWidth(), 900);

                popupWindow.setFocusable(true);
                popupWindow.showAsDropDown(mET_spinner);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String number = list.get(i);

                mET_spinner.setText(number);

                popupWindow.dismiss();
            }
        });
    }

    private void initData() {
        list = new ArrayList<>();
        for(int i=0;i<20;i++){
            list.add("102000"+i);
        }
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public String getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            MyViewHolde holder;
            if(view==null){
                view = View.inflate(SpinnerActivity.this,R.layout.list_item,null);
                holder=new MyViewHolde();
                holder.mTV= (TextView) view.findViewById(R.id.mTextView);
                holder.mIB= (ImageButton) view.findViewById(R.id.mIB_delete);

                view.setTag(holder);
            }else{
                holder= (MyViewHolde) view.getTag();
            }
            holder.mTV.setText(getItem(i));
            holder.mIB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    list.remove(i);
                    myAdapter.notifyDataSetChanged();

                    //delete the backgroud
                    if(list.size()==0){
                        popupWindow.dismiss();
                    }
                }
            });
            return view;
        }

        class MyViewHolde{
            TextView mTV;
            ImageButton mIB;
        }
    }

}
