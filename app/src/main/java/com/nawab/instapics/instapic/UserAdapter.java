package com.nawab.instapics.instapic;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
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

        if(user.getBitmap() != null){
            ImageView image = (ImageView)view.findViewById(R.id.imageView);
            image.setImageBitmap(user.getBitmap());
        }else{
            UserandView container = new UserandView();
            container.user = user;
            container.view = view;

            ImageLoader loader = new ImageLoader();
            loader.execute(container);
        }



        return view;
    }

    class UserandView{
        public User user;
        public View view;
        public Bitmap bitmap;
    }

    private class ImageLoader extends AsyncTask<UserandView,Void,UserandView>{

        @Override
        protected UserandView doInBackground(UserandView... params) {
            UserandView container = params[0];
            User user = container.user;

            try {
                String url = user.getUploaded_photo();
                InputStream in = (InputStream)new URL(url).getContent();
                Bitmap bitmap = BitmapFactory.decodeStream(in);
                user.setBitmap(bitmap);
                in.close();
                container.bitmap = bitmap;
                return  container;
            }catch (Exception e){
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(UserandView userandView) {
            ImageView image = (ImageView)userandView.view.findViewById(R.id.imageView);
            image.setImageBitmap(userandView.bitmap);
            userandView.user.setBitmap(userandView.bitmap);
        }
    }
}
