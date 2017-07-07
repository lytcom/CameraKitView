package cn.lytcom.cameraview;

import java.io.File;

public abstract class CameraListener {

    public void onCameraOpened() {

    }

    public void onCameraClosed() {

    }

    public void onPictureTaken(byte[] jpeg) {

    }

    public void onVideoTaken(File video) {

    }
}
