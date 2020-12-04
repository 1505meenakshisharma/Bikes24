package com.example.bikes24;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class customer extends AppCompatActivity
{
    EditText e1,e2,e3,e4,e5,e6;
    SQLiteDatabase db;
    final String TAG=this.getClass().getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        e1=(EditText)findViewById(R.id.editText39);
        e2=(EditText)findViewById(R.id.editText40);
        e3=(EditText)findViewById(R.id.editText41);
        e4=(EditText)findViewById(R.id.editText42);
        e5=(EditText)findViewById(R.id.editText43);
        e6=(EditText)findViewById(R.id.editText44);
        db=openOrCreateDatabase("mydb",MODE_PRIVATE,null);
        db.execSQL("create table if not exists cust(custid varchar,custname varchar,address varchar,phone varchar,email varchar,dob varchar)");
    }
    public void cancel(View view)
    {
        Intent i=new Intent(this,Home.class);
        startActivity(i);
        finish();
    }
    public void edit(View view)
    {
        Intent i=new Intent(this,EditCustomers.class);
        startActivity(i);
        finish();
    }
    public void add(View view)
    {
        String custid=e1.getText().toString();
        String custname=e2.getText().toString();
        String address=e3.getText().toString();
        String phone=e4.getText().toString();
        String email=e5.getText().toString();
        String dob=e6.getText().toString();

        if (custid  == null || custid == "" || custid.length()<3)
        {
            Toast.makeText(this, "Enter valid cutomerID", Toast.LENGTH_LONG).show();
        }
        else if(custname == null || custname == "" || custname.length()<3)
        {
            Toast.makeText(this, "Enter valid customer Name", Toast.LENGTH_LONG).show();
        }
        else if(address == null || address == "" || address.length()<3)
        {
            Toast.makeText(this, "Enter valid address", Toast.LENGTH_LONG).show();
        }
        else if(phone == null || phone == "" || phone.length()<10)
        {
            Toast.makeText(this, "Enter valid phonenumber", Toast.LENGTH_LONG).show();
        }
        else if(email == null || email == "" || email.length()<12)
        {
            Toast.makeText(this, "Enter valid Email", Toast.LENGTH_LONG).show();
        }
        else if(dob == null || dob == "" || dob.length()<9)
        {
            Toast.makeText(this, "Enter valid DateOfBirth", Toast.LENGTH_LONG).show();
        }
        else
        {
            Cursor c = db.rawQuery("select * from cust where custid='" + custid + "'", null);
            c.moveToFirst();
            if (c.getCount() > 0)
            {
                Toast.makeText(this, "CustID already exists", Toast.LENGTH_LONG).show();
            }
            else
            {
                try
                {
                    db.execSQL("insert into cust values('" + custid + "','" + custname + "','" + address + "','" + phone + "','" + email + "','" + dob + "')");
                    Toast.makeText(this, "Customer added", Toast.LENGTH_LONG).show();
                    e1.setText("");
                    e2.setText("");
                    e3.setText("");
                    e4.setText("");
                    e5.setText("");
                    e6.setText("");
                } catch (SQLException e)
                {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                    return;
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
        Toast.makeText(customer.this,"Please press Back again to exit",Toast.LENGTH_LONG).show();
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
