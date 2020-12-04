package com.example.bikes24;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class qrcodegenerate extends AppCompatActivity {

    TextView t1, t2, t3, t4, t5;
    EditText et1;
    ImageView image;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcodegenerate);
        db = openOrCreateDatabase("mydb", MODE_PRIVATE, null);
        image = (ImageView) findViewById(R.id.imageView);
        et1 = (EditText) findViewById(R.id.editText25);
        t1 = (TextView) findViewById(R.id.textView);
        t2 = (TextView) findViewById(R.id.textView2);
        t3 = (TextView) findViewById(R.id.textView3);
        t4 = (TextView) findViewById(R.id.textView4);
        t5 = (TextView) findViewById(R.id.textView23);
    }

    public void search(View v)
    {
        String salesid = et1.getText().toString();
        image.setImageDrawable(null);
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        t5.setText("");
        if (salesid == null || salesid == "" || salesid.length() < 3)
        {
            Toast.makeText(this, "Please enter correct Sales Id...", Toast.LENGTH_LONG).show();
        }
        else
        {
            Cursor c = db.rawQuery("select * from sales where salesid='" + salesid + "'", null);
            c.moveToFirst();
            if (c.getCount() > 0)
            {
                do
                {
                    String custname = c.getString(2);
                    String address = c.getString(3);
                    String model = c.getString(6);
                    String price = c.getString(7);
                    String salesdate = c.getString(8);
                    t1.setText(custname);
                    t2.setText(address);
                    t3.setText(model);
                    t4.setText(price);
                    t5.setText(salesdate);
                } while (c.moveToNext());
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try
                {
                    BitMatrix bitMatrix = multiFormatWriter.encode(salesid, BarcodeFormat.QR_CODE, 200, 200);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    image.setImageBitmap(bitmap);
                }
                catch (WriterException e)
                {
                    e.printStackTrace();
                }
            }
            else
                Toast.makeText(this, "Sales Id does not match...", Toast.LENGTH_LONG).show();
        }
        et1.setText("");
    }

    public void back(View view)
    {
        Intent i = new Intent(this, Home.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(this, Home.class);
        startActivity(i);
        finish();
    }
}
