package com.example.bikes24;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayBill extends AppCompatActivity {
    TextView e1, e2, e3, e4, e5, e6,e7,e8,e9;
    EditText et1;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_bill);
        et1=(EditText)findViewById(R.id.editText26);
        e1 = (TextView) findViewById(R.id.textView85);
        e2 = (TextView) findViewById(R.id.textView94);
        e3 = (TextView) findViewById(R.id.textView95);
        e4 = (TextView) findViewById(R.id.textView96);
        e5 = (TextView) findViewById(R.id.textView97);
        e6 = (TextView) findViewById(R.id.textView98);
        e7 = (TextView) findViewById(R.id.textView99);
        e8 = (TextView) findViewById(R.id.textView100);
        e9 = (TextView) findViewById(R.id.textView101);
        db = openOrCreateDatabase("mydb", MODE_PRIVATE, null);
    }
    public void generate(View view)
    {
        Intent i=new Intent(this,qrcodegenerate.class);
        startActivity(i);
        finish();
    }
    public void search(View view)
    {
        String salesid = et1.getText().toString();
        if (salesid == null || salesid == "" || salesid.length() < 2)
        {
            Toast.makeText(this, "Enter valid SalesID", Toast.LENGTH_LONG).show();
        }
        else
        {
            Cursor c = db.rawQuery("select * from bill where salesid='" + salesid + "'", null);
            e1.setText("");
            e2.setText("");
            e3.setText("");
            e4.setText("");
            e5.setText("");
            e6.setText("");
            e7.setText("");
            e8.setText("");
            e9.setText("");
            c.moveToFirst();
            if (c.getCount() > 0)
            {
                do
                {
                    String sales = c.getString(0);
                    String custname = c.getString(1);
                    String address = c.getString(2);
                    String phone = c.getString(3);
                    String model = c.getString(4);
                    String price = c.getString(5);
                    String salesdate= c.getString(6);
                    String taxes= c.getString(7);
                    String total= c.getString(8);
                    e1.setText(sales);
                    e2.setText(custname);
                    e3.setText(address);
                    e4.setText(phone);
                    e5.setText(model);
                    e6.setText(salesdate);
                    e7.setText(price);
                    e8.setText(taxes);
                    e9.setText(total);
                } while (c.moveToNext());
            }
            else
            {
                Toast.makeText(this, " Salesid Doesn't match", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void back(View view)
    {
        Intent i=new Intent(this,Billing.class);
        startActivity(i);
        finish();
    }
}
