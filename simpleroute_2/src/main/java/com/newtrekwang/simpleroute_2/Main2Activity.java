package com.newtrekwang.simpleroute_2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.Route;

@Route("router://activity/main2")
public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
}
