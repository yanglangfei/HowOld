package com.util.ylf.mynews.server;

import android.app.DownloadManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2016/9/9.
 */
public class DownLoadServer extends Service {
    private BroadcastReceiver receiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        uploadApk("http://imtt.dd.qq.com/16891/49D0FEB62F426DBA907480A8B9B6A058.apk?fsname=com.example.androidnetwork_2.6_16.apk&csr=4d5s");
    }

    public void uploadApk(String url) {
        final DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

        final DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        final long id = manager.enqueue(request);
        //设置下载任务需要的网络状态
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        request.setTitle("downLoad complete");
        request.setDescription("下载完成,哈哈哈");
        //设置下载文件保存路径
        //  request.setDestinationUri(Uri.parse(""));
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);


        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);


        //注册下载完成的广播通知
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                long reference = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                if (id == reference) {
                    DownloadManager.Query myDownloadQuery = new DownloadManager.Query();
                    myDownloadQuery.setFilterById(reference);

                    Cursor myDownload = manager.query(myDownloadQuery);
                    if (myDownload.moveToFirst()) {
                        //下载文件路径
                        int fileNameIdx =
                                myDownload.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME);
                        int fileUriIdx =
                                myDownload.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI);
                        //下载失败原因
                        int reason = myDownload.getColumnIndex(DownloadManager.COLUMN_REASON);

                        String fileName = myDownload.getString(fileNameIdx);
                        String fileUri = myDownload.getString(fileUriIdx);

                        // TODO Do something with the file.
                        Log.d("111", fileName + " : " + fileUri);
                    }
                    myDownload.close();
                }
            }
        };
        registerReceiver(receiver, filter);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
