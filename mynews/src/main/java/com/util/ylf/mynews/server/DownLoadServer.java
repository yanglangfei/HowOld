package com.util.ylf.mynews.server;

import android.app.DownloadManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;

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
        uploadApk("http://p3.so.qhmsg.com/bdr/326__/t0184315399f3f5fab8.jpg", "jcp");
    }

    public void uploadApk(String url, final String versionName) {
        final DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

        final DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        //设置下载任务需要的网络状态
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        //设置通知栏信息
        request.setTitle("downLoad complete");
        request.setDescription("下载完成,哈哈哈");
        //通知栏是否可见
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        //设置下载文件类型   可以在下载后自动打开
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        String type = mimeTypeMap.getMimeTypeFromExtension(url);
        request.setMimeType(type);

        //设置下载文件保存路径
        request.setDestinationInExternalPublicDir("/dowmload", versionName);
        //开始下载    返回唯一id可以进行断点  下载 和获取任务状态
        final long id = manager.enqueue(request);

        //监听任务下载完成  和 任务点击
        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        filter.addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED);
        //注册下载完成的广播通知
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                long reference = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
                    Toast.makeText(DownLoadServer.this, "下载完成", Toast.LENGTH_SHORT).show();
                    try {
                        manager.openDownloadedFile(reference);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    String downloadPath = Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + versionName;
                    installAPK(new File(downloadPath));
                }

                if (intent.getAction().equals(DownloadManager.ACTION_NOTIFICATION_CLICKED)) {
                    Toast.makeText(DownLoadServer.this, "click notify", Toast.LENGTH_SHORT).show();
                }

             /*   //获取下载任务信息
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
                        int downReason = myDownload.getColumnIndex(DownloadManager.COLUMN_REASON);
                        int downState = myDownload.getColumnIndex(DownloadManager.COLUMN_STATUS);

                        String fileName = myDownload.getString(fileNameIdx);
                        String fileUri = myDownload.getString(fileUriIdx);
                        //获取下载状态
                        //    DownloadManager.STATUS_SUCCESSFUL     成功
                        //    DownloadManager.STATUS_FAILED          失败
                        //    DownloadManager.STATUS_PAUSED         暂停
                        //    DownloadManager.STATUS_RUNNING        正在下载
                        //    DownloadManager.STATUS_PENDING        下载延迟
                        int state = myDownload.getInt(downState);
                        //下载失败原因
                        int resaon = myDownload.getInt(downReason);

                        // TODO Do something with the file.
                        Log.d("111", fileName + " : " + fileUri);
                    }
                    myDownload.close();
                }*/


            }
        };
        registerReceiver(receiver, filter);


    }

    private void installAPK(File file) {
        //安装apk
        if (!file.exists()) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse("file://" + file.toString());
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        //在服务中开启activity必须设置flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        //任务结束   销毁自己
        stopSelf();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
