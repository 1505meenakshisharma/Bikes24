package com.example.bikes24;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.os.Handler;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SalesDetails extends AppCompatActivity
{
    EditText e1,e2,e3,e4,e5,e6,e7,e8,e9;
    SQLiteDatabase db;
    final String TAG=this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_details);
        e1=(EditText)findViewById(R.id.editText55);
        e2=(EditText)findViewById(R.id.editText56);
        e3=(EditText)findViewById(R.id.editText57);
        e4=(EditText)findViewById(R.id.editText58);
        e5=(EditText)findViewById(R.id.editText59);
        e6=(EditText)findViewById(R.id.editText60);
        e7=(EditText)findViewById(R.id.editText61);
        e8=(EditText)findViewById(R.id.editText62);
        e9=(EditText)findViewById(R.id.editText63);
        db=openOrCreateDatabase("mydb",MODE_PRIVATE,null);
        db.execSQL("create table if not exists sales(salesid varchar,custid varchar,name varchar,address varchar,phone varchar,modelid varchar,model varchar,price varchar,salesdate varchar)");
    }
    public void cancel(View view)
    {
        Intent i=new Intent(this,Home.class);
        startActivity(i);
        finish();
    }
    public void edit(View view)
    {
        Intent i=new Intent(this,EditSales.class);
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
        Toast.makeText(SalesDetails.this,"Please press Back again to exit",Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                twice=false;
                Log.d(TAG,"twice:"+twice);
            }
        },3000);
    }
    public void search(View view)
    {
        String custid = e2.getText().toString();
        if (custid == null || custid == "" || custid.length() < 2)
        {
            Toast.makeText(this, "Enter valid cutomer ID", Toast.LENGTH_LONG).show();
        }
        else
        {
            Cursor c = db.rawQuery("select * from cust where custid='" + custid + "'", null);
            e3.setText("");
            e4.setText("");
            e5.setText("");

            c.moveToFirst();
            if (c.getCount() > 0)
            {
                do
                {
                    String name = c.getString(1);
                    String address = c.getString(2);
                    String phone = c.getString(3);
                    e3.setText(name);
                    e4.setText(address);
                    e5.setText(phone);
                } while (c.moveToNext());
            }
            else
            {
                Toast.makeText(this, "customer id Doesn't match", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void searchh(View view)
    {
        String modelid = e6.getText().toString();
        if (modelid == null || modelid == "" || modelid.length() < 2)
        {
            Toast.makeText(this, "Enter valid ModelID", Toast.LENGTH_LONG).show();
        }
        else
        {
            Cursor c = db.rawQuery("select * from auto where modelid='" + modelid + "'", null);
            e7.setText("");
            e8.setText("");

            c.moveToFirst();
            if (c.getCount() > 0)
            {
                do
                {
                    String model = c.getString(1);
                    String price = c.getString(6);
                    e7.setText(model);
                    e8.setText(price);

                } while (c.moveToNext());
            }
            else
            {
                Toast.makeText(this, " Modelid Doesn't match", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void add(View view)
    {
        String salesid=e1.getText().toString();
        String custid=e2.getText().toString();
        String name=e3.getText().toString();
        String address=e4.getText().toString();
        String phone=e5.getText().toString();
        String modelid=e6.getText().toString();
        String model=e7.getText().toString();
        String price=e8.getText().toString();
        String salesdate=e9.getText().toString();

        if (salesid  == null || salesid == "" || salesid.length()<3)
        {
            Toast.makeText(this, "Enter valid SalesID", Toast.LENGTH_LONG).show();
        }
        else if (custid == null || custid == "" || custid.length()<3)
        {
            Toast.makeText(this, "Enter valid Custid", Toast.LENGTH_LONG).show();
        }
        else if (name == null || name == "" || name.length()<3)
        {
            Toast.makeText(this, "Enter valid name", Toast.LENGTH_LONG).show();
        }
        else if (address == null || address == "" || address.length()<3)
        {
            Toast.makeText(this, "Enter valid address", Toast.LENGTH_LONG).show();
        }
        else if (phone == null || phone == "" || phone.length()<9)
        {
            Toast.makeText(this, "Enter valid phone", Toast.LENGTH_LONG).show();
        }
        else if (modelid == null || modelid == "" || modelid.length()<3)
        {
            Toast.makeText(this, "Enter valid ModelID", Toast.LENGTH_LONG).show();
        }
        else if (model == null || model == "" || model.length()<3)
        {
            Toast.makeText(this, "Enter valid Model", Toast.LENGTH_LONG).show();
        }
        else if (price == null || price == "" || price.length()<5)
        {
            Toast.makeText(this, "Enter valid price", Toast.LENGTH_LONG).show();
        }
        else if (salesdate == null || salesdate == "" || salesdate.length()<9)
        {
            Toast.makeText(this, "Enter valid SalesDate", Toast.LENGTH_LONG).show();
        }
        else
        {
            Cursor c = db.rawQuery("select * from sales where salesid='" + salesid + "'", null);
            c.moveToFirst();
            if (c.getCount() > 0)
            {
                Toast.makeText(this, "SalesID already exists", Toast.LENGTH_LONG).show();
            }
            else
            {
                try
                {
                    db.execSQL("insert into sales values('" + salesid + "','" + custid +"','"+ name +"','"+ address +"','"+ phone +"','"+ modelid +"','"+ model +"','"+ price +"','"+ salesdate +"')");
                    Toast.makeText(this, "sales details added", Toast.LENGTH_LONG).show();
                    e1.setText("");
                    e2.setText("");
                    e3.setText("");
                    e4.setText("");
                    e5.setText("");
                    e6.setText("");
                    e7.setText("");
                    e8.setText("");
                    e9.setText("");
                } catch (SQLException e)
                {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
    }

}
