package cn.lytcom.cameraview;

import java.util.Set;

public class Camera2 extends CameraImpl {

    public Camera2(CameraListener cameraListener) {
        super(cameraListener);
    }

    @Override
    boolean start() {
        return false;
    }

    @Override
    void stop() {

    }

    @Override
    boolean isCameraOpened() {
        return false;
    }

    @Override
    void setFacing(int facing) {

    }

    @Override
    int getFacing() {
        return 0;
    }

    @Override
    Set<AspectRatio> getSupportedAspectRatios() {
        return null;
    }

    @Override
    boolean setAspectRatio(AspectRatio ratio) {
        return false;
    }

    @Override
    AspectRatio getAspectRatio() {
        return null;
    }

    @Override
    void setAutoFocus(boolean autoFocus) {

    }

    @Override
    boolean getAutoFocus() {
        return false;
    }

    @Override
    void setFlash(int flash) {

    }

    @Override
    int getFlash() {
        return 0;
    }

    @Override
    void takePicture() {

    }

    @Override
    void startRecordingVideo() {

    }

    @Override
    void stopRecordingVideo() {

    }


}
