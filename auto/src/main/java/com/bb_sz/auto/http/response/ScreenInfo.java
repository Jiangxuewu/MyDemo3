package com.bb_sz.auto.http.response;

/**
 * Created by Administrator on 2017/4/11.
 */

public class ScreenInfo {

    /**
     * _id : 1
     * width : 480
     * height : 854
     * dpi : 240
     * density : 1.5
     */

    private String _id;
    private int width;
    private int height;
    private int dpi;
    private float density;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDpi() {
        return dpi;
    }

    public void setDpi(int dpi) {
        this.dpi = dpi;
    }

    public float getDensity() {
        return density;
    }

    public void setDensity(float density) {
        this.density = density;
    }
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("refresh.height=").append(height).append("\n");
        sb.append("refresh.width=").append(width).append("\n");
        sb.append("refresh.dpi=").append(dpi).append("\n");
        sb.append("refresh.density=").append(density).append("\n");
        return sb.toString();
    }
}
