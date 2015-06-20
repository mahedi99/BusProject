package com.example.mahedihassan.busproject;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class CurrentBusLocation extends ActionBarActivity implements View.OnClickListener{

    EditText busNumberET;
    Button button;

    String busNumberOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_bus_location);


        busNumberET= (EditText) findViewById(R.id.busnumberET);
        button= (Button) findViewById(R.id.button);

        button.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_current_bus_location, menu);
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

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.button)
        {
            busNumberOutput= busNumberET.getText().toString();
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(), UsersMap.class);
            intent.putExtra("BusNumber",busNumberOutput);
            intent.putExtra("secondData","User");
            startActivity(intent);
        }
    }
}
