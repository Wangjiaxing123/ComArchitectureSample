package com.newtrekwang.simpleroute_2;

import android.app.Application;

import com.newtrekwang.router.RouterManager;

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
