package com.example.bikes24;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Billing extends AppCompatActivity
{
    EditText e1,e2,e3,e4,e5,e6,e7,e8,e9;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);
        e1 = (EditText) findViewById(R.id.editText);
        e2 = (EditText) findViewById(R.id.editText2);
        e3 = (EditText) findViewById(R.id.editText90);
        e4 = (EditText) findViewById(R.id.editText95);
        e5 = (EditText) findViewById(R.id.editText91);
        e6 = (EditText) findViewById(R.id.editText92);
        e7 = (EditText) findViewById(R.id.editText97);
        e8 = (EditText) findViewById(R.id.editText88);
        e9 = (EditText) findViewById(R.id.editText89);
        db=openOrCreateDatabase("mydb",MODE_PRIVATE,null);
        db.execSQL("create table if not exists bill(salesid varchar,custname varchar,address varchar,phone varchar,model varchar,price varchar,salesdate varchar,taxes varchar,total varchar)");
    }
    public void search(View view)
    {
        String salesid = e1.getText().toString();
        if (salesid == null || salesid== "" || salesid.length() < 2)
        {
            Toast.makeText(this, "Enter valid SalesID", Toast.LENGTH_LONG).show();
        }
        else
        {
            try {
                Cursor c = db.rawQuery("select * from sales where salesid='" + salesid + "'", null);
                e2.setText("");
                e3.setText("");
                e4.setText("");
                e5.setText("");
                e6.setText("");
                e7.setText("");
                c.moveToFirst();
                if (c.getCount() > 0) {
                    do {
                        String name = c.getString(2);
                        String address = c.getString(3);
                        String phone = c.getString(4);
                        String model = c.getString(6);
                        String price = c.getString(7);
                        String salesdate = c.getString(8);
                        e2.setText(name);
                        e3.setText(address);
                        e4.setText(phone);
                        e5.setText(model);
                        e6.setText(price);
                        e7.setText(salesdate);

                    } while (c.moveToNext());
                } else {
                    Toast.makeText(this, " Saleslid Doesn't match", Toast.LENGTH_LONG).show();
                }
            }
            catch (SQLException e)
            {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
    }
    public void add(View view)
    {
        String salesid = e1.getText().toString();
        String custname = e2.getText().toString();
        String address = e3.getText().toString();
        String phone = e4.getText().toString();
        String model = e5.getText().toString();
        String price = e6.getText().toString();
        String salesdate = e7.getText().toString();
        String taxes = e8.getText().toString();
        String total = e9.getText().toString();

        if (salesid == null || salesid == "" || salesid.length() < 2)
        {
            Toast.makeText(this, "Enter valid SalesID", Toast.LENGTH_LONG).show();
        }
        else if (custname == null || custname == "" || custname.length() < 2)
        {
            Toast.makeText(this, "Enter valid CustName", Toast.LENGTH_LONG).show();
        }
        else if (address == null || address == "" || address.length() < 2)
        {
            Toast.makeText(this, "Enter valid Address Name", Toast.LENGTH_LONG).show();
        }
        else if (phone == null || phone == "" || phone.length() < 10)
        {
            Toast.makeText(this, "Enter valid Phonenumber", Toast.LENGTH_LONG).show();
        }
        else if (model == null || model == "" || model.length() < 2)
        {
            Toast.makeText(this, "Enter valid Model", Toast.LENGTH_LONG).show();
        }
        else if (price == null || price == "" || price.length() < 5)
        {
            Toast.makeText(this, "Enter valid price", Toast.LENGTH_LONG).show();
        }
        else if (salesdate == null || salesdate == "" || salesdate.length() < 9)
        {
            Toast.makeText(this, "Enter valid salesdate", Toast.LENGTH_LONG).show();
        }
        else if (taxes == null || taxes == "" || taxes.length() < 3)
        {
            Toast.makeText(this, "Enter valid Taxes", Toast.LENGTH_LONG).show();
        }
        else if (total == null || total == "" || total.length() < 5)
        {
            Toast.makeText(this, "Enter valid Total", Toast.LENGTH_LONG).show();
        }
        else
        {
            Cursor c = db.rawQuery("select * from bill where salesid='" + salesid + "'", null);
            c.moveToFirst();
            if (c.getCount() > 0)
            {
                Toast.makeText(this, "SalesID already exists", Toast.LENGTH_LONG).show();
            }
            else
            {
                try
                {
                    db.execSQL("insert into bill values('" + salesid + "','" + custname + "','" + address + "','" + phone + "','" + model + "','" + price + "','" + salesdate + "','" + taxes + "','"+total+"','"+taxes+"')");
                    Toast.makeText(this, " Enter SalesID to Generate Bill", Toast.LENGTH_LONG).show();
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
                    Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }

        }

    }
    public void cancel(View view)
    {
        Intent i=new Intent(this,Home.class);
        startActivity(i);
        finish();
    }
    public void sum(View view)
    {
        Float firstnumber=Float.valueOf(e6.getText().toString());
        Float secondnumber=Float.valueOf(e8.getText().toString());

        Float sum=firstnumber+secondnumber;
        e9.setText(sum.toString());

    }
    public void print(View view)
    {
        Intent i=new Intent(this,DisplayBill.class);
        startActivity(i);
        finish();
    }
}
