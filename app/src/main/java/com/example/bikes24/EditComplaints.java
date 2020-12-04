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

public class EditComplaints extends AppCompatActivity
{
    EditText e1, e2, e3, e4, e5, e6;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_complaints);
        e1 = (EditText) findViewById(R.id.editText76);
        e2 = (EditText) findViewById(R.id.editText80);
        e3 = (EditText) findViewById(R.id.editText81);
        e4 = (EditText) findViewById(R.id.editText82);
        e5 = (EditText) findViewById(R.id.editText83);
        e6 = (EditText) findViewById(R.id.editText84);
        db = openOrCreateDatabase("mydb", MODE_PRIVATE, null);
    }
    public void cancel(View view)
    {
        Intent i=new Intent(this,Complaints.class);
        startActivity(i);
        finish();
    }
    public void onBackPressed()
    {
        Intent i=new Intent(this,Complaints.class);
        startActivity(i);
        finish();
    }
    public void search(View view)
    {
        String salesid = e1.getText().toString();
        if (salesid == null || salesid == "" || salesid.length() < 2)
        {
            Toast.makeText(this, "Enter valid SalesID", Toast.LENGTH_LONG).show();
        }
        else
        {
            Cursor c = db.rawQuery("select * from comp where salesid='" + salesid + "'", null);
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
                    String name = c.getString(1);
                    String model = c.getString(2);
                    String salesdate = c.getString(3);
                    String complaint = c.getString(4);
                    String compdate = c.getString(5);
                    e2.setText(name);
                    e3.setText(model);
                    e4.setText(salesdate);
                    e5.setText(complaint);
                    e6.setText(compdate);
                } while (c.moveToNext());
            }
            else
            {
                Toast.makeText(this, "Sales id Doesn't match", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void delete(View view)
    {
        String salesid=e1.getText().toString();
        if (salesid == null || salesid == "" || salesid.length()<2)
        {
            Toast.makeText(this,"Please enter a valid SalesID",Toast.LENGTH_LONG).show();
        }
        else
        {
            try
            {
                db.execSQL("delete from comp where salesid='"+salesid+"'");
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
        String salesid=e1.getText().toString();
        String name=e2.getText().toString();
        String model=e3.getText().toString();
        String salesdate=e4.getText().toString();
        String comlaint=e5.getText().toString();
        String compdate=e6.getText().toString();

        if(salesid == null || salesid == "" || salesid.length()<2)
        {
            Toast.makeText(this,"Please enter correct SalesID",Toast.LENGTH_LONG).show();
        }
        else
        {
            try
            {
                db.execSQL("update comp set name='"+name+"',model='"+model+"',salesdate='"+salesdate+"',complaint='"+comlaint+"',compdate='"+compdate+"' where salesid='"+salesid+"'");
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
    
}

