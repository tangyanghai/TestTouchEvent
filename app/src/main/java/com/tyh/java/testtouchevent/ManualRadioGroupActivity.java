package com.tyh.java.testtouchevent;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 创建人: tyh
 * 创建时间: 2019/2/27
 * 描述:
 */
public class ManualRadioGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_radio_group);

        ManualRadioGroup rg = findViewById(R.id.rg);
        rg.setOnChildRadioButtonClickedListener(new ManualRadioGroup.OnChildRadioButtonClickedListener() {
            @Override
            public void onRadioButtonClickedWhenCheckedNone(RadioButton button) {
                button.setChecked(true);
                Toast.makeText(ManualRadioGroupActivity.this, "第一次选中", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRadioButtonCheckedClicked(RadioButton button) {
                Toast.makeText(ManualRadioGroupActivity.this, "重复选中", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRadioButtonDifferentFromCheckedClicked(final RadioButton clickedRadioButton, final RadioButton checkedRadioButton) {
                new AlertDialog.Builder(ManualRadioGroupActivity.this)
                        .setTitle("重选提示")
                        .setMessage("确定要改变选中吗?")
                        .setCancelable(false)
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                clickedRadioButton.setChecked(true);
                                dialog.dismiss();
                            }
                        })
                        .setNeutralButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create()
                        .show();
            }

        });
    }


    public void onTextViewClicked(View view) {
        if (view instanceof TextView) {
            CharSequence text = ((TextView) view).getText();
            if (text!=null) {
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
            }
        }
    }



}
