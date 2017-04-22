package com.newtrekwang.simpleroute_2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.Route;
import com.newtrekwang.router.RouterManager;
/**
 * 实现简单的路由跳转
 */
@Route("router://activity/main")
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void go2(View view){
        RouterManager.getRouterManager().openResult(MainActivity.this,"activity/main2");
    }

    public void go3(View view){
        RouterManager.getRouterManager().openResult(MainActivity.this,"activity/main3");

    }
}
