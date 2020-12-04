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

public class EmployeeDetails extends AppCompatActivity
{
    EditText e1,e2,e3,e4,e5,e6,e7,e8;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);
        e1=(EditText)findViewById(R.id.editText27);
        e2=(EditText)findViewById(R.id.editText28);
        e3=(EditText)findViewById(R.id.editText29);
        e4=(EditText)findViewById(R.id.editText30);
        e5=(EditText)findViewById(R.id.editText31);
        e6=(EditText)findViewById(R.id.editText32);
        e7=(EditText)findViewById(R.id.editText45);
        e8=(EditText)findViewById(R.id.editText46);
        db=openOrCreateDatabase("mydb",MODE_PRIVATE,null);
        db.execSQL("create table if not exists emp(empid varchar,empname varchar,address varchar,phone varchar,dob varchar,age varchar,doj varchar,position varchar)");
    }
    public void add(View view)
    {
        String empid=e1.getText().toString();
        String empname=e2.getText().toString();
        String address=e3.getText().toString();
        String phone=e4.getText().toString();
        String dob=e5.getText().toString();
        String age=e6.getText().toString();
        String doj=e7.getText().toString();
        String position=e8.getText().toString();

        if (empid  == null || empid == "" || empid.length()<2)
        {
            Toast.makeText(this, "Enter valid EmployeeID", Toast.LENGTH_LONG).show();
        }
        else if(empname == null || empname == "" || empname.length()<3)
        {
            Toast.makeText(this, "Enter valid Employee Name", Toast.LENGTH_LONG).show();
        }
        else if(address == null || address == "" || address.length()<3)
        {
            Toast.makeText(this, "Enter valid address", Toast.LENGTH_LONG).show();
        }
        else if(phone == null || phone == "" || phone.length()<10)
        {
            Toast.makeText(this, "Enter valid phonenumber", Toast.LENGTH_LONG).show();
        }
        else if(dob == null || dob== "" || dob.length()<9)
        {
            Toast.makeText(this, "Enter valid Date of Birth", Toast.LENGTH_LONG).show();
        }
        else if(age == null || age == "" || age.length()<2)
        {
            Toast.makeText(this, "Enter valid Age", Toast.LENGTH_LONG).show();
        }
        else if(doj == null || doj == "" || doj.length()<9)
        {
            Toast.makeText(this, "Enter valid Date", Toast.LENGTH_LONG).show();
        }
        else if(position == null || position == "" || position.length()<3)
        {
            Toast.makeText(this, "Enter valid Position", Toast.LENGTH_LONG).show();
        }
        else
        {
            Cursor c = db.rawQuery("select * from emp where empid='" + empid + "'", null);
            c.moveToFirst();
            if (c.getCount() > 0)
            {
                Toast.makeText(this, "EmployeeID already exists", Toast.LENGTH_LONG).show();
            }
            else
            {
                try
                {
                    db.execSQL("insert into emp values('" + empid + "','" + empname + "','" + address + "','" + phone + "','" + dob + "','" + age + "','"+doj+"','"+position+"')");
                    Toast.makeText(this, "Employee added", Toast.LENGTH_LONG).show();
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
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
    }
    public void edit(View view)
    {
        Intent i=new Intent(this,EditEmployees.class);
        startActivity(i);
        finish();
    }
    public void cancel(View view)
    {
        Intent i=new Intent(this,Home.class);
        startActivity(i);
        finish();
    }
}
