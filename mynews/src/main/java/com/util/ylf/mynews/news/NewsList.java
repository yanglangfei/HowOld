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
public class NewsList extends Fragment implements RadioGroup.OnCheckedChangeListener{
    private RecyclerView rv;
    private NewsAdapter adapter;
    private List<News> newsArray = new ArrayList<>();
    private static final String GET_NEWS = "http://v.juhe.cn/toutiao/index";
    //top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
    private String typeArray[] = {"top", "shehui", "guonei", "guoji", "yule", "tiyu", "junshi", "keji", "caijing", "shishang"};
    private RadioGroup group;

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.newslist,container,false);
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
        rv = (RecyclerView) view.findViewById(R.id.rv);
        LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(lm);
        adapter = new NewsAdapter(newsArray, getActivity());
        rv.setAdapter(adapter);
        group = (RadioGroup)view. findViewById(R.id.group);
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
