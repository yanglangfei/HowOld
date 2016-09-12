package com.util.ylf.mynews.application;

import android.app.Application;
import android.content.Context;

import com.morgoo.droidplugin.PluginHelper;

import org.xutils.x;

/**
 * Created by Administrator on 2016/9/7.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //这里必须在super.onCreate方法之后，顺序不能变
        PluginHelper.getInstance().applicationOnCreate(getBaseContext());
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }

    @Override
    protected void attachBaseContext(Context base) {
        PluginHelper.getInstance().applicationAttachBaseContext(base);
        super.attachBaseContext(base);
    }
}
