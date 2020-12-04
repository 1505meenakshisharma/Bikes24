package com.example.bikes24;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AutomobileListForManager extends AppCompatActivity
{

    SQLiteDatabase db;
    ListView lv;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automobile_list_for_manager);

        lv=(ListView)findViewById(R.id.listview);
        ArrayList data=new ArrayList();

        db=openOrCreateDatabase("mydb",MODE_PRIVATE,null);

        Cursor c1=db.rawQuery("select name from sqlite_master where type='table' and name='sales'",null);
        if (c1.getCount() == 0)
            return;

        Cursor c2=db.rawQuery("select * from sales group by model",null);
        while (c2.moveToNext())
        {
            data.add(c2.getString(6));
        }

        ArrayAdapter ad=new ArrayAdapter(this,android.R.layout.simple_list_item_1,data);
        lv.setAdapter(ad);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = String.valueOf(parent.getItemAtPosition(position));
                Intent i = new Intent(AutomobileListForManager.this, SalesDetailForManager.class);
                i.putExtra("selectedModel", selected);
                startActivity(i);
            }
        });
    }
    public void back(View view)
    {
        Intent back=new Intent(this,SalesDetailBannerForManager.class);
        startActivity(back);
        finish();
    }
    @Override
    public void onBackPressed()
    {
        Intent i=new Intent(this,SalesDetailBannerForManager.class);
        startActivity(i);
        finish();
    }
}