package com.tyh.java.testtouchevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtil.logMethod();
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.logMethod();
        return false;
    }


    public void onJumpToRadioGroupClicked(View view) {
        ActivityCompat.startActivity(this,new Intent(this,ManualRadioGroupActivity.class),null);
    }
}
