package cn.lytcom.cameraview.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener {

    private CameraKitFragment mCameraKitFragment;

    /**
     * The button of record video
     */
    private Button mRecordButton;

    /**
     * The button of take picture
     */
    private Button mPictureButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_camera);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        if (null == savedInstanceState) {
            mCameraKitFragment = CameraKitFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, mCameraKitFragment)
                .commit();
        } else {
            mCameraKitFragment = (CameraKitFragment) getSupportFragmentManager().findFragmentById(R.id.container);
        }

        mRecordButton = (Button) findViewById(R.id.video);
        mRecordButton.setOnClickListener(this);
        mPictureButton = (Button) findViewById(R.id.picture);
        mPictureButton.setOnClickListener(this);

        int height = getResources().getDisplayMetrics().heightPixels / 4;
        LinearLayout controlLayout = (LinearLayout) findViewById(R.id.control);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) controlLayout.getLayoutParams();
        layoutParams.height = height;
        controlLayout.setLayoutParams(layoutParams);
    }

    private void setFullScreen() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION |
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onClick(View v) {

    }
}
