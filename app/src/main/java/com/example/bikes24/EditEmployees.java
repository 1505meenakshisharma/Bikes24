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

public class EditEmployees extends AppCompatActivity
{
    EditText e1, e2, e3, e4, e5, e6,e7,e8;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_employees);
        e1 = (EditText) findViewById(R.id.editText47);
        e2 = (EditText) findViewById(R.id.editText48);
        e3 = (EditText) findViewById(R.id.editText49);
        e4 = (EditText) findViewById(R.id.editText50);
        e5 = (EditText) findViewById(R.id.editText51);
        e6 = (EditText) findViewById(R.id.editText52);
        e7 = (EditText) findViewById(R.id.editText53);
        e8 = (EditText) findViewById(R.id.editText54);
        db = openOrCreateDatabase("mydb", MODE_PRIVATE, null);
    }
    public void search(View view)
    {
        String empid = e1.getText().toString();
        if (empid == null || empid == "" || empid.length() < 2)
        {
            Toast.makeText(this, "Enter valid EmpID", Toast.LENGTH_LONG).show();
        }
        else
        {
            Cursor c = db.rawQuery("select * from emp where empid='" + empid + "'", null);
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
                    String name = c.getString(1);
                    String address = c.getString(2);
                    String phone = c.getString(3);
                    String dob = c.getString(4);
                    String age = c.getString(5);
                    String doj = c.getString(6);
                    String position= c.getString(7);
                    e2.setText(name);
                    e3.setText(address);
                    e4.setText(phone);
                    e5.setText(dob);
                    e6.setText(age);
                    e7.setText(doj);
                    e8.setText(position);

                } while (c.moveToNext());
            }
            else
            {
                Toast.makeText(this, " EmployeeId Doesn't match", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void delete(View view)
    {
        String empid=e1.getText().toString();
        if (empid == null || empid == "" || empid.length()<2)
        {
            Toast.makeText(this,"Please enter a valid EmployeeID",Toast.LENGTH_LONG).show();
        }
        else
        {
            try
            {
                db.execSQL("delete from emp where empid='"+empid+"'");
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
        String empid=e1.getText().toString();
        String empname=e2.getText().toString();
        String address=e3.getText().toString();
        String phone=e4.getText().toString();
        String dob=e5.getText().toString();
        String age=e6.getText().toString();
        String doj=e7.getText().toString();
        String position=e8.getText().toString();

        if(empid == null || empid == "" || empid.length()<2)
        {
            Toast.makeText(this,"Please enter correct EmployeeID",Toast.LENGTH_LONG).show();
        }
        else
        {
            try
            {
                db.execSQL("update emp set empname='"+empname+"',address='"+address+"',phone='"+phone+"',dob='"+dob+"',age='"+age+"',doj='"+doj+"',position='"+position+"' where empid='"+empid+"'");
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
        Intent i=new Intent(this,EmployeeDetails.class);
        startActivity(i);
        finish();
    }
    public void cancel(View view)
    {
        Intent i=new Intent(this,EmployeeDetails.class);
        startActivity(i);
        finish();
    }
}
