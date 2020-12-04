package com.example.bikes24;

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

public class newautomobile extends AppCompatActivity {

    int[] IMAGES = {R.drawable.activa,R.drawable.deo,R.drawable.msk,R.drawable.vespa,R.drawable.apache,R.drawable.fascino,R.drawable.pulsar,R.drawable.ktm,R.drawable.royal,R.drawable.bajaj};

    String[] NAMES = {"Activa","Dio","TVS-XL","Vespa","Apache","Fascino","Pulsar","Duke","Royal Enfield","Bajaj Pulsar"};

    String[] DESCRIPTION = {"Company : Honda","Company : Honda","Company : TVS","Company : Piaggio","Company : TVS","Company : Yamaha","Company : Bajaj","Company : Bajaj","Company : Enfield","Company : Honda"};

    String[] one={"(1)","(2)","(3)","(4)","(5)","(6)","(7)","(8)","(9)","(10)"};

    String[] two={"Model : Activa-4G","Model : Honda Dio","Model : TVS-XL 100","Model : Vespa-SXL 150","Model : Apche RTR-160","Model : Yamaha Fascino","Model : Pulsar 180","Model : KTM1 200 Duke","Model : Classic 350","Model : Pulsar 200-NS"};

    String[] three={"Price : Rs.53,079","Price : Rs.51,468","Price : Rs.33,279","Price : Rs.71,649","Price : Rs.76,714","Price : Rs.56,082","Price : Rs.81,331","Price : Rs.1,48,621","Price : Rs.1,39,039","Price : Rs.95,971"};

    String[] four={"Mileage : 60kmpl","Mileage : 50kmpl","Mileage : 67kmpl","Mileage : 65kmpl","Mileage : 50kmpl","Mileage : 56kmpl","Mileage : 45kmpl","Mileage : 35kmpl","Mileage : 37kmpl","Mileage : 35kmpl"};

    String[] five={"Engine : 109.20cc","Engine : 109cc","Engine : 99.70cc","Engine : 150cc","Engine : 160cc","Engine : 113cc","Engine : 178cc","Engine : 199.50cc","Engine : 346cc","Engine : 199.5cc"};

    String[] six={"Colors : Red,blue","Colors : black,orange","Colors : Red,green","Colors : Orange,White","Colors : Red,white,Blue","Colors : white,blue","Colors : Blue,red","Colors : Orange,Blue","Colors : Red,Black","Colors : Red,Blue"};

    String[] seven={"MAX_Speed : 120kmph","MAX_Speed : 100kmph","MAX_Speed : 80kmph","MAX_Speed : 140kmph","MAX_Speed : 160kmph","MAX_Speed : 130kmph","MAX_Speed : 180kmph","MAX_Speed : 200kmph","MAX_Speed : 250kmph","MAX_Speed : 180kmph"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newautomobile);

        ListView listView=(ListView)findViewById(R.id.listview);
        CustomAdapter customAdapter=new CustomAdapter();
        listView.setAdapter(customAdapter);
    }
    class CustomAdapter extends BaseAdapter{

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
            view=getLayoutInflater().inflate(R.layout.customlayout,null);

            ImageView imageView=(ImageView)view.findViewById(R.id.imageView);
            TextView textView_name=(TextView)view.findViewById(R.id.name);
            TextView textView_description=(TextView)view.findViewById(R.id.description);
            TextView textView_one=(TextView)view.findViewById(R.id.one);
            TextView textView_two=(TextView)view.findViewById(R.id.two);
            TextView textView_three=(TextView)view.findViewById(R.id.three);
            TextView textView_four=(TextView)view.findViewById(R.id.four);
            TextView textView_five=(TextView)view.findViewById(R.id.five);
            TextView textView_six=(TextView)view.findViewById(R.id.six);
            TextView textView_seven=(TextView)view.findViewById(R.id.seven);

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
    public void view (View view)
    {
        Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.bikewale.com/m/"));
        startActivity(i);
    }
    public void onBackPressed()
    {
        Intent i=new Intent(this,VehicleDetails.class);
        startActivity(i);
        finish();
    }
}
