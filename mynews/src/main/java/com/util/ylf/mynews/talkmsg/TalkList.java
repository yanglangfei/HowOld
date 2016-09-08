package com.util.ylf.mynews.talkmsg;

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
public class TalkList extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.ui_talk_list,container,false);
        return view;
    }
}
