package com.util.ylf.mynews.my;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;

import com.util.ylf.mynews.R;
import com.util.ylf.mynews.model.Video;

/**
 * Created by Administrator on 2016/9/9.
 */
public class MyInfo extends Activity implements LoaderManager.LoaderCallbacks<Video> {
    private LoaderManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_my_info);
        manager = getLoaderManager();
        manager.initLoader(1000, null, this);

    }

    @Override
    public Loader<Video> onCreateLoader(int id, Bundle args) {
        //Loader 根据指定id创建
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Video> loader, Video data) {
        // Load 完成

    }

    @Override
    public void onLoaderReset(Loader<Video> loader) {
        //Loader 重置


    }


    class Load extends AsyncTaskLoader<Video> {

        public Load(Context context) {
            super(context);
        }


        @Override
        public Video loadInBackground() {
            return null;
        }
    }


}
