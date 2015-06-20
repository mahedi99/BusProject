package com.example.mahedihassan.busproject;

/**
 * Created by hemel007 on 5/12/2015.
 */
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

/**
 * Created by Mahedi Hassan on 5/11/2015.
 */
public class GetData extends AsyncTask<String, String, JSONArray> {
    Destination destination;

    // Result data
    JSONArray jsonArray;
    int id = 0;
    String month, year;
    String value;
    HttpClient client;
    Exception exception = null;
    private ProgressDialog pDialog;

    public GetData(Destination activity, ProgressDialog progressDialog) {
        this.destination = activity;
        this.pDialog = progressDialog;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pDialog.show();
    }

    @Override
    protected JSONArray doInBackground(String... args) {

        try {
            HttpGet getrequest = new HttpGet("http://192.168.137.1/login.php");
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

            destination.result(valid);
        }
    }
}
