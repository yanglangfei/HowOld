package com.util.ylf.howold;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/9/5.
 */
public class ResultActivity extends Activity{
    private TextView tv_info;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_result);
        tv_info= (TextView) findViewById(R.id.tv_info);
        result=getIntent().getStringExtra("result");
        parseData(result);
    }

    private void parseData(String result)  {
        //{"face":[{"position":{"mouth_right":{"y":54.173667,"x":75.327219},"mouth_left":
        // {"y":53.974833,"x":45.427515},"center":{"y":37.666667,"x":58.284024},
        // "height":45.333333,"width":80.473373,"nose":{"y":42.3915,"x":61.907396},
        // "eye_left":{"y":29.8155,"x":38.826036},"eye_right":{"y":29.214,"x":82.787278}},
        // "attribute":{"race":{"value":"Asian","confidence":99.96340000000001},
        // "gender":{"value":"Male","confidence":99.9917},"smiling":{"value":3.23937},
        // "age":{"value":19,"range":5}},"tag":"",
        // "face_id":"67a9996bc2fe5a73586f54020e23af7f"}],
        // "session_id":"e48efbdfd98d4a76b38dbb27f820fa5b",
        // "img_height":816,"img_width":460,
        // "img_id":"9fe0fd4d704a8e56718cce279aff26f3",
        // "url":null,"response_code":200}
        try {
            Log.i("111","result:"+result);
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
