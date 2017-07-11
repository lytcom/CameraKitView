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

import cn.lytcom.cameraview.CameraView;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener {

    private CameraView mCameraView;

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
        mCameraView = (CameraView) findViewById(R.id.camera_view);

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
    protected void onResume() {
        super.onResume();
        mCameraView.start();
    }

    @Override
    protected void onPause() {
        mCameraView.stop();
        super.onPause();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.picture: {
                mCameraView.takePicture();
                break;
            }
            case R.id.video: {
                if (mCameraView.isRecordingVideo()) {
                    mCameraView.stopRecordingVideo();
                    mRecordButton.setText(R.string.start_record_video);
                    mPictureButton.setEnabled(true);
                } else {
                    mPictureButton.setEnabled(false);
                    mCameraView.startRecordingVideo();
                    mRecordButton.setText(R.string.stop_record_video);
                }
                break;
            }
        }
    }
}
