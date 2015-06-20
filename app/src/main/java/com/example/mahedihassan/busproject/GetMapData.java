package com.example.mahedihassan.busproject;

/**
 * Created by hemel007 on 6/7/2015.
 */
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Mahedi Hassan on 5/11/2015.
 */
public class GetMapData extends AsyncTask<String, String, JSONArray> {
    UsersMap map;

    // Result data
    JSONArray jsonArray;
    int id = 0;
    String month, year;
    String value;
    HttpClient client;
    Exception exception = null;
    private ProgressDialog pDialog;

    String busNumber;

    public GetMapData(UsersMap map, String busNumber) {
        this.map = map;

        this.busNumber= busNumber;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pDialog.show();
    }

    @Override
    protected JSONArray doInBackground(String... args) {

        try {

            int actionChoice = 1;

            // post a text data
            if (actionChoice == 1) {
                postText();
            }

            HttpGet getrequest = new HttpGet("http://192.168.137.1/get_map_data.php");
            HttpParams httpParameters = new BasicHttpParams();
            // Set the timeout in milliseconds until a connection is
            // established.
            // The default value is zero, that means the timeout is not used.
            int timeoutConnection = 10000;

            HttpConnectionParams.setConnectionTimeout(httpParameters,
                    timeoutConnection);
            // Set the default socket timeout (SO_TIMEOUT)
            // in milliseconds which is the timeout for waiting for data.
            int timeoutSocket = 15000;
            HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

            client = new DefaultHttpClient(httpParameters);

            HttpResponse response = client.execute(getrequest);
            HttpEntity entity = response.getEntity();
            String responseStr = EntityUtils.toString(entity);
            jsonArray = new JSONArray(responseStr);

            if (response.getStatusLine().getStatusCode()==200)
            {
                id=1;
            }
            //Log.v("NO", jsonObject.toString());



            //Toast.makeText(mainActivity,jo.getString("description"),Toast.LENGTH_LONG).show();


            //JSONArray jsonArray = jsonObject.toJSONArray();

            // int a = 10 / 0;
        } catch (ConnectTimeoutException e) {


            Log.v("NO", Log.getStackTraceString(e));

        } catch (Exception e) {


            Log.v("NO", Log.getStackTraceString(e));

        }

        return jsonArray;
    }

    private void postText() {
        try {
            // url where the data will be posted
            String postReceiverUrl = "http://192.168.137.1/postphp.php";
            //Log.v(TAG, "postURL: " + postReceiverUrl);

            // HttpClient
            HttpClient httpClient = new DefaultHttpClient();

            // post header
            HttpPost httpPost = new HttpPost(postReceiverUrl);

            // add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            Calendar c = Calendar.getInstance();
            nameValuePairs.add(new BasicNameValuePair("date",  String.valueOf(c.get(Calendar.DATE)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.YEAR)+"    "+c.get(Calendar.HOUR)+"-"+c.get(Calendar.MINUTE)+"-"+c.get(Calendar.AM_PM))));
            nameValuePairs.add(new BasicNameValuePair("busnumber", busNumber));
            // nameValuePairs.add(new BasicNameValuePair("email", "mike@testmail.com"));

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // execute HTTP post request
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity resEntity = response.getEntity();

            if (resEntity != null) {

                String responseStr = EntityUtils.toString(resEntity).trim();
                //Log.v(TAG, "Response: " + responseStr);

                // you can add an if statement here and do other actions based on the response
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPostExecute(JSONArray valid)
    {

        try {
            pDialog.dismiss();
            pDialog = null;
        } catch (Exception e) {
            // nothing
        }

        if (id == -1 || id == 0) {

            // mainActivity.showError(id);

        } else if (id == 1) {

            map.result(valid);
        }
    }
}
