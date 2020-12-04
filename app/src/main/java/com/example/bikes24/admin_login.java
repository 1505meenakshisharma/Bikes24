package com.example.bikes24;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class admin_login extends AppCompatActivity
{
    EditText et1,et2;
    Button login;
    final String TAG=this.getClass().getName();
    CheckBox chk;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        et1=(EditText)findViewById(R.id.username);
        et2=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.login);
        chk=(CheckBox)findViewById(R.id.checkBox);
        chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean Check) {

                if(Check)
                {
                    et2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                else
                {
                    et2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }

            }
        });
    }
    public void cancel(View view)
    {
        Intent cancel=new Intent(this,MainActivity.class);
        startActivity(cancel);
        finish();
    }
    public void login(View v)
    {
        String username = et1.getText().toString();
        String password = et2.getText().toString();


        if (username == null || username =="" || username.length()<5)
        {
            Toast.makeText(this, "enter valid username", Toast.LENGTH_LONG).show();
        }
        else if (password == null || password =="" || password.length()<5)
        {
            Toast.makeText(this, "enter valid password", Toast.LENGTH_LONG).show();
        }
        if (username.equals("Admin") && password.equals("admin123"))
        {
            Intent i = new Intent(this, Home.class);
            startActivity(i);
            finish();
            Toast.makeText(this,"Login successful",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"You are not an Admin",Toast.LENGTH_LONG).show();
        }
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
        Toast.makeText(admin_login.this,"Please press Back again to exit",Toast.LENGTH_LONG).show();
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
