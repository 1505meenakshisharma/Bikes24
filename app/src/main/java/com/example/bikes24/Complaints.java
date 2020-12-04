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

public class Complaints extends AppCompatActivity
{
    EditText e1,e2,e3,e4,e5,e6;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);
        e1=(EditText)findViewById(R.id.editText66);
        e2=(EditText)findViewById(R.id.editText67);
        e3=(EditText)findViewById(R.id.editText68);
        e4=(EditText)findViewById(R.id.editText69);
        e5=(EditText)findViewById(R.id.editText70);
        e6=(EditText)findViewById(R.id.editText71);
        db=openOrCreateDatabase("mydb",MODE_PRIVATE,null);
        db.execSQL("create table if not exists comp(salesid varchar,name varchar,model varchar,salesdate varchar,complaint varchar,compdate varchar)");
    }
    public void cancel(View view)
    {
        Intent i=new Intent(this,Home.class);
        startActivity(i);
        finish();
    }
    public void edit(View view)
    {
        Intent i=new Intent(this,EditComplaints.class);
        startActivity(i);
        finish();
    }
    public void search(View view)
    {
        String salesid = e1.getText().toString();
        if (salesid == null || salesid == "" || salesid.length() < 2)
        {
            Toast.makeText(this, "Enter valid Sales ID", Toast.LENGTH_LONG).show();
        }
        else
        {
            Cursor c = db.rawQuery("select * from sales where salesid='" + salesid + "'", null);
            e2.setText("");
            e3.setText("");
            e4.setText("");

            c.moveToFirst();
            if (c.getCount() > 0)
            {
                do
                {
                    String name = c.getString(2);
                    String model = c.getString(6);
                    String salesdate = c.getString(8);
                    e2.setText(name);
                    e3.setText(model);
                    e4.setText(salesdate);
                } while (c.moveToNext());
            }
            else
            {
                Toast.makeText(this, "Sales id Doesn't match", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void add(View view)
    {
        String salesid=e1.getText().toString();
        String name=e2.getText().toString();
        String model=e3.getText().toString();
        String salesdate=e4.getText().toString();
        String complaint=e5.getText().toString();
        String compdate=e6.getText().toString();

        if (salesid  == null || salesid == "" || salesid.length()<2)
        {
            Toast.makeText(this, "Enter valid SalesID", Toast.LENGTH_LONG).show();
        }
        else if(name == null || name == "" || name.length()<3)
        {
            Toast.makeText(this, "Enter valid Customer Name", Toast.LENGTH_LONG).show();
        }
        else if(model == null || model == "" || model.length()<3)
        {
            Toast.makeText(this, "Enter model name", Toast.LENGTH_LONG).show();
        }
        else if(salesdate == null || salesdate == "" || salesdate.length()<9)
        {
            Toast.makeText(this, "Enter valid Date", Toast.LENGTH_LONG).show();
        }
        else if(complaint == null || complaint == "" || complaint.length()<2)
        {
            Toast.makeText(this, "Enter proper complaint", Toast.LENGTH_LONG).show();
        }
        else if(compdate == null || compdate == "" || compdate.length()<9)
        {
            Toast.makeText(this, "Enter valid date", Toast.LENGTH_LONG).show();
        }
        else
        {
            Cursor c = db.rawQuery("select * from comp where salesid='" + salesid + "'", null);
            c.moveToFirst();
            if (c.getCount() > 0)
            {
                Toast.makeText(this, "SalesID already exists", Toast.LENGTH_LONG).show();
            }
            else
            {
                try
                {
                    db.execSQL("insert into comp values('" + salesid + "','" + name + "','" + model + "','" + salesdate + "','" + complaint + "','" + compdate + "')");
                    Toast.makeText(this, "Complaint saved", Toast.LENGTH_LONG).show();
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
}
