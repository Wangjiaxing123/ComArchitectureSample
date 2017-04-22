package com.newtrekwang.router;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class RouterManager {
    private Map<String,Class<? extends Activity>> mTables;
    private String mSchemeprefix;

    private RouterManager(){
        mTables=new HashMap<>();
    }

    private static class RouterManagerHolder{
        private final static RouterManager INSTANCE=new RouterManager();
    }
    public static RouterManager getRouterManager(){
        return RouterManagerHolder.INSTANCE;
    }

    public void init() {
        try {
            String className = "com.newtrekwang.router.impl.AppRouter";
            Class<?> moduleRouteTable = Class.forName(className);
            Constructor constructor = moduleRouteTable.getConstructor();
            IRoute instance = (IRoute) constructor.newInstance();
            instance.initRouter(mTables);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setmSchemeprefix(String schemeprefix){
        this.mSchemeprefix=schemeprefix;
    }

    public void openResult(Context context,String path){
        if (!TextUtils.isEmpty(mSchemeprefix)){
            path=mSchemeprefix+"://"+path;
            Log.e(">>>>>", "openResult: "+path );
        }


        try {
            Class aClass=mTables.get(path);
            Intent intent=new Intent(context,aClass);
            if (!(context instanceof Activity)){
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
