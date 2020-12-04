package com.example.bikes24;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity
{
    EditText et1,et2;
    SQLiteDatabase database;
    CheckBox chk;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et1=(EditText)findViewById(R.id.username);
        et2=(EditText)findViewById(R.id.password);
        chk=(CheckBox)findViewById(R.id.checkBox3);
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
        database=openOrCreateDatabase(  "mydatabase",MODE_PRIVATE,null);
    }
    public void login(View view)
    {
        String username=et1.getText().toString();
        String password=et2.getText().toString();

        if (username == null || username == "" || username.length()<3 )
        {
            Toast.makeText(this,"Enter a correct username",Toast.LENGTH_LONG).show();
        }
        else if (password == null || password == "" || password.length()<6)
        {
            Toast.makeText(this,"Enter a correct password",Toast.LENGTH_LONG).show();
        }
        if (username.equals("Manager") && password.equals("manager123"))
        {
            Intent i = new Intent(this, SalesDetailBannerForManager.class);
            startActivity(i);
            finish();
            Toast.makeText(this,"Login successful",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"You are not a Manager",Toast.LENGTH_LONG).show();
        }
    }
    public void cancel(View view)
    {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }
}
