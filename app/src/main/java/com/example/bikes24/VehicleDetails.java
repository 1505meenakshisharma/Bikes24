package com.example.bikes24;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class VehicleDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_details);
    }
    public void onBackPressed()
    {
        Intent i=new Intent(this,Home.class);
        startActivity(i);
        finish();
    }
    public void cancel(View view)
    {
        Intent i=new Intent(this,Home.class);
        startActivity(i);
        finish();
    }
    public void newauto(View view)
    {
        Intent i=new Intent(this,newautomobile.class);
        startActivity(i);
        finish();
    }
    public void contact(View view)
    {
        Intent i=new Intent(this,ContactUs.class);
        startActivity(i);
        finish();
    }
    public void used(View view)
    {
        Intent i=new Intent(this,usedautomobile.class);
        startActivity(i);
        finish();
    }
}
