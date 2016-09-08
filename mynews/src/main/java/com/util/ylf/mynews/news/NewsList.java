package com.util.ylf.mynews.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.util.ylf.mynews.R;
import com.util.ylf.mynews.adapter.NewsAdapter;
import com.util.ylf.mynews.model.News;
import com.util.ylf.mynews.utils.Contact;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/7.
 */
public class NewsList extends Fragment implements RadioGroup.OnCheckedChangeListener {
    private static final String GET_NEWS = "http://v.juhe.cn/toutiao/index";
    private RecyclerView rv;
    private NewsAdapter adapter;
    private List<News> newsArray = new ArrayList<>();
    //top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
    private String typeArray[] = {"top", "shehui", "guonei", "guoji", "yule", "tiyu", "junshi", "keji", "caijing", "shishang"};
    private RadioGroup group;

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.newslist, container, false);
        initView();
        newsArray.clear();
        return view;
    }

    private void initData(String type) {
        RequestParams param = new RequestParams(GET_NEWS);
        param.addBodyParameter("type", type);
        param.addBodyParameter("key", Contact.KEY);
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
                        JSONObject res = object.getJSONObject("result");

                        JSONArray array = res.getJSONArray("data");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject n = array.getJSONObject(i);
                            String title = n.getString("title");
                            String thumbnail_pic_s = n.getString("thumbnail_pic_s");
                            String date = n.getString("date");
                            String url = n.getString("url");
                            News news = new News();
                            news.setTitle(title);
                            news.setUrl(url);
                            news.setThumbnail_pic_s(thumbnail_pic_s);
                            news.setDate(date);
                            newsArray.add(news);
                        }
                        adapter.notifyDataSetChanged();
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

    private void initView() {
      /*  View v = LayoutInflater.from(getActivity()).inflate(R.layout.ui_pop, null);
        PopupWindow popupWindow = new PopupWindow(v, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        JCVideoPlayerStandard video_lay = (JCVideoPlayerStandard) v.findViewById(R.id.video_lay);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        video_lay.setUp("http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4", JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, "傻子来了");
        video_lay.thumbImageView.setImageResource(R.drawable.u);
        video_lay.thumbImageView.setImageURI(Uri.parse("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640"));
        video_lay.startPlayLocic();*/





        rv = (RecyclerView) view.findViewById(R.id.rv);
        LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(lm);
        adapter = new NewsAdapter(newsArray, getActivity());
        rv.setAdapter(adapter);
        group = (RadioGroup) view.findViewById(R.id.group);
        group.setOnCheckedChangeListener(this);
        RadioButton radioButton = (RadioButton) group.getChildAt(0);
        radioButton.setChecked(true);
        adapter.setListener(new NewsAdapter.onItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), NewsDetail.class);
                intent.putExtra("url", newsArray.get(position).getUrl());
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        for (int i = 0; i < group.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) group.getChildAt(i);
            if (radioButton.isChecked()) {
                newsArray.clear();
                initData(typeArray[i]);
            }
        }
    }
}
