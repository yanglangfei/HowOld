package com.util.ylf.howold;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Administrator on 2016/9/6.
 */
public class MoveActivity  extends Activity implements SurfaceHolder.Callback {
    private SurfaceView surfaceView;
    private SurfaceHolder holder;
    private Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_move);
        initView();
    }

    private void initView() {
        surfaceView= (SurfaceView) findViewById(R.id.surfaceView);
        holder=surfaceView.getHolder();
    }

    @Override
    public void surfaceCreated(final SurfaceHolder holder) {




    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
