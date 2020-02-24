package android.androidVNC;

import android.widget.ImageView;

class FullscreenScaling extends AbstractScaling {
    private VncCanvas mView;

    FullscreenScaling() {
        this(R.id.itemFullScreen, ImageView.ScaleType.FIT_CENTER);
    }

    protected FullscreenScaling(int id, ImageView.ScaleType scaleType) {
        super(id, scaleType);
    }

    @Override
    int getDefaultHandlerId() {
        return R.id.itemFullScreen;
    }

    @Override
    boolean isAbleToPan() {
        return false;
    }

    @Override
    boolean isValidInputMode(int mode) {
        return true;
    }

    void setScaleTypeForActivity(VncCanvasActivity activity) {
        mView = activity.vncCanvas;
        super.setScaleTypeForActivity(activity);
        float scale = getScale();

        activity.vncCanvas.absoluteXPosition = (int) Math.min(
                (mView.getImageWidth() * scale - mView.getWidth()) / 2, 0);
        activity.vncCanvas.absoluteYPosition = (int) Math.min(
                (mView.getImageHeight() * scale - mView.getHeight()) / 2, 0);
        activity.vncCanvas.scrollTo(0, 0);
    }

    @Override
    float getScale() {
        if (mView == null) return 1.0f;
        return Math.min((float) mView.getHeight() / (float) mView.getImageHeight(),
                (float) mView.getWidth() / (float) mView.getImageWidth());
    }
}
