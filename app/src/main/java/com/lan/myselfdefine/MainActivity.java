package com.lan.myselfdefine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout mRL_level1;
    private RelativeLayout mRL_level2;
    private RelativeLayout mRL_level3;
    private  boolean mRL_level3_visiable=true;
    private  boolean mRL_level2_visiable=true;
    private  boolean mRL_level1_visiable=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyfindViewById();


    }

    private void MyfindViewById() {
        ImageButton mIB_home = (ImageButton) findViewById(R.id.mIB_home);
        ImageButton mIB_menu = (ImageButton) findViewById(R.id.mIB_menu);
        mRL_level1 = (RelativeLayout) findViewById(R.id.mRL_level1);
        mRL_level2 = (RelativeLayout) findViewById(R.id.mRL_level2);
        mRL_level3 = (RelativeLayout) findViewById(R.id.mRL_level3);

//        mRL_level1.setVisibility(RelativeLayout.INVISIBLE);
  //      mRL_level2.setVisibility(RelativeLayout.INVISIBLE);
    //    mRL_level3.setVisibility(RelativeLayout.INVISIBLE);

        mIB_home.setOnClickListener(this);
        mIB_menu.setOnClickListener(this);

        Button mBT_viewpagerDemo = (Button) findViewById(R.id.mBT_viewpagerDemo);
        Button mSP_choose = (Button) findViewById(R.id.mSP_choose);
        mBT_viewpagerDemo.setOnClickListener(this);
        mSP_choose.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mIB_home:
                Log.e("MainActivity",mRL_level1_visiable+","+mRL_level2_visiable+","+mRL_level3_visiable);
                if(mRL_level2_visiable){
                    AnimationUtils.out(mRL_level2,400);
                    mRL_level2_visiable=false;
                    if(mRL_level3_visiable){
                        AnimationUtils.out(mRL_level3,100);
                        mRL_level3_visiable=false;
                    }
                }else{
                    AnimationUtils.in(mRL_level2,0);
                    mRL_level2_visiable=true;
                }
                break;
            case R.id.mIB_menu:
                if(mRL_level3_visiable){
                    AnimationUtils.out(mRL_level3,0);
                    mRL_level3_visiable=false;
                }else{
                    AnimationUtils.in(mRL_level3,0);
                    mRL_level3_visiable=true;
                }
                break;
            case R.id.mBT_viewpagerDemo:

                startActivity(new Intent(MainActivity.this,ViewPagerActivity.class));
                break;
            case R.id.mSP_choose:
                startActivity(new Intent(MainActivity.this,SpinnerActivity.class));
                break;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_MENU){
            if(mRL_level1_visiable){
                if(mRL_level3_visiable){
                    AnimationUtils.out(mRL_level3,0);
                    mRL_level3_visiable=false;
                    if(mRL_level2_visiable){
                        AnimationUtils.out(mRL_level2,300);
                        mRL_level2_visiable=false;
                    }
                    AnimationUtils.out(mRL_level1,600);
                    mRL_level1_visiable=false;
                }
            }else{
                AnimationUtils.in(mRL_level1,0);
                AnimationUtils.in(mRL_level2,300);
                AnimationUtils.in(mRL_level3,600);
                mRL_level3_visiable=true;
                mRL_level2_visiable=true;
                mRL_level1_visiable=true;
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
