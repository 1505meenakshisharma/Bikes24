package com.example.bikes24;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewSales extends AppCompatActivity
{

    SQLiteDatabase db;
    ListView lv;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sales);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        lv=(ListView)findViewById(R.id.list);
        db=openOrCreateDatabase("mydb",MODE_PRIVATE,null);
    }
    public void view(View view)
    {
        String salesid="Sales Id : ";
        ArrayList data=new ArrayList();

        Cursor c1=db.rawQuery("select name from sqlite_master where type='table' and name='sales'",null);
        if (c1.getCount() == 0)
            return;

        Cursor c2=db.rawQuery("select * from sales",null);
        while (c2.moveToNext())
        {
            data.add(salesid.concat(c2.getString(0).concat("  Customer Id : ").concat(c2.getString(1).concat("  Customer Name : ").concat(c2.getString(2).concat("  Customer Address : ").concat(c2.getString(3).concat("  Customer Phone : ").concat(c2.getString(4).concat("  Model Id : ").concat(c2.getString(5).concat("  Model Name : ").concat(c2.getString(6).concat("  Model Price : ").concat(c2.getString(7).concat("  Sales Date : ").concat(c2.getString(8)))))))))));
        }

        ArrayAdapter ad=new ArrayAdapter(this,android.R.layout.simple_list_item_1,data);
        lv.setAdapter(ad);
    }
    public void back(View view)
    {
        Intent back=new Intent(this,ViewDetails.class);
        startActivity(back);
        finish();
    }
}