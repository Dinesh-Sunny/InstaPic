package com.nawab.instapics.instapic;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Macbook on 22/05/15.
 */
public class UserAdapter extends ArrayAdapter<User> {

        private Context context;
        private List<User> userViewObj;
        public UserAdapter(Context context,int resource,List<User> userViewObj){
            super(context,resource,userViewObj);
            this.context = context;
            this.userViewObj = userViewObj;
        }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item, parent, false);

        User user = userViewObj.get(position);
        TextView tv = (TextView)view.findViewById(R.id.simple_list_item);
        tv.setText(user.getUser_name());

        ImageView image = (ImageView)view.findViewById(R.id.imageView);
        image.setImageBitmap(user.getBitmap());

        return view;
    }
}
