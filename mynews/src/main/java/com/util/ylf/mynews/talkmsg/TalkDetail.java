package com.util.ylf.mynews.talkmsg;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.util.ylf.mynews.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by Administrator on 2016/9/8.
 */
public class TalkDetail extends Activity {
    private static final String GET_TALK_DETAIL = "http://api.juheapi.com/japi/tohdet";
    private static final String url2 = "http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4";
    private static final String url1 = "http://devimages.apple.com/iphone/samples/bipbop/gear1/prog_index.m3u8";
    private String id;
    private TextView talkTitle, talkContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_talk_detail);
        initView();
    }

    private void initView() {
     /*   player1= (JCVideoPlayerStandard) findViewById(R.id.player1);
        player2= (JCVideoPlayerSimple) findViewById(R.id.player2);
        //http://v.youku.com/v_show/id_XMTcxNzI2NjM5Mg==.html?old
        player1.setUp(url2, JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, "傻子来了");
        player2.setUp(url2, JCVideoPlayerSimple.SCREEN_LAYOUT_LIST, "傻子走了");
        player1.thumbImageView.setImageURI(Uri.parse("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640"));
        player1.startPlayLocic();*/
        talkTitle = (TextView) findViewById(R.id.talk_titll);
        talkContent = (TextView) findViewById(R.id.talk_content);


        id = getIntent().getStringExtra("id");
        initData();
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //   JCVideoPlayer.releaseAllVideos();
    }

    private void initData() {
        RequestParams param = new RequestParams(GET_TALK_DETAIL);
        param.addParameter("key", "884f0858c1a665ba2ab48abe40df4277");
        param.addParameter("id", id);
        param.addParameter("v", "1.0");
        x.http().get(param, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    try {
                        JSONObject object = new JSONObject(result);
                        JSONObject object1 = object.getJSONObject("result");
                        String content = object1.getString("content");
                        String title = object1.getString("title");
                        talkTitle.setText(title);
                        talkContent.setText(content);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
