package com.util.ylf.mynews.talkmsg;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.util.ylf.mynews.R;
import com.util.ylf.mynews.model.Talk;

import java.util.List;

/**
 * Created by Administrator on 2016/9/8.
 */
public class TalkAdapter extends RecyclerView.Adapter<TalkAdapter.MyHolder> {


    private final List<Talk> talks;
    private final Context context;

    private onItemClickListener listener;

    public TalkAdapter(List<Talk> talks, Context context) {
        this.talks = talks;
        this.context = context;
    }


    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ui_video_item, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        View view = holder.itemView;
        view.setTag(position);
        ImageView iv_news_logo_video = (ImageView) view.findViewById(R.id.iv_news_logo_video);
        TextView tv_title_video = (TextView) view.findViewById(R.id.tv_title_video);
        TextView tv_desc_video = (TextView) view.findViewById(R.id.tv_desc_video);
        tv_desc_video.setText(talks.get(position).getDes());
        tv_title_video.setText(talks.get(position).getTitle());
        iv_news_logo_video.setVisibility(View.GONE);
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
        return talks.size();
    }

    public interface onItemClickListener {
        void onClickListener(View view, int p);
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        View itemView;

        public MyHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }
}
