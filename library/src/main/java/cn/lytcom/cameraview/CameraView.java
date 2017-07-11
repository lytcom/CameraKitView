package cn.lytcom.cameraview;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import java.io.File;
import java.util.Set;

public class CameraView extends FrameLayout {

    CameraImpl mCameraImpl;
    CameraListenerWrapper mCameraListenerWrapper;

    public CameraView(@NonNull Context context) {
        this(context, null);
    }

    public CameraView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CameraView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mCameraListenerWrapper = new CameraListenerWrapper();
        mCameraImpl = createCameraImpl();

        TypedArray typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.CameraView,
            defStyleAttr,
            R.style.Widget_CameraView);

        setFacing(typedArray.getInt(R.styleable.CameraView_facing, CameraConstants.FACING_BACK));
        String aspectRatio = typedArray.getString(R.styleable.CameraView_aspectRatio);
        if (!TextUtils.isEmpty(aspectRatio)) {
            setAspectRatio(AspectRatio.parse(aspectRatio));
        } else {
            setAspectRatio(CameraConstants.DEFAULT_ASPECT_RATIO);
        }
        setAutoFocus(typedArray.getBoolean(R.styleable.CameraView_autoFocus, true));
        setFlash(typedArray.getInt(R.styleable.CameraView_flash, CameraConstants.FLASH_AUTO));
        typedArray.recycle();
    }

    public CameraImpl createCameraImpl() {
        CameraImpl cameraImpl;
        if (Build.VERSION.SDK_INT < 21) {
            cameraImpl = new Camera1(mCameraListenerWrapper);
        } else {
            cameraImpl = new Camera2(mCameraListenerWrapper);
        }
        return cameraImpl;
    }

    private class CameraListenerWrapper implements CameraListener {

        private CameraListener cameraListener;

        void setCameraListener(CameraListener cameraListener) {
            this.cameraListener = cameraListener;
        }

        @Override
        public void onCameraOpened() {
            if (cameraListener != null) {
                cameraListener.onCameraOpened();
            }
        }

        @Override
        public void onCameraClosed() {
            if (cameraListener != null) {
                cameraListener.onCameraClosed();
            }
        }

        @Override
        public void onPictureTaken(byte[] jpeg) {
            if (cameraListener != null) {
                cameraListener.onPictureTaken(jpeg);
            }
        }

        @Override
        public void onVideoTaken(File video) {
            if (cameraListener != null) {
                cameraListener.onVideoTaken(video);
            }
        }
    }

    public void setCameraListener(CameraListener cameraListener) {
        mCameraListenerWrapper.setCameraListener(cameraListener);
    }

    public void start() {
        if (!mCameraImpl.start()) {
            mCameraImpl = new Camera1(mCameraListenerWrapper);
            mCameraImpl.start();
        }
    }

    public void stop() {
        mCameraImpl.stop();
    }

    public boolean isCameraOpened() {
        return mCameraImpl.isCameraOpened();
    }

    public Set<AspectRatio> getSupportedAspectRatios() {
        return mCameraImpl.getSupportedAspectRatios();
    }

    public void setAspectRatio(AspectRatio ratio) {
        mCameraImpl.setAspectRatio(ratio);
    }

    public AspectRatio getAspectRatio() {
        return mCameraImpl.getAspectRatio();
    }

    public void setFacing(int facing) {
        mCameraImpl.setFacing(facing);
    }

    public int getFacing() {
        return mCameraImpl.getFacing();
    }

    public void setAutoFocus(boolean autoFocus) {
        mCameraImpl.setAutoFocus(autoFocus);
    }

    public boolean getAutoFocus() {
        return mCameraImpl.getAutoFocus();
    }

    public void setFlash(int flash) {
        mCameraImpl.setFlash(flash);
    }

    public int getFlash() {
        return mCameraImpl.getFlash();
    }

    public void takePicture() {
        mCameraImpl.takePicture();
    }

    public void startRecordingVideo() {
        mCameraImpl.startRecordingVideo();
    }

    public void stopRecordingVideo() {
        mCameraImpl.stopRecordingVideo();
    }
}
