package com.example.bikes24;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SalesDetailForManager extends AppCompatActivity
{

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_detail_for_manager);

        TextView tv8 = (TextView)findViewById(R.id.textView8);
        TextView tv13 = (TextView)findViewById(R.id.textView13);
        TextView tv86 = (TextView)findViewById(R.id.textView86);
        TextView tv87 = (TextView)findViewById(R.id.textView87);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        String selected_model_name = bundle.getString("selectedModel");
        tv8.setText("Sales For " + selected_model_name + " :-");

        db=openOrCreateDatabase("mydb",MODE_PRIVATE,null);

        long total = 0;
        long selected_model_total = 0;
        Cursor c1=db.rawQuery("select * from sales",null);
        while (c1.moveToNext())
        {
            total = total + c1.getInt(7);
        }

        Cursor c2=db.rawQuery("select * from sales where model='" + selected_model_name + "'",null);
        while (c2.moveToNext())
        {
            selected_model_total = selected_model_total + c2.getInt(7);
        }
        float selected_model_percent = (float)selected_model_total * 100 / total;
        String rounded_percent = String.format("%.2f", selected_model_percent);
        tv13.setText("Total sales happened for "+ selected_model_name + " = ₹" + selected_model_total);
        tv86.setText("Total sales happened from all the sold Automobiles till now = ₹" + total);
        tv87.setText("Sales % happened for "+ selected_model_name + " = " + rounded_percent + "%");
    }
    public void back(View view)
    {
        Intent back=new Intent(this,AutomobileListForManager.class);
        startActivity(back);
        finish();
    }
    @Override
    public void onBackPressed()
    {
        Intent i=new Intent(this,AutomobileListForManager.class);
        startActivity(i);
        finish();
    }
}