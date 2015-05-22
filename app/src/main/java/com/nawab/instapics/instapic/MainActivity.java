package com.nawab.instapics.instapic;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ListActivity {

    EditText editTextInfo;
    String info;
    TextView txtview, txtview2;


    public static final String PostManName = "com.nawab.instapics.instapic";

    List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        userList = new ArrayList<>();


    }

    public void sendMessage(View v) {

        editTextInfo = (EditText) findViewById(R.id.ediTname);
        info = editTextInfo.getText().toString();

        if (checkInternet() == true) {
            MyTask bg_tasks = new MyTask();
            bg_tasks.execute("https://maps.googleapis.com/maps/api/geocode/json?address=" + info);
        } else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean checkInternet() {

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo result = cm.getActiveNetworkInfo();
        if (result != null && result.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    private class MyTask extends AsyncTask<String, Integer, List<User>> {


        @Override
        protected void onPreExecute() {
           txtview = (TextView) findViewById(R.id.textView);
          txtview.setText("Execution started");
        }

        @Override


        protected List<User> doInBackground(String... params) {
            String content = Connection.getData(params[0]);
            if (content != null) {
                try {
                    JSONObject jsonObj = new JSONObject(content);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("results");
                    JSONObject c = contacts.getJSONObject(0);
                    JSONObject geoObj = c.getJSONObject("geometry");
                    JSONObject locObj = geoObj.getJSONObject("location");
                    double latitude = locObj.getDouble("lat");
                    double longitude = locObj.getDouble("lng");

                    content = latitude + " " + longitude;
                    String instaContent = "";

                    instaContent = Connection.getData("https://api.instagram.com/v1/media/search?lat=" + latitude + "&lng=" + longitude + "&distance=2000&client_id=58e8fb0df1954de69238ce3057013f10");
                    userList = JsonParser.parseFeed(instaContent);


                    return userList;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                }
            }else{return null;}

        }





    @Override
    protected void onPostExecute(List<User> userList) {


        UserAdapter adapter = new UserAdapter(MainActivity.this, R.layout.list_item, userList);
        setListAdapter(adapter);


    }
}
}
