package com.util.ylf.mynews.my;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.morgoo.droidplugin.pm.PluginManager;
import com.util.ylf.mynews.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by Administrator on 2016/9/9.
 */
public class Setting extends Activity {

    private BroadcastReceiver receiver;
    private String apkPath;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_setting);
    }


    public void onOpen(View view) {
        try {
            List<PackageInfo> packageNames = PluginManager.getInstance().getInstalledPackages(0);
            Log.i("111", "s:" + packageNames.size());
            for (int i = 0; i < packageNames.size(); i++) {
                String packageName = packageNames.get(i).packageName;
                Log.i("111", "pn:" + packageName);
                PackageManager pm = getPackageManager();
                Intent intent = pm.getLaunchIntentForPackage(packageName);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }


    public void onDown(View view) {
        uploadApk("http://shouji.360tpcdn.com/160808/5d781997e6d2fbef77c415ead323415f/com.tencent.mobileqq_398.apk", "qq");
    }

    public void onInstall(View view) {
        new InstallApk().execute();
    }

    public void uploadApk(String url, final String versionName) {
        final DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

        final DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        //设置下载任务需要的网络状态
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        //设置通知栏信息
        request.setTitle("downLoad apk");
        request.setDescription("下载中...");
        //通知栏是否可见
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        //设置下载文件类型   可以在下载后自动打开
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        String type = mimeTypeMap.getMimeTypeFromExtension(url);
        request.setMimeType(type);

        //设置下载文件保存路径
        request.setDestinationInExternalPublicDir("/plugs", versionName);
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
                    Toast.makeText(Setting.this, "下载完成", Toast.LENGTH_SHORT).show();


                    //获取下载任务信息
                    DownloadManager.Query myDownloadQuery = new DownloadManager.Query();
                    myDownloadQuery.setFilterById(id);

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
                        apkPath = fileName;
                        Log.d("111", fileName + " : " + apkPath);
                    }
                    myDownload.close();


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
                    Toast.makeText(Setting.this, "click notify", Toast.LENGTH_SHORT).show();
                }


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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    class InstallApk extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                int isSuccess = PluginManager.getInstance().installPackage(apkPath, 0);
                if (isSuccess == PluginManager.INSTALL_FAILED_NO_REQUESTEDPERMISSION) {
                    return "权限不足";
                }
            } catch (RemoteException e) {
                e.printStackTrace();
                return "error";
            }
            return "成功";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(Setting.this, "res:" + s, Toast.LENGTH_SHORT).show();
        }
    }
}
