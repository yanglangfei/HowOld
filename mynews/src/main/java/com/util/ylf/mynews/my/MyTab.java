package com.util.ylf.mynews.my;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.util.ylf.mynews.R;

/**
 * Created by Administrator on 2016/9/7.
 */
public class MyTab extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.ui_my_tab,container,false);
        return view;
    }
}
