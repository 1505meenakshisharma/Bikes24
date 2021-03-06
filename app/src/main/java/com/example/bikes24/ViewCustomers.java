package com.example.bikes24;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewCustomers extends AppCompatActivity
{
    SQLiteDatabase db;
    ListView lv;
    final String TAG=this.getClass().getName();
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customers);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        lv=(ListView)findViewById(R.id.list);
        db=openOrCreateDatabase("mydb",MODE_PRIVATE,null);
    }
    public void view(View view)
    {
        String custid="Customer Id : ";
        ArrayList data=new ArrayList();

        Cursor c1=db.rawQuery("select name from sqlite_master where type='table' and name='cust'",null);
        if (c1.getCount() == 0)
            return;

        Cursor c2=db.rawQuery("select * from cust",null);
        while (c2.moveToNext())
        {
            data.add(custid.concat(c2.getString(0).concat("  Customer Name : ").concat(c2.getString(1).concat("  Address : ").concat(c2.getString(2).concat("  Phone : ").concat(c2.getString(3).concat("  Email : ").concat(c2.getString(4).concat("  Date Of Birth : ").concat(c2.getString(5))))))));
        }

        ArrayAdapter ad=new ArrayAdapter(this,android.R.layout.simple_list_item_1,data);
        lv.setAdapter(ad);
    }
    public void back(View view)
    {
        Intent i=new Intent(this,ViewDetails.class);
        startActivity(i);
        finish();
    }
    boolean twice=false;
    @Override
    public void onBackPressed()
    {
        Log.d(TAG,"click");
        if(twice==true)
        {
            Intent i=new Intent(Intent.ACTION_MAIN);
            i.addCategory(Intent.CATEGORY_HOME);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
            System.exit(0);
        }
        twice=true;
        Log.d(TAG,"twice:"+twice);
        //super.onBackPressed();
        Toast.makeText(ViewCustomers.this,"Please press Back again to exit",Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                twice=false;
                Log.d(TAG,"twice:"+twice);
            }
        },3000);
    }
}
