package com.example.bikes24;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ViewDetails extends AppCompatActivity
{
    final String TAG=this.getClass().getName();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
    }
    boolean twice=false;
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
        Toast.makeText(ViewDetails.this,"Please press Back again to exit",Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                twice=false;
                Log.d(TAG,"twice:"+twice);
            }
        },3000);
    }
    public void automobiles(View view)
    {
        Intent i=new Intent(this,ViewAutomobiles.class);
        startActivity(i);
        finish();
    }
    public void customers(View view)
    {
        Intent i=new Intent(this,ViewCustomers.class);
        startActivity(i);
        finish();
    }
    public void back(View view)
    {
        Intent i=new Intent(this,Home.class);
        startActivity(i);
        finish();
    }
    public void employees(View view)
    {
        Intent i=new Intent(this,ViewEmployees.class);
        startActivity(i);
        finish();
    }
    public void sales(View view)
    {
        Intent i=new Intent(this,ViewSales.class);
        startActivity(i);
        finish();
    }
    public void complaints(View view)
    {
        Intent i=new Intent(this,ViewComplaints.class);
        startActivity(i);
        finish();
    }
}
