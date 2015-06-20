package com.example.mahedihassan.busproject;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class AvailableBuses extends ActionBarActivity {

    HashMap<String, List<String>> citiesList;
    List<String> busesList;
    ExpandableListView expandableLV;
    BusesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_buses);
        expandableLV = (ExpandableListView) findViewById(R.id.exp_list);
        citiesList = DataProvider.getInfo();
        busesList = new ArrayList<String>(citiesList.keySet());
        adapter = new BusesAdapter(AvailableBuses.this, citiesList, busesList);
        expandableLV.setAdapter(adapter);

        expandableLV.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent intent = new Intent();
                intent.setClass(getBaseContext(), UserInput.class);
                //intent.putExtra("EXTRA_ID", "SOME DATAS");

                if (groupPosition==0 && childPosition==0){

                    intent.putExtra("LATI",25.74389);
                    intent.putExtra("LONGI",89.27523);
                }
                else if (groupPosition==1 && childPosition==0){

                    intent.putExtra("LATI",22.70000);
                    intent.putExtra("LONGI",90.36667);
                }
                else if (groupPosition==2 && childPosition==0){

                    intent.putExtra("LATI",22.81667);
                    intent.putExtra("LONGI",89.55000);
                }
                else if (groupPosition==3 && childPosition==0){

                    intent.putExtra("LATI",23.80625);
                    intent.putExtra("LONGI",90.41269);
                }
                else if (groupPosition==3 && childPosition==1){
                    intent.putExtra("LATI",23.80625);
                    intent.putExtra("LONGI",90.41269);
                }
                else if (groupPosition==3 && childPosition==2){
                    intent.putExtra("LATI",23.80625);
                    intent.putExtra("LONGI",90.41269);
                }
                else if (groupPosition==3 && childPosition==3){

                    intent.putExtra("LATI",23.81033);
                    intent.putExtra("LONGI",90.41269);
                }
                else if (groupPosition==3 && childPosition==4){

                    intent.putExtra("LATI",23.81033);
                    intent.putExtra("LONGI",90.41252);
                }
                else if (groupPosition==3 && childPosition==5){

                    intent.putExtra("LATI",23.81033);
                    intent.putExtra("LONGI",90.41252);
                }
                else if (groupPosition==3 && childPosition==6){

                    intent.putExtra("LATI",23.81033);
                    intent.putExtra("LONGI",90.41252);
                }
                else if (groupPosition==3 && childPosition==7){

                    intent.putExtra("LATI",23.81033);
                    intent.putExtra("LONGI",90.41252);
                }
                else if (groupPosition==4 && childPosition==0){

                    intent.putExtra("LATI",24.89778);
                    intent.putExtra("LONGI",91.87139);
                }
                else if (groupPosition==5 && childPosition==0){

                    intent.putExtra("LATI",24.36667);
                    intent.putExtra("LONGI",88.60000);
                }
                else if (groupPosition==6 && childPosition==0){
                    intent.putExtra("LATI",22.36667);
                    intent.putExtra("LONGI",91.80000);

                }



                getChildPosition();
                startActivity(intent);

                return false;
            }
        });

    }

    public void getChildPosition()
    {
        //if ()
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_available_buses, menu);
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
}
