package com.example.bikes24;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SalesDetailBannerForManager extends AppCompatActivity
{
    private static final String TAG = SalesDetailBannerForManager.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_detail_banner_for_manager);
    }

    public void sales_check(View view)
    {
        Intent i= new Intent(this,AutomobileListForManager.class);
        startActivity(i);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout)
        {
            final AlertDialog.Builder builder=new AlertDialog.Builder(SalesDetailBannerForManager.this);
            builder.setMessage("Do you really want to Logout?");
            builder.setCancelable(true);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    // After Logout redirect user to MainActivity
                    Intent logout = new Intent(SalesDetailBannerForManager.this, MainActivity.class);

                    // Closing all the Activities
                    logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    // Add new Flag to start new Activity
                    logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    // Starting MainActivity
                    startActivity(logout);
                    finish();
                    Toast.makeText(SalesDetailBannerForManager.this,"Logout successful",Toast.LENGTH_LONG).show();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    dialog.cancel();
                }
            });

            AlertDialog alertDialog=builder.create();
            alertDialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
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
        Toast.makeText(SalesDetailBannerForManager.this,"Please press Back again to exit",Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                twice=false;
                Log.d(TAG,"twice:"+twice);
            }
        },3000);
    }
}