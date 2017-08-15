package com.jiyun.qcloud.pop;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 承岩 on 2016/5/23.
 */
public abstract class
BaseActivity extends AppCompatActivity {

    public Context mContext;

    public List<Activity> activities=new ArrayList<Activity>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            String FRAGMENTS_TAG = "android:support:fragments";
            savedInstanceState.remove(FRAGMENTS_TAG);
        }
        mContext = this;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏

        ActivityMgr.getActivityManager().pushActivity(this);
        setContentView(getlayout());

        init();
        findViews();
        setListeners();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    /**
     * 设置布局
     */
    protected abstract int getlayout();

    /**
     * 初始化，setContentView后调用
     */
    protected abstract void init();

    /**
     * 查找视图。init后调用；
     */
    protected abstract void findViews();

    /**
     * 设置侦听器，findViews后调用
     */
    protected abstract void setListeners();

    /**
     * 释放资源
     */
    protected abstract void release();


    @Override//击后退键的时候，为了防止点得过快，触发两次后退事件，故做此设置。保留这个判断，增强程序健壮性。
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
                ActivityMgr.getActivityManager().popActivity(this);
                return true;
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        release();
    }

}
