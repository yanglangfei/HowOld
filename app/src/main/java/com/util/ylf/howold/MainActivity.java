package com.util.ylf.howold;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;
import com.util.ylf.howold.utils.Contact;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.List;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

public class MainActivity extends Activity {
    private ImageView iv_pic;
    private String imagePath;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result= (String) msg.obj;
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("result", result);
            startActivity(intent);
        }
    };
    private Bitmap bm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_pic= (ImageView) findViewById(R.id.iv_pic);
    }

    public  void  onPicture(View view){
        GalleryFinal.openGallerySingle(100, new GalleryFinal.OnHanlderResultCallback() {
            @Override
            public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                if (reqeustCode == 100) {
                    if (resultList != null && resultList.size() > 0) {
                        PhotoInfo info = resultList.get(0);
                        imagePath = info.getPhotoPath();
                        Bitmap bm = BitmapFactory.decodeFile(imagePath);
                        iv_pic.setImageBitmap(bm);
                        resizePhoto();
                        checkFace();
                    }

                }

            }

            @Override
            public void onHanlderFailure(int requestCode, String errorMsg) {
                Toast.makeText(MainActivity.this, "open gallery fail", Toast.LENGTH_SHORT).show();

            }
        });


    }


    public  void  onToLocation(View view){
        startActivity(new Intent(this, LocationActivity.class));
    }


    public  void  onToMove(View view){
        startActivity(new Intent(this,MoveActivity.class));
    }

    private void resizePhoto() {
        //得到BitmapFactory的操作权
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 如果设置为 true ，不获取图片，不分配内存，但会返回图片的高宽度信息。
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath,options);
        //计算宽高要尽可能小于1024
        double ratio=Math.max(options.outWidth*1.0d/1024f,options.outHeight*1.0d/1024f);
        //设置图片缩放的倍数。假如设为 4 ，则宽和高都为原来的 1/4 ，则图是原来的 1/16 。
        options.inSampleSize=(int)Math.ceil(ratio);
        //我们这里并想让他显示图片所以这里要置为false
        options.inJustDecodeBounds=false;
        //利用Options的这些值就可以高效的得到一幅缩略图。
        bm=BitmapFactory.decodeFile(imagePath,options);
    }

    private void checkFace() {
        if(imagePath!=null){
          new Thread(){
              @Override
              public void run() {
                  HttpRequests requests=new HttpRequests(Contact.KEY,Contact.Scrent,true,true);
                  //从0，0点挖取整个视图，后两个参数是目标大小
                  Bitmap bitmapsmall = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight());
                  //这里api要求传入一个字节数组数据，因此要用字节数组输出流
                  ByteArrayOutputStream stream=new ByteArrayOutputStream();
                    /*Bitmap.compress()方法可以用于将Bitmap-->byte[]
                      既将位图的压缩到指定的OutputStream。如果返回true，
                      位图可以通过传递一个相应的InputStream BitmapFactory.decodeStream（重建）
                      第一个参数可设置JPEG或PNG格式,第二个参数是图片质量，第三个参数是一个流信息*/
                  bitmapsmall.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                  byte[] arrays=stream.toByteArray();
                  //实现发送参数功能
                  PostParameters params=new PostParameters();
                  //发送数据
                  params.setImg(arrays);
                  try {
                      JSONObject result = requests.detectionDetect(params);
                      handler.obtainMessage(100,result.toString()).sendToTarget();
                  } catch (FaceppParseException e) {
                      e.printStackTrace();
                      Log.i("111","error:"+e.getErrorCode());
                  }
              }
          }.start();


        }

    }

    public  void  onCamera(View view){
        GalleryFinal.openCamera(200, new GalleryFinal.OnHanlderResultCallback() {
            @Override
            public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                if(reqeustCode==200){
                    if(resultList!=null&&resultList.size()>0){
                        PhotoInfo info=resultList.get(0);
                        imagePath=info.getPhotoPath();
                        Bitmap bm= BitmapFactory.decodeFile(imagePath);
                        iv_pic.setImageBitmap(bm);
                        resizePhoto();
                        checkFace();
                    }

                }
            }

            @Override
            public void onHanlderFailure(int requestCode, String errorMsg) {
                Toast.makeText(MainActivity.this, "open camera fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
