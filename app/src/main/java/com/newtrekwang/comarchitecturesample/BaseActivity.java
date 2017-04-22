package com.newtrekwang.comarchitecturesample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        注解解析 ,遍历所有的子类，找到修饰了注解ContentView注解的类，获取ContentView的属性值，为Activity设置布局
        for (Class c=this.getClass();c !=Context.class;c=c.getSuperclass()){
            ContentView annotation= (ContentView) c.getAnnotation(ContentView.class);
            if (annotation!=null){
                try {
                    this.setContentView(annotation.value());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return;
        }
    }
}
