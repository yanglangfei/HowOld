package com.util.ylf.mynews.live;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.util.ylf.mynews.R;
import com.util.ylf.mynews.model.Video;
import com.util.ylf.mynews.news.NewsDetail;

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
public class LiveList extends Fragment {
    private static final String GET_VIDEO = "http://tv.sohu.com/api/";
    private RecyclerView live_rv;
    private LiveAdapter adapter;
    private View view;
    private List<Video> videos = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.ui_livelist, container, false);
        initView();
        initData();
        return view;
    }

    private void initData() {
        RequestParams param = new RequestParams(GET_VIDEO);
        param.setCharset("GBK");
        x.http().get(param, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                String res = result.replaceAll("<!doctype html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"", "")
                        .replaceAll("<head>", "")
                        .replaceAll("<meta http-equiv=content-type content=\"text/html; charset=GBK\">", "");
                String s = res.replaceAll("\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">", "")
                        .replaceAll("<html xmlns=\"http://www.w3.org/1999/xhtml\">", "")
                        .replaceAll("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=EmulateIE7\" />", "")
                        .replaceAll("<title>open api - 搜狐视频</title>", "")
                        .replaceAll("<meta name=\"description\" content=\"open api\" />", "")
                        .replaceAll("<meta name=\"keywords\" content=\"open api\" />", "")
                        .replaceAll("<meta name=\"robots\" content=\"all\" />", "")
                        .replaceAll("</head>", "").replaceAll("<body>", "")
                        .replaceAll("<div id=\"fullscreenad\" style=\"display:none\"></div>", "")
                        .replaceAll("\n", "")
                        .replaceAll(" ", "")
                        .trim();

                if (result != null) {
                    try {
                        JSONObject object = new JSONObject(s);
                        JSONArray array = object.getJSONArray("data");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object1 = array.getJSONObject(i);
                            String p = object1.getString("p");
                            String t = object1.getString("t");
                            String t1 = object1.getString("t1");
                            String l = object1.getString("l");
                            Video video = new Video();
                            video.setP(p);
                            video.setT(t);
                            video.setT1(t1);
                            video.setL(l);
                            videos.add(video);
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
        live_rv = (RecyclerView) view.findViewById(R.id.live_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        live_rv.setLayoutManager(linearLayoutManager);
        adapter = new LiveAdapter(videos, getActivity());
        live_rv.setAdapter(adapter);

        adapter.setListener(new LiveAdapter.onItemClickListener() {
            @Override
            public void onClickListener(View view, int position) {
                Intent intent = new Intent(getActivity(), NewsDetail.class);
                intent.putExtra("url", videos.get(position).getL());
                getActivity().startActivity(intent);
                view.performClick();

            }
        });
    }
}
