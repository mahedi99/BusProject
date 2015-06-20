package com.example.mahedihassan.busproject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mahedihassan.busproject.GetData;
import com.example.mahedihassan.busproject.R;

import org.json.JSONArray;
import org.json.JSONObject;


public class Destination extends Activity implements View.OnClickListener{


    EditText usernameET;
    EditText passwordET;
    EditText busnumberET;
    Button loginBtn;

    String userName;
    String password;
    String busNumber;
    boolean checkPass=false;

    ProgressDialog progressDialog;
    GetData getVersionData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);


        usernameET= (EditText) findViewById(R.id.usernameET);
        passwordET= (EditText) findViewById(R.id.passwordET);
        busnumberET= (EditText) findViewById(R.id.busnumberET);
        loginBtn = (Button) findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(this);
        //button2.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    @Override
    public void onClick(View view) {


        if (view == loginBtn) {

            userName = usernameET.getText().toString();
            password = passwordET.getText().toString();
            busNumber = busnumberET.getText().toString();

            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("checking for new data...");
            progressDialog.setCancelable(false);
            getVersionData = new GetData(Destination.this, progressDialog);
            getVersionData.execute("ssgsg", "fsfdf");
        }
        // Toast.makeText(getApplicationContext(),getVersionData.getValue(),Toast.LENGTH_LONG).show();

    }

    public void result(JSONArray jsonArray) {

        try {
            for (int i = 0 ; i < jsonArray.length(); ++i) {
                JSONObject jo = (JSONObject) jsonArray.get(i);
                Log.v("DES", jo.getString("description"));
                Log.v("NAME", jo.getString("name"));
                if (jo.getString("description").equalsIgnoreCase(userName) && jo.getString("name").equalsIgnoreCase(password))
                {
                    Toast.makeText(getApplicationContext(),"Accepted",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), GoogleMap.class);
                    intent.putExtra("BusNumber",busNumber);
                    intent.putExtra("secondData","Admin");
                    startActivity(intent);
                    checkPass=true;
                }


                //printValue[i] = jo.getString("name");
                //Toast.makeText(getApplicationContext(),jo.getString("description"),Toast.LENGTH_LONG).show();
                //Toast.makeText(getApplicationContext(),jo.getString("description"),Toast.LENGTH_LONG).show();
            }
            if (checkPass!=true)
            {
                Toast.makeText(getApplicationContext(),"Access Denied",Toast.LENGTH_LONG).show();
            }


        } catch (Exception e)
        {
            //Toast.makeText(getApplicationContext(),"Access Denied",Toast.LENGTH_LONG).show();
        }


    }
}
