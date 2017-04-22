package com.newtrekwang.simpleroute_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private Button button_2,button_3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_2= (Button) findViewById(R.id.button2);
        button_3= (Button) findViewById(R.id.button3);

        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button2:
                RouterManager.getRouterManager().openResult(MainActivity.this,"activity/main2");
                break;
            case R.id.button3:
                RouterManager.getRouterManager().openResult(MainActivity.this,"activity/main3");
                break;
        }

    }
}
