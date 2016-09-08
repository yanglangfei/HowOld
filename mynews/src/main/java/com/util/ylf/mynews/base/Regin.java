package com.util.ylf.mynews.base;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.util.ylf.mynews.R;

/**
 * Created by Administrator on 2016/9/8.
 */
public class Regin extends Activity {
    private EditText et_account, et_pwd;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_regin);
        initView();
    }

    private void initView() {
        et_account = (EditText) findViewById(R.id.rt_account);
        et_pwd = (EditText) findViewById(R.id.rt_password);
    }

    public void onRegin(View view) {
        String account = et_account.getText().toString();
        String password = et_pwd.getText().toString();
        if (null == account || "".equals(account)) {
            Toast.makeText(Regin.this, "please input account", Toast.LENGTH_SHORT).show();
            return;
        }

        if (null == password || "".equals(password)) {
            Toast.makeText(Regin.this, "please input password", Toast.LENGTH_SHORT).show();
            return;
        }

        sharedPreferences = getSharedPreferences("userInfo", Context.MODE_APPEND);
        edit = sharedPreferences.edit();
        edit.putString("account", account);
        edit.putString("password", password);
        edit.commit();
    }
}
