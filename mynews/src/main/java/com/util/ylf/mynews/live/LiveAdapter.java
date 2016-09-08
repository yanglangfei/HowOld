package com.util.ylf.mynews.live;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.util.ylf.mynews.R;
import com.util.ylf.mynews.model.Video;

import java.util.List;

/**
 * Created by Administrator on 2016/9/7.
 */
public class LiveAdapter extends RecyclerView.Adapter<LiveAdapter.MyAdapter> {


    private final List<Video> videos;
    private final Context context;

    public onItemClickListener listener;

    public LiveAdapter(List<Video> videos, Context context) {
        this.videos = videos;
        this.context = context;

    }

    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public MyAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ui_video_item, parent, false);
        MyAdapter holder = new MyAdapter(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyAdapter holder, final int position) {
        View view = holder.itemView;
        view.setTag(position);
        ImageView iv_news_logo_video = (ImageView) view.findViewById(R.id.iv_news_logo_video);
        TextView tv_title_video = (TextView) view.findViewById(R.id.tv_title_video);
        TextView tv_desc_video = (TextView) view.findViewById(R.id.tv_desc_video);
        tv_title_video.setText(videos.get(position).getT());
        tv_desc_video.setText(videos.get(position).getT1());
        Glide.with(context).load(videos.get(position).getP()).into(iv_news_logo_video);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClickListener(v, (Integer) v.getTag());
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public interface onItemClickListener {
        void onClickListener(View view, int position);
    }

    public class MyAdapter extends RecyclerView.ViewHolder {
        View itemView;

        public MyAdapter(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }
}
