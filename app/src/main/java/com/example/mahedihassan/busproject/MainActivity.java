package com.example.mahedihassan.busproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener{

    GridView gv;
    Context context;
    ArrayList optionName;
    public static String [] optionList={"Admin Login","Current Bus Location","Available Buses","Google Map","Bus Counters","About"};
    public static int [] optionImages={R.drawable.logo1,R.drawable.location,R.drawable.bus,R.drawable.map,R.drawable.input,R.drawable.about};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gv=(GridView)findViewById(R.id.gridView1);
        gv.setAdapter(new CustomAdapter(this, optionList,optionImages));
        gv.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (position==0)
        {
            Intent intent = new Intent(this, Destination.class);
            startActivity(intent);
        }
        if (position==1)
        {
            Intent intent = new Intent(this, CurrentBusLocation.class);
            startActivity(intent);
        }
        if (position==2)
        {
            Intent intent = new Intent(this, AvailableBuses.class);
            startActivity(intent);
        }
        if (position==3)
        {
            Intent intent = new Intent(this, GoogleMap.class);
            startActivity(intent);
        }
        if (position==4)
        {
            Intent intent = new Intent(this, UserInput.class);
            startActivity(intent);
        }
        if (position==5)
        {
            Intent intent = new Intent(this, About.class);
            startActivity(intent);
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


}



class CustomAdapter extends BaseAdapter {

    String [] result;
    Context context;
    int [] imageId;
    private static LayoutInflater inflater=null;


    public CustomAdapter(MainActivity mainActivity, String[] optionList,int[] optionImages) {
        result=optionList;
        context=mainActivity;
        imageId=optionImages;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Holder holder=new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.custom_grid,null);
        holder.tv=(TextView)rowView.findViewById(R.id.textView1);
        holder.img=(ImageView)rowView.findViewById(R.id.imageView1);

        holder.tv.setText(result[position]);
        holder.img.setImageResource(imageId[position]);

        return rowView;
    }




}
