package com.jiyun.qcloud.pop;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseActivity {


    private boolean isExit;


    @Override
    protected int getlayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void findViews() {
        TextView textView= (TextView) findViewById(R.id.text);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void release() {

    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
                exit();
                return true;
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            ActivityMgr.getActivityManager().popAllActivity();
           /* int currentVersion = android.os.Build.VERSION. SDK_INT;
            if (currentVersion > android.os.Build.VERSION_CODES .ECLAIR_MR1) {
                Intent startMain = new Intent (Intent. ACTION_MAIN);
                startMain .addCategory( Intent.CATEGORY_HOME );
                startMain .setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                startActivity (startMain) ;
                System.exit( 0);
            } else {// android2.1
//                ActivityManager am = (ActivityManager) getSystemService (ACTIVITY_SERVICE) ;
//                am .restartPackage( getPackageName());
            }*/
        }
    }


    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };
}
