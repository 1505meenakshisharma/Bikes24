package com.example.bikes24;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class usedautomobile extends AppCompatActivity {

    int[] IMAGES = {R.drawable.helloactiva, R.drawable.pulsur, R.drawable.rx, R.drawable.exel, R.drawable.passion, R.drawable.enfield, R.drawable.hero, R.drawable.shine};

    String[] NAMES = {"Activa", "Pulsar", "RX", "TVS-XL", "Passion-Pro", "Royal Enfield", "Hero Honda", "Honda Shine"};

    String[] DESCRIPTION = {"Company : Honda", "Company : Bajaj", "Company : RX", "Company : TVS", "Company : Hero", "Company : Royal Enfield", "Company : Hero", "Company : Honda"};

    String[] one = {"(1)", "(2)", "(3)", "(4)", "(5)", "(6)", "(7)", "(8)"};

    String[] two = {"Model : Activa-4G", "Model : Pulsar 150", "Model : RX-100", "Model : XL-100", "Model : Passion-pro", "Model : Royal-Enfield classic-350", "Model : Splender-plus", "Model : Honda shine"};

    String[] three = {"Price : Rs.20,000", "Price : Rs.30,000", "Price : Rs.15,000", "Price : Rs.13,000", "Price : Rs.22,000", "Price : Rs.1,10,000", "Price : Rs.17,000", "Price : Rs.25,000"};

    String[] four = {"Mileage : 25kmpl", "Mileage : 30kmpl", "Mileage : 18kmpl", "Mileage : 17kmpl", "Mileage : 34kmpl", "Mileage : 25kmpl", "Mileage : 35kmpl", "Mileage : 40kmpl"};

    String[] five = {"Engine : 109.20cc", "Engine : 109cc", "Engine : 100cc", "Engine : 90cc", "Engine : 100cc", "Engine : 350cc", "Engine : 110cc", "Engine : 125cc"};

    String[] six = {"Colors : Red", "Colors : black", "Color : Black", "Color : Black", "Color : Black", "Color : Black", "Color : Black", "Color : Black"};

    String[] seven = {"MAX_Speed : 120kmph", "MAX_Speed : 100kmph", "MAX_Speed : 110kmph", "MAX_Speed : 40kmph", "MAX_Speed : 90kmph", "MAX_Speed : 130kmph", "MAX_Speed : 100kmph", "MAX_Speed : 130kmph"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usedautomobile);
        ListView listView = (ListView) findViewById(R.id.listview);
        usedautomobile.CustomAdapter customAdapter = new usedautomobile.CustomAdapter();
        listView.setAdapter(customAdapter);
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return IMAGES.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup parent) {
            view = getLayoutInflater().inflate(R.layout.customused, null);

            ImageView imageView = (ImageView) view.findViewById(R.id.imageView11);
            TextView textView_name = (TextView) view.findViewById(R.id.name);
            TextView textView_description = (TextView) view.findViewById(R.id.description);
            TextView textView_one = (TextView) view.findViewById(R.id.textView76);
            TextView textView_two = (TextView) view.findViewById(R.id.textView68);
            TextView textView_three = (TextView) view.findViewById(R.id.textView69);
            TextView textView_four = (TextView) view.findViewById(R.id.textView70);
            TextView textView_five = (TextView) view.findViewById(R.id.textView73);
            TextView textView_six = (TextView) view.findViewById(R.id.textView78);
            TextView textView_seven = (TextView) view.findViewById(R.id.textView80);

            imageView.setImageResource(IMAGES[i]);
            textView_name.setText(NAMES[i]);
            textView_description.setText(DESCRIPTION[i]);
            textView_one.setText(one[i]);
            textView_two.setText(two[i]);
            textView_three.setText(three[i]);
            textView_four.setText(four[i]);
            textView_five.setText(five[i]);
            textView_six.setText(six[i]);
            textView_seven.setText(seven[i]);
            return view;
        }

    }

    public void onBackPressed() {
        Intent i = new Intent(this, VehicleDetails.class);
        startActivity(i);
        finish();
    }

    @SuppressLint("MissingPermission")
    public void call(View view) {
        Intent i = new Intent(Intent.ACTION_DIAL);
        i.setData(Uri.parse("tel:9472679634"));
        startActivity(i);

    }
}

