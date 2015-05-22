package com.nawab.instapics.instapic;

import android.graphics.Bitmap;

/**
 * Created by Macbook on 21/05/15.
 */
public class User {

    private String user_name;
    private String profile_pic;
    private String uploaded_photo;
    private Double pic_uploaded_time;
    private Bitmap bitmap;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getUploaded_photo() {
        return uploaded_photo;
    }

    public void setUploaded_photo(String uploaded_photo) {
        this.uploaded_photo = uploaded_photo;
    }

    public Double getPic_uploaded_time() {
        return pic_uploaded_time;
    }

    public void setPic_uploaded_time(Double pic_uploaded_time) {
        this.pic_uploaded_time = pic_uploaded_time;
    }


}
