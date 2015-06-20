package com.example.mahedihassan.busproject;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hemel007 on 5/23/2015.
 */
public class SendData extends AsyncTask<String, String, String> {

    String latitude;
    String longitude;
    SendData(double latitude, double longitude)
    {
        this.latitude = Double.toString(latitude);
        this.longitude= Double.toString(longitude);
    }

    private static final String TAG = "MainActivity.java";

    protected void onPreExecute() {
        super.onPreExecute();
        // do stuff before posting data
    }


    @Override
    protected String doInBackground(String... params) {
        try {

            // 1 = post text data, 2 = post file
            int actionChoice = 1;

            // post a text data
            if (actionChoice == 1) {
                postText();
            }

          /*  // post a file
            else{
                postFile();
            }
            */

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String lenghtOfFile) {
        // do stuff after posting data
    }

    private void postText() {
        try {
            // url where the data will be posted
            String postReceiverUrl = "http://192.168.137.1/postphp.php";
            Log.v(TAG, "postURL: " + postReceiverUrl);

            // HttpClient
            HttpClient httpClient = new DefaultHttpClient();

            // post header
            HttpPost httpPost = new HttpPost(postReceiverUrl);

            // add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("latitude", latitude));
            nameValuePairs.add(new BasicNameValuePair("longitude", longitude));
           // nameValuePairs.add(new BasicNameValuePair("email", "mike@testmail.com"));

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // execute HTTP post request
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity resEntity = response.getEntity();

            if (resEntity != null) {

                String responseStr = EntityUtils.toString(resEntity).trim();
                Log.v(TAG, "Response: " + responseStr);

                // you can add an if statement here and do other actions based on the response
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
