package com.util.ylf.howold.application;

import android.app.Application;

import com.emokit.sdk.util.SDKAppInit;
import com.util.ylf.howold.utils.UILImageLoader;

import org.xutils.x;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;

/**
 * Created by Administrator on 2016/9/5.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //xUtils
        x.Ext.init(this);
        x.Ext.setDebug(true);
        //表情分析
        SDKAppInit.createInstance(this);
        SDKAppInit.setDebugMode(true);



        //相机  相册
        ThemeConfig theme = new ThemeConfig.Builder().build();  //设置主题
        FunctionConfig function = new FunctionConfig.Builder().build();  //配置功能
        ImageLoader loader = new UILImageLoader();  //配置ImageLoader
        CoreConfig config = new CoreConfig.Builder(this, loader, theme).setFunctionConfig(function).build();
        GalleryFinal.init(config);
    }
}
