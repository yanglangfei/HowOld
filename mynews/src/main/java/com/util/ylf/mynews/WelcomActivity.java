package com.util.ylf.mynews;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.util.ylf.mynews.base.Regin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/7.
 */
public class WelcomActivity extends Activity implements View.OnClickListener {
    private ViewPager vp_navication;
    private List<View> pages = new ArrayList<>();
    private TextView tv_turnMain;

    private Button btn_login;

    private EditText et_pwd, et_account;

    private int images[] = {R.drawable.y1, R.drawable.y2, R.drawable.y3, R.drawable.y4};
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_welcom);
        initView();
    }


    private void initView() {
        tv_turnMain = (TextView) findViewById(R.id.tv_turnMain);
        tv_turnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WelcomActivity.this.startActivity(new Intent(WelcomActivity.this, MainActivity.class));
            }
        });
        vp_navication = (ViewPager) findViewById(R.id.vp_navication);
        for (int i = 0; i < images.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(images[i]);
            pages.add(imageView);
        }
        View view = LayoutInflater.from(this).inflate(R.layout.ui_login, null);
        btn_login = (Button) view.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        et_account = (EditText) view.findViewById(R.id.et_account);
        et_pwd = (EditText) view.findViewById(R.id.et_pwd);
        pages.add(view);
        vp_navication.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return pages.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(pages.get(position));
                return pages.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
    }

    @Override
    public void onClick(View v) {
        String pwdStr = et_pwd.getText().toString();
        String accountStr = et_account.getText().toString();
        if (null == accountStr || "".equals(accountStr)) {
            Toast.makeText(WelcomActivity.this, "please input account", Toast.LENGTH_SHORT).show();
            return;
        }

        if (null == pwdStr || "".equals(pwdStr)) {
            Toast.makeText(WelcomActivity.this, "please input password", Toast.LENGTH_SHORT).show();
            return;
        }

        sharedPreferences = getSharedPreferences("userInfo", Context.MODE_APPEND);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        String account = sharedPreferences.getString("account", "");
        if (null == account || "".equals(account)) {
            Toast.makeText(WelcomActivity.this, "to regin", Toast.LENGTH_SHORT).show();
            WelcomActivity.this.startActivity(new Intent(WelcomActivity.this, Regin.class));
        }
        WelcomActivity.this.startActivity(new Intent(WelcomActivity.this, MainActivity.class));

    }
}
