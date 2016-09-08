package com.util.ylf.mynews;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.util.ylf.mynews.live.LiveList;
import com.util.ylf.mynews.my.MyTab;
import com.util.ylf.mynews.news.NewsList;
import com.util.ylf.mynews.talkmsg.TalkList;

public class MainActivity extends FragmentActivity {
    private FragmentTabHost tabHost;
    private String tabTxt[] = {"新闻", "直播", "话题", "我"};
    private Class fragments[] = {NewsList.class, LiveList.class, TalkList.class, MyTab.class};
    private int images[] = {R.drawable.tab_news_select, R.drawable.tab_live_select, R.drawable.tab_talk_select, R.drawable.tab_my_select};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.realContent);

        for (int i = 0; i < tabTxt.length; i++) {
            TabHost.TabSpec tab = tabHost.newTabSpec(tabTxt[i]).setIndicator(getViews(i));
            tabHost.addTab(tab, fragments[i], null);
        }


    }

    private View getViews(int i) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab_item, null);
        ImageView tab_iv = (ImageView) view.findViewById(R.id.tab_iv);
        TextView tv_tab = (TextView) view.findViewById(R.id.tv_tab);
        tv_tab.setText(tabTxt[i]);
        tab_iv.setBackground(ContextCompat.getDrawable(this, images[i]));
        return view;
    }


}
