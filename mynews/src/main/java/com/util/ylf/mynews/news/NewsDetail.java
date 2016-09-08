package com.util.ylf.mynews.news;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;

import com.util.ylf.mynews.R;

/**
 * Created by Administrator on 2016/9/7.
 */
public class NewsDetail extends Activity implements View.OnClickListener {
    private WebView wv;
    private String url;
    private ImageButton iv_finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_new_detail);
        initView();
    }

    private void initView() {
        wv = (WebView) findViewById(R.id.wv);
        wv.getSettings().setJavaScriptEnabled(true);
        iv_finish = (ImageButton) findViewById(R.id.iv_finish);
        iv_finish.setOnClickListener(this);
        url = getIntent().getStringExtra("url");
        wv.loadUrl(url);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_finish:
                this.finish();
                break;
            default:
                break;
        }
    }
}
