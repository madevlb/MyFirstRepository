package com.jiyun.qcloud.pop;

import android.content.Intent;
import android.view.View;

public class SecondActivity extends BaseActivity

{



    @Override
    protected int getlayout() {
        return R.layout.activity_second;
    }



    @Override
    protected void init() {

    }

    @Override
    protected void findViews() {
            findViewById(R.id.text3).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(SecondActivity.this,Main2Activity.class));
                }
            });
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void release() {

    }

}
