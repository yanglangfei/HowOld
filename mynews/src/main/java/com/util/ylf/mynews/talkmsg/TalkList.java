package com.util.ylf.mynews.talkmsg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.util.ylf.mynews.R;
import com.util.ylf.mynews.model.Talk;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/9/7.
 */
public class TalkList extends Fragment {
    private static final String GET_TALK = "http://api.juheapi.com/japi/toh";

    private View view;
    private RecyclerView talk_list;
    private TalkAdapter talkAdapter;
    private List<Talk> talks = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.ui_talk_list, container, false);
        initView();
        initData();
        return view;
    }

    private void initData() {
        Date date = new Date();
        RequestParams param = new RequestParams(GET_TALK);
        param.addParameter("key", "884f0858c1a665ba2ab48abe40df4277");
        param.addParameter("v", "1.0");
        param.addParameter("month", date.getMonth() + 1);
        param.addParameter("day", date.getDate());
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
                        JSONArray array = object.getJSONArray("result");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object1 = array.getJSONObject(i);
                            String _id = object1.getString("_id");
                            String title = object1.getString("title");
                            String des = object1.getString("des");
                            int year = object1.getInt("year");
                            Talk talk = new Talk();
                            talk.setTitle(title);
                            talk.set_id(_id);
                            talk.setDes(des);
                            talk.setYear(year);
                            talks.add(talk);
                        }
                        talkAdapter.notifyDataSetChanged();
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
        talk_list = (RecyclerView) view.findViewById(R.id.talk_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        talk_list.setLayoutManager(linearLayoutManager);
        talkAdapter = new TalkAdapter(talks, getActivity());
        talk_list.setAdapter(talkAdapter);
        talkAdapter.setListener(new TalkAdapter.onItemClickListener() {
            @Override
            public void onClickListener(View view, int p) {
                Intent intent = new Intent();
                intent.putExtra("id", talks.get(p).get_id());
                intent.setClass(getActivity(), TalkDetail.class);
                getActivity().startActivity(intent);
            }
        });

    }
}
