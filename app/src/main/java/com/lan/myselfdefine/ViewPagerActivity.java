package com.lan.myselfdefine;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by X_S on 2016/10/29.
 */
public class ViewPagerActivity  extends AppCompatActivity {
    private List<ImageView> imageViews;
    private TextView mTV_point;
    private String str[];
    private LinearLayout mLL_point;
    private int index;
    private boolean flag =true;
    //private ScheduledExecutorService scheduledExecutorService;

    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mVP_demo.setCurrentItem(index);
        }
    };
    private ViewPager mVP_demo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpagerdemo);
        mVP_demo = (ViewPager) findViewById(R.id.mVP_demo);
        mTV_point = (TextView) findViewById(R.id.mTV_info);
        mLL_point = (LinearLayout) findViewById(R.id.mLL_point);

        initData();
        //default center position
        mVP_demo.setAdapter(new MyAdapter());
        mVP_demo.setOnPageChangeListener(new MyPageChangeListener());
        mVP_demo.setCurrentItem(Integer.MAX_VALUE/2);

        new Thread(){
            @Override
            public void run() {
                while(flag){
                    try {
                        Thread.sleep(200);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mVP_demo.setCurrentItem(index+1);
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Log.e("ddd=","dddddddddd");
                    }
                }

            }
        }.start();

    }

    private void initData() {
        index=0;
        int[] icons =new int []{R.mipmap.a,R.mipmap.b,R.mipmap.c,R.mipmap.d,R.mipmap.e};
        str=new String[]{
                "dddddd","dadf","fgsf","dfa","dfa"
        };
        imageViews = new ArrayList<>();

        for(int i =0;i<icons.length;i++){

            ImageView mIV=new ImageView(ViewPagerActivity.this);
            mIV.setBackgroundResource(icons[i]);
            imageViews.add(mIV);
            // add pointer
            View view = new View(this);

            view.setBackgroundResource(R.drawable.pointer);
            //set view param
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(10, 10);
            if(i!=0){
                layoutParams.leftMargin=15;
            }
            view.setLayoutParams(layoutParams);
            mLL_point.addView(view);
            mLL_point.getChildAt(index).setBackgroundResource(R.drawable.pointer_white);
        }

    }

    class MyAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //give position
            ImageView v = imageViews.get(position%imageViews.size());
            ViewGroup parent = (ViewGroup) v.getParent();
            if (parent != null) {
                parent.removeAllViews();
            }
            position=position % imageViews.size();
            ImageView mIV = imageViews.get(position);
            container.addView(mIV);
            return mIV;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            position=position % imageViews.size();
            container.removeView((View) object);
        }
    }

    class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            position=position % imageViews.size();
        }

        @Override
        public void onPageSelected(int position) {
            position=position % imageViews.size();
            mTV_point.setText(str[position]);
            mLL_point.getChildAt(index).setBackgroundResource(R.drawable.pointer);
            mLL_point.getChildAt(position).setBackgroundResource(R.drawable.pointer_white);
            index=position;

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

    }

    @Override
    protected void onDestroy() {
        flag=false;
    }

    //    @Override
//    protected void onStart() {
//        super.onStart();
//        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//        scheduledExecutorService.scheduleWithFixedDelay(new ViewPagerTask(),2,2, TimeUnit.SECONDS);
//    }
//
//    class ViewPagerTask implements Runnable{
//        @Override
//        public void run() {
//            index=(index+1)%imageViews.size();
//
//            handler.obtainMessage().sendToTarget();
//        }
//    }

}
