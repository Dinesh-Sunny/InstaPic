package com.nawab.instapics.instapic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Macbook on 21/05/15.
 */
public class JsonParser {

    public static List<User> parseFeed(String content) {

        if (content != null) {
            try {
                JSONObject root = new JSONObject(content);

                List<User> user_list = new ArrayList<>();
                JSONArray data = root.getJSONArray("data");
                String userList = "";

                for (int i = 0; i < data.length(); i++) {
                    JSONObject user = data.getJSONObject(i);
                    double timeUploaded = user.getDouble("created_time");

                    JSONObject userObj = user.getJSONObject("user");
                    String userName = userObj.getString("username");
                    String profilePic = userObj.getString("profile_picture");


                    JSONObject image = user.getJSONObject("images");
                    JSONObject pic_res = image.getJSONObject("low_resolution");
                    String uploadedPic = pic_res.getString("url");
                    User listObject = new User();
                    listObject.setUser_name(userName);
                    listObject.setProfile_pic(profilePic);
                    listObject.setUploaded_photo(uploadedPic);
                    listObject.setPic_uploaded_time(timeUploaded);
                    user_list.add(listObject);
                }
                return user_list;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }

        }
        return null;
    }
}