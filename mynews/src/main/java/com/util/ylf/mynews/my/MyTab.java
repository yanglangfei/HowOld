package com.util.ylf.mynews.my;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.util.ylf.mynews.R;
import com.util.ylf.mynews.server.DownLoadServer;

/**
 * Created by Administrator on 2016/9/7.
 */
public class MyTab extends Fragment implements View.OnClickListener {

    private View view;

    private RelativeLayout rela_My;
    private RelativeLayout lay_setting;
    private RelativeLayout app_center;
    private RelativeLayout sysMsg_lay;
    private RelativeLayout upload_lay;
    private ImageView my_logo;
    private BroadcastReceiver receiver;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.ui_my_tab, container, false);
        initView();
        return view;
    }

    private void initView() {
        rela_My = (RelativeLayout) view.findViewById(R.id.lay_my);
        my_logo = (ImageView) view.findViewById(R.id.my_logo);
        rela_My.setOnClickListener(this);
        lay_setting = (RelativeLayout) view.findViewById(R.id.lay_setting);
        lay_setting.setOnClickListener(this);
        app_center = (RelativeLayout) view.findViewById(R.id.app_center);
        app_center.setOnClickListener(this);
        sysMsg_lay = (RelativeLayout) view.findViewById(R.id.sysMsg_lay);
        sysMsg_lay.setOnClickListener(this);
        upload_lay = (RelativeLayout) view.findViewById(R.id.upload_lay);
        upload_lay.setOnClickListener(this);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.u);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getActivity().getResources(), bitmap);
        roundedBitmapDrawable.setCircular(true);
        roundedBitmapDrawable.setAntiAlias(true);
        roundedBitmapDrawable.setCornerRadius(100);
        my_logo.setImageDrawable(roundedBitmapDrawable);



        /*Glide.with(getActivity()).load("").asBitmap().into(new BitmapImageViewTarget(my_logo) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                roundedBitmapDrawable.setCircular(true);
                my_logo.setImageBitmap(resource);
            }
        });*/

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lay_my:
                startActivity(new Intent(getActivity(), MyInfo.class));
                break;
            case R.id.lay_setting:
                startActivity(new Intent(getActivity(), Setting.class));
                break;
            case R.id.app_center:
                startActivity(new Intent(getActivity(), AppCenter.class));
                break;
            case R.id.upload_lay:
                getActivity().startService(new Intent(getActivity(), DownLoadServer.class));
                break;
            case R.id.sysMsg_lay:
                startActivity(new Intent(getActivity(), SysMessage.class));
                break;
            default:
                break;

        }


    }


}
