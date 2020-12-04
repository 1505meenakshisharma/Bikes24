package com.example.bikes24;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ContactUs extends AppCompatActivity
{
    Button btnCall;
    EditText numtxt;
    String sNum;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        btnCall=(Button)findViewById(R.id.button96);
        numtxt=(EditText)findViewById(R.id.editText86);
    }
    @SuppressLint("MissingPermission")
    public void btnCall(View view)
    {
        Intent i=new Intent(Intent.ACTION_DIAL);
        sNum=numtxt.getText().toString();
        if(sNum.trim().isEmpty()){
            i.setData(Uri.parse("tel:9472679634"));
        }
        else {
            i.setData(Uri.parse("tel:"+sNum));
        }
        startActivity(i);
    }
    @Override
    public void onBackPressed()
    {
        Intent i=new Intent(this,Home.class);
        startActivity(i);
        finish();
    }
    public void mail(View view)
    {
        Intent i=new Intent(Intent.ACTION_SEND);
        i.setData(Uri.parse("email"));
        String[] s={"sarrafshubham40@gmail.com","sharmameenakshi66@gmail.com","amruta.teena@gmail.com"};
        i.putExtra(Intent.EXTRA_EMAIL,s);
        i.putExtra(Intent.EXTRA_TEXT,"");
        i.setType("message/rfc882");
        Intent chooser=Intent.createChooser(i,"Launch Email");
        startActivity(chooser);
    }
}
