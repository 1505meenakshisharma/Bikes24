package com.example.bikes24;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AutomobileDetails extends AppCompatActivity
{
    EditText e1, e2, e3, e4, e5, e6, e7, e8;
    SQLiteDatabase db;
    final String TAG=this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automobile_details);
        e1 = (EditText) findViewById(R.id.editText9);
        e2 = (EditText) findViewById(R.id.editText10);
        e3 = (EditText) findViewById(R.id.editText11);
        e4 = (EditText) findViewById(R.id.editText12);
        e5 = (EditText) findViewById(R.id.editText13);
        e6 = (EditText) findViewById(R.id.editText14);
        e7 = (EditText) findViewById(R.id.editText15);
        e8 = (EditText) findViewById(R.id.editText16);
        db = openOrCreateDatabase("mydb", MODE_PRIVATE, null);
        db.execSQL("create table if not exists auto(modelid varchar,model varchar,company varchar,fuel varchar,speed varchar,average varchar,price varchar,quantity varchar)");
    }
    public void cancel(View view)
    {
        Intent i = new Intent(this, Home.class);
        startActivity(i);
        finish();
    }
    public void add(View view)
    {
        String modelid = e1.getText().toString();
        String model = e2.getText().toString();
        String company = e3.getText().toString();
        String fuel = e4.getText().toString();
        String speed = e5.getText().toString();
        String average = e6.getText().toString();
        String price = e7.getText().toString();
        String quantity = e8.getText().toString();

        if (modelid == null || modelid == "" || modelid.length() < 3)
        {
            Toast.makeText(this, "Enter valid ModelID", Toast.LENGTH_LONG).show();
        }
        else if (model == null || model == "" || model.length() < 3)
        {
            Toast.makeText(this, "Enter valid Model Name", Toast.LENGTH_LONG).show();
        }
        else if (company == null || company == "" || company.length() < 3)
        {
            Toast.makeText(this, "Enter valid Company Name", Toast.LENGTH_LONG).show();
        }
        else if (fuel == null || fuel == "" || fuel.length() < 1)
        {
            Toast.makeText(this, "Enter valid Fuel", Toast.LENGTH_LONG).show();
        }
        else if (speed == null || speed == "" || speed.length() < 2)
        {
            Toast.makeText(this, "Enter valid speed", Toast.LENGTH_LONG).show();
        }
        else if (average == null || average == "" || average.length() < 1)
        {
            Toast.makeText(this, "Enter valid Average", Toast.LENGTH_LONG).show();
        }
        else if (price == null || price == "" || price.length() < 4)
        {
            Toast.makeText(this, "Enter valid price", Toast.LENGTH_LONG).show();
        }
        else if (quantity == null || quantity == "" || quantity.length() < 1)
        {
            Toast.makeText(this, "Enter valid quantity", Toast.LENGTH_LONG).show();
        }
        else
        {
            Cursor c = db.rawQuery("select * from auto where modelid='" + modelid + "'", null);
            c.moveToFirst();
            if (c.getCount() > 0)
            {
                Toast.makeText(this, "ModelID already exists", Toast.LENGTH_LONG).show();
            }
            else
            {
                try
                {
                    db.execSQL("insert into auto values('" + modelid + "','" + model + "','" + company + "','" + fuel + "','" + speed + "','" + average + "','" + price + "','" + quantity + "')");
                    Toast.makeText(this, "Automobile added", Toast.LENGTH_LONG).show();
                    e1.setText("");
                    e2.setText("");
                    e3.setText("");
                    e4.setText("");
                    e5.setText("");
                    e6.setText("");
                    e7.setText("");
                    e8.setText("");
                } catch (SQLException e)
                {
                    Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }

        }

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
        Toast.makeText(AutomobileDetails.this,"Please press Back again to exit",Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                twice=false;
                Log.d(TAG,"twice:"+twice);
            }
        },3000);
    }
    public void edit(View view)
    {
        Intent i=new Intent(this,EditAutomobiles.class);
        startActivity(i);
        finish();
    }
}
