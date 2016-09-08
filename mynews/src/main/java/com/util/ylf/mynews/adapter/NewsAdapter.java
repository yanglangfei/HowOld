package com.util.ylf.mynews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.util.ylf.mynews.R;
import com.util.ylf.mynews.model.News;

import java.util.List;

/**
 * Created by Administrator on 2016/9/7.
 */
public class NewsAdapter  extends RecyclerView.Adapter<NewsAdapter.MyHolder>{


    private final List<News> news;
    private final Context context;

    private  onItemClickListener  listener;

    public NewsAdapter(List<News> news, Context context) {
        this.news=news;
        this.context=context;

    }

    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ui_news_item,parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        View v=holder.itemView;
        v.setTag(position);
        ImageView iv_news_logo= (ImageView) v.findViewById(R.id.iv_news_logo);
        TextView tv_title= (TextView) v.findViewById(R.id.tv_title);
        TextView tv_time= (TextView) v.findViewById(R.id.tv_time);
        Glide.with(context).load(news.get(position).getThumbnail_pic_s()).into(iv_news_logo);
        tv_title.setText(news.get(position).getTitle());
        tv_time.setText(news.get(position).getDate() );
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onClick(v, (Integer) v.getTag());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public  class  MyHolder extends  RecyclerView.ViewHolder{
        View itemView;

        public MyHolder(View itemView) {
            super(itemView);
            this.itemView=itemView;
        }
    }

    public  interface  onItemClickListener{
        void onClick(View view,int position);
    }
}
