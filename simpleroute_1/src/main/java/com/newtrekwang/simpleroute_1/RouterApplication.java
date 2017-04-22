package com.newtrekwang.simpleroute_1;

import android.app.Application;

public class RouterApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initRouter();
    }

    private void initRouter() {
        RouterManager routerManager=RouterManager.getRouterManager();
        routerManager.setmSchemeprefix("router");
       routerManager.init();
    }
}
