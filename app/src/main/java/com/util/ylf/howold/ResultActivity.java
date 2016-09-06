package com.util.ylf.howold;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.emokit.sdk.InitListener;
import com.emokit.sdk.basicinfo.AdvancedInformation;
import com.emokit.sdk.record.EmotionDetect;
import com.emokit.sdk.record.EmotionVoiceListener;
import com.emokit.sdk.util.SDKAppInit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/9/5.
 */
public class ResultActivity extends Activity{
    private TextView tv_info;
    private String result;
    private EmotionVoiceListener emotionVoiceListener=new EmotionVoiceListener() {
        @Override
        public void onVolumeChanged(int i) {
            //实时分贝
            Log.i("111","change:"+i);

        }

        @Override
        public void onBeginOfSpeech() {
            //开始说话
            Log.i("111","begin:");

        }

        @Override
        public void onEndOfSpeech() {
            //结束说话
            Log.i("111","end");

        }

        @Override
        public void onVoiceResult(String s) {
            //分析结果
            Log.i("111","分析:"+s);


        }
    };

    private InitListener initListener=new InitListener() {
        @Override
        public void onInit(int i) {
            //初始化用户信息   用户名      用户名
            AdvancedInformation pp = AdvancedInformation.getSingleton(ResultActivity.this);
            SDKAppInit.registerforuid("HowOld",pp.getp().getSimSerial(),null);

        }
    };
    private EmotionDetect detect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_result);
        tv_info= (TextView) findViewById(R.id.tv_info);
        result=getIntent().getStringExtra("result");
        parseData(result);
    }



   public  void  onVoiceExpression(View view){
       //结束监听
       detect=EmotionDetect.createRecognizer(this, initListener);
       //启动监听
       detect.startListening(emotionVoiceListener);
   }

    public  void  onStopAnsy(View view){
        detect.stopListening();
    }

    private void parseData(String result)  {
        try {
            JSONObject object=new JSONObject(result);
            JSONArray array=object.getJSONArray("face");
            for (int i = 0; i <array.length() ; i++) {
                JSONObject face=array.getJSONObject(i);
                JSONObject attribute=face.getJSONObject("attribute");
                JSONObject age=attribute.getJSONObject("age");
                int value=age.getInt("value");
                JSONObject gender=attribute.getJSONObject("gender");
                String genderValue=gender.getString("value");
                JSONObject race=attribute.getJSONObject("race");
                String raceValue=race.getString("value");
                JSONObject smiling=attribute.getJSONObject("smiling");
                String smilingValue=smiling.getString("value");
                tv_info.setText("年龄："+value+"\n"+"性别:"+genderValue+"\n"+"地区:"+raceValue+"\n"+"微笑程度:"+smilingValue);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
