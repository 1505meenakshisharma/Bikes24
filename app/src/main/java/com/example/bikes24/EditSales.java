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

public class EditSales extends AppCompatActivity
{
    EditText e1,e2,e3,e4,e5,e6,e7,e8,e9;
    SQLiteDatabase db;
    final String TAG=this.getClass().getName();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sales);
        e1 = (EditText) findViewById(R.id.editText64);
        e2 = (EditText) findViewById(R.id.editText65);
        e3 = (EditText) findViewById(R.id.editText72);
        e4 = (EditText) findViewById(R.id.editText73);
        e5 = (EditText) findViewById(R.id.editText74);
        e6 = (EditText) findViewById(R.id.editText75);
        e7 = (EditText) findViewById(R.id.editText77);
        e8 = (EditText) findViewById(R.id.editText78);
        e9 = (EditText) findViewById(R.id.editText79);
        db = openOrCreateDatabase("mydb", MODE_PRIVATE, null);
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
        Toast.makeText(EditSales.this,"Please press Back again to exit",Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                twice=false;
                Log.d(TAG,"twice:"+twice);
            }
        },3000);
    }*/
    public void cancel(View view)
    {
        Intent i=new Intent(this,SalesDetails.class);
        startActivity(i);
        finish();
    }
    public void onBackPressed()
    {
        Intent i=new Intent(this,SalesDetails.class);
        startActivity(i);
        finish();
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
                e8.setText("");
                e9.setText("");
                c.moveToFirst();
                if (c.getCount() > 0) {
                    do {
                        String custid = c.getString(1);
                        String name = c.getString(2);
                        String address = c.getString(3);
                        String phone = c.getString(4);
                        String modelid = c.getString(5);
                        String model = c.getString(6);
                        String price = c.getString(7);
                        String salesdate = c.getString(8);
                        e2.setText(custid);
                        e3.setText(name);
                        e4.setText(address);
                        e5.setText(phone);
                        e6.setText(modelid);
                        e7.setText(model);
                        e8.setText(price);
                        e9.setText(salesdate);

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
    public void delete(View view)
    {
        String salesid=e1.getText().toString();
        if (salesid == null || salesid == "" || salesid.length()<2)
        {
            Toast.makeText(this,"Please enter a valid CustID",Toast.LENGTH_LONG).show();
        }
        else
        {
            try
            {
                db.execSQL("delete from sales where salesid='"+salesid+"'");
                Toast.makeText(this,"Rocords Deleted",Toast.LENGTH_LONG).show();
                e1.setText("");
                e2.setText("");
                e3.setText("");
                e4.setText("");
                e5.setText("");
                e6.setText("");
                e7.setText("");
                e8.setText("");
                e9.setText("");
            }catch (SQLException e)
            {
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
    }
    public void update(View view)
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

        if(salesid == null || salesid == "" || salesid.length()<2)
        {
            Toast.makeText(this,"Please enter correct SalesID",Toast.LENGTH_LONG).show();
        }
        else
        {
            try
            {
                db.execSQL("update sales set custid='"+custid+"',name='"+name+"',address='"+address+"',phone='"+phone+"',modelid='"+modelid+"',model='"+model+"',price='"+price+"',salesdate='"+salesdate+"' where salesid='"+salesid+"'");
                Toast.makeText(this,"Records updated",Toast.LENGTH_LONG).show();
                e1.setText("");
                e2.setText("");
                e3.setText("");
                e4.setText("");
                e5.setText("");
                e6.setText("");
                e7.setText("");
                e8.setText("");
                e9.setText("");
            }catch (SQLException e)
            {
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
            }
        }

    }
}
