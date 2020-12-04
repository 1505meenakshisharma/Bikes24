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

public class EditAutomobiles extends AppCompatActivity
{
    EditText e1, e2, e3, e4, e5, e6,e7,e8;
    SQLiteDatabase db;
    final String TAG=this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_automobiles);
        e1 = (EditText) findViewById(R.id.editText17);
        e2 = (EditText) findViewById(R.id.editText18);
        e3 = (EditText) findViewById(R.id.editText19);
        e4 = (EditText) findViewById(R.id.editText20);
        e5 = (EditText) findViewById(R.id.editText21);
        e6 = (EditText) findViewById(R.id.editText22);
        e7 = (EditText) findViewById(R.id.editText23);
        e8 = (EditText) findViewById(R.id.editText24);
        db = openOrCreateDatabase("mydb", MODE_PRIVATE, null);

    }
    public void cancel(View view)
    {
        Intent i=new Intent(this,AutomobileDetails.class);
        startActivity(i);
        finish();
    }
    public void search(View view)
    {
        String modelid = e1.getText().toString();
        if (modelid == null || modelid == "" || modelid.length() < 2)
        {
            Toast.makeText(this, "Enter valid ModelID", Toast.LENGTH_LONG).show();
        }
        else
        {
            Cursor c = db.rawQuery("select * from auto where modelid='" + modelid + "'", null);
            e2.setText("");
            e3.setText("");
            e4.setText("");
            e5.setText("");
            e6.setText("");
            e7.setText("");
            e8.setText("");

            c.moveToFirst();
            if (c.getCount() > 0)
            {
                do
                {
                    String model = c.getString(1);
                    String company = c.getString(2);
                    String fuel = c.getString(3);
                    String speed = c.getString(4);
                    String average = c.getString(5);
                    String price = c.getString(6);
                    String quantity= c.getString(7);
                    e2.setText(model);
                    e3.setText(company);
                    e4.setText(fuel);
                    e5.setText(speed);
                    e6.setText(average);
                    e7.setText(price);
                    e8.setText(quantity);

                } while (c.moveToNext());
            }
            else
            {
                Toast.makeText(this, " Modelid Doesn't match", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void delete(View view)
    {
        String modelid=e1.getText().toString();
        if (modelid == null || modelid == "" || modelid.length()<2)
        {
            Toast.makeText(this,"Please enter a valid ModelID",Toast.LENGTH_LONG).show();
        }
        else
        {
            try
            {
                db.execSQL("delete from auto where modelid='"+modelid+"'");
                Toast.makeText(this,"Rocords Deleted",Toast.LENGTH_LONG).show();
                e1.setText("");
                e2.setText("");
                e3.setText("");
                e4.setText("");
                e5.setText("");
                e6.setText("");
                e7.setText("");
                e8.setText("");
            }catch (SQLException e)
            {
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
    }
    public void update(View view)
    {
        String modelid=e1.getText().toString();
        String model=e2.getText().toString();
        String company=e3.getText().toString();
        String fuel=e4.getText().toString();
        String speed=e5.getText().toString();
        String average=e6.getText().toString();
        String price=e7.getText().toString();
        String quantity=e8.getText().toString();

        if(modelid == null || modelid == "" || modelid.length()<3)
        {
            Toast.makeText(this,"Please enter correct ModelID",Toast.LENGTH_LONG).show();
        }
        else
        {
            try
            {
                db.execSQL("update auto set model='"+model+"',company='"+company+"',fuel='"+fuel+"',speed='"+speed+"',average='"+average+"',price='"+price+"',quantity='"+quantity+"' where modelid='"+modelid+"'");
                Toast.makeText(this,"Records updated",Toast.LENGTH_LONG).show();
                e1.setText("");
                e2.setText("");
                e3.setText("");
                e4.setText("");
                e5.setText("");
                e6.setText("");
                e7.setText("");
                e8.setText("");
            }catch (SQLException e)
            {
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
            }
        }

    }
    public void onBackPressed()
    {
        Intent i=new Intent(this,AutomobileDetails.class);
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
        Toast.makeText(EditAutomobiles.this,"Please press Back again to exit",Toast.LENGTH_LONG).show();
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
