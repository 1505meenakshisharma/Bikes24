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

public class EditCustomers extends AppCompatActivity
{
    EditText e1, e2, e3, e4, e5, e6;
    SQLiteDatabase db;
    final String TAG=this.getClass().getName();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_customers);
        e1 = (EditText) findViewById(R.id.editText33);
        e2 = (EditText) findViewById(R.id.editText34);
        e3 = (EditText) findViewById(R.id.editText35);
        e4 = (EditText) findViewById(R.id.editText36);
        e5 = (EditText) findViewById(R.id.editText37);
        e6 = (EditText) findViewById(R.id.editText38);
        db = openOrCreateDatabase("mydb", MODE_PRIVATE, null);
    }
    public void cancel(View view)
    {
        Intent i = new Intent(this,customer.class);
        startActivity(i);
        finish();
    }
    public void search(View view)
    {
        String custid = e1.getText().toString();
        if (custid == null || custid == "" || custid.length() < 2)
        {
            Toast.makeText(this, "Enter valid cutomer ID", Toast.LENGTH_LONG).show();
        }
        else
        {
            Cursor c = db.rawQuery("select * from cust where custid='" + custid + "'", null);
            e2.setText("");
            e3.setText("");
            e4.setText("");
            e5.setText("");
            e6.setText("");

            c.moveToFirst();
            if (c.getCount() > 0)
            {
                do
                {
                    String custname = c.getString(1);
                    String address = c.getString(2);
                    String phone = c.getString(3);
                    String email = c.getString(4);
                    String dob = c.getString(5);
                    e2.setText(custname);
                    e3.setText(address);
                    e4.setText(phone);
                    e5.setText(email);
                    e6.setText(dob);
                } while (c.moveToNext());
            }
            else
            {
                Toast.makeText(this, "customer id Doesn't match", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void delete(View view)
    {
        String custid=e1.getText().toString();
        if (custid == null || custid == "" || custid.length()<2)
        {
            Toast.makeText(this,"Please enter a valid CustID",Toast.LENGTH_LONG).show();
        }
        else
        {
            try
            {
                db.execSQL("delete from cust where custid='"+custid+"'");
                Toast.makeText(this,"Rocords Deleted",Toast.LENGTH_LONG).show();
                e1.setText("");
                e2.setText("");
                e3.setText("");
                e4.setText("");
                e5.setText("");
                e6.setText("");
            }catch (SQLException e)
            {
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
    }
    public void update(View view)
    {
        String custid=e1.getText().toString();
        String custname=e2.getText().toString();
        String address=e3.getText().toString();
        String phone=e4.getText().toString();
        String email=e5.getText().toString();
        String dob=e6.getText().toString();

        if(custid == null || custid == "" || custid.length()<3)
        {
            Toast.makeText(this,"Please enter correct custID",Toast.LENGTH_LONG).show();
        }
        else
        {
            try
            {
                db.execSQL("update cust set custname='"+custname+"',address='"+address+"',phone='"+phone+"',email='"+email+"',dob='"+dob+"' where custid='"+custid+"'");
                Toast.makeText(this,"Records updated",Toast.LENGTH_LONG).show();
                e1.setText("");
                e2.setText("");
                e3.setText("");
                e4.setText("");
                e5.setText("");
                e6.setText("");
            }catch (SQLException e)
            {
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
            }
        }

    }
    public void onBackPressed()
    {
        Intent i=new Intent(this,customer.class);
        startActivity(i);
        finish();
    }
    /*boolean twice=false;
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
        Toast.makeText(EditCustomers.this,"Please press Back again to exit",Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                twice=false;
                Log.d(TAG,"twice:"+twice);
            }
        },3000);
    }*/
}
