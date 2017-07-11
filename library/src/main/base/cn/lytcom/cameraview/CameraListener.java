package cn.lytcom.cameraview;

import java.io.File;

public interface CameraListener {

    void onCameraOpened();

    void onCameraClosed();

    void onPictureTaken(byte[] jpeg);

    void onVideoTaken(File video);
}
