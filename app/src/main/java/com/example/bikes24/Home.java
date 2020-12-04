package com.example.bikes24;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{
    final String TAG=this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i=new Intent(Intent.ACTION_SEND);
                i.setData(Uri.parse("email"));
                String[] s={"sarrafshubham40@gmail.com","sharmameenakshi66@gmail.com","amruta.teena@gmail.com"};
                i.putExtra(Intent.EXTRA_EMAIL,s);
                i.putExtra(Intent.EXTRA_SUBJECT,"FeedBack");
                i.putExtra(Intent.EXTRA_TEXT,"");
                i.setType("message/rfc882");
                Intent chooser=Intent.createChooser(i,"Launch Email");
                startActivity(chooser);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
            final AlertDialog.Builder builder=new AlertDialog.Builder(Home.this);
            builder.setMessage("Do you really want to Logout?");
            builder.setCancelable(true);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    // After Logout redirect user to MainActivity
                    Intent logout = new Intent(Home.this, MainActivity.class);

                    // Closing all the Activities
                    logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    // Add new Flag to start new Activity
                    logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    // Starting MainActivity
                    startActivity(logout);
                    finish();
                    Toast.makeText(Home.this,"Logout successful",Toast.LENGTH_LONG).show();
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
        Toast.makeText(Home.this,"Please press Back again to exit",Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                twice=false;
                Log.d(TAG,"twice:"+twice);
            }
        },3000);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Automobiles)
        {
            Intent i= new Intent(this,AutomobileDetails.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_Customers)
        {
            Intent i= new Intent(this,customer.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_Employees)
        {
            Intent i=new Intent(this,EmployeeDetails.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_sales)
        {
            Intent i=new Intent(this,SalesDetails.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_Billing)
        {
            Intent i=new Intent(this,Billing.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_Viewall)
        {
            Intent i=new Intent(this,ViewDetails.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_buy_automobiles)
        {
            Intent i=new Intent(this,VehicleDetails.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_Complaints)
        {
            Intent i=new Intent(this,Complaints.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_Generate)
        {
            Intent i=new Intent(this,qrcodegenerate.class);
            startActivity(i);
            finish();
        }
        else if(id == R.id.nav_feedback)
        {
            Intent i=new Intent(Intent.ACTION_SEND);
            i.setData(Uri.parse("email"));
            String[] s={"sarrafshubham40@gmail.com","sharmameenakshi66@gmail.com","amruta.teena@gmail.com"};
            i.putExtra(Intent.EXTRA_EMAIL,s);
            i.putExtra(Intent.EXTRA_SUBJECT,"FeedBack");
            i.putExtra(Intent.EXTRA_TEXT,"");
            i.setType("message/rfc882");
            Intent chooser=Intent.createChooser(i,"Launch Email");
            startActivity(chooser);
        }
        else if (id == R.id.nav_contact_us)
        {
            Intent i=new Intent(this,ContactUs.class);
            startActivity(i);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void automobiles(View view)
    {
        Intent i= new Intent(this,AutomobileDetails.class);
        startActivity(i);
        finish();
    }

    public void customers(View view)
    {
        Intent i= new Intent(this,customer.class);
        startActivity(i);
        finish();
    }

    public void employees(View view)
    {
        Intent i=new Intent(this,EmployeeDetails.class);
        startActivity(i);
        finish();
    }

    public void sales(View view)
    {
        Intent i=new Intent(this,SalesDetails.class);
        startActivity(i);
        finish();
    }

    public void complaints(View view)
    {
        Intent i=new Intent(this,Complaints.class);
        startActivity(i);
        finish();
    }

    public void billing(View view)
    {
        Intent i=new Intent(this,Billing.class);
        startActivity(i);
        finish();
    }
}
