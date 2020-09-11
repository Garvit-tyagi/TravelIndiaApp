package com.example.delta_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.delta_project.homeFragment.Finalname;
import static com.example.delta_project.homeFragment.Finalurl;

public class finalDisplay extends AppCompatActivity {
    private static final String TAG = "finalDisplay";
    public static final String Value="value";
    private TextView info;
String information;
    String imageUrl;
    String finalName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_display);

        Intent i=getIntent();
        if(i.getStringExtra("from").equals("middleDisplay")){
            information=i.getStringExtra("info");
            imageUrl=i.getStringExtra("url");
            finalName=i.getStringExtra("name");
        }else if(i.getStringExtra("from").equals("pii")){
            Log.i(TAG,"from pii");
            information=i.getStringExtra("info");
            imageUrl=i.getStringExtra("url");
            finalName=i.getStringExtra("name");
        }
        else if(i.getStringExtra("from").equals("sliderItem")){
            information=i.getStringExtra("info");
            imageUrl=i.getStringExtra(Finalurl);
            finalName=i.getStringExtra(Finalname);
        }
        else{
            information="information";
         imageUrl=i.getStringExtra(Finalurl);
         finalName=i.getStringExtra(Finalname);
        }
            info=findViewById(R.id.info);
            info.setText(information);
        ImageView image=findViewById(R.id.detail_image);
        TextView name=findViewById(R.id.detail_name);
        name.setText(finalName);

        Picasso.with(this).load(imageUrl).fit().centerCrop().placeholder(R.drawable.travelindia1).into(image);
        ImageView iv=findViewById(R.id.chrome);
        ImageView iv2=findViewById(R.id.wiki);
        ImageView iv3=findViewById(R.id.maps);
        ImageView iv4=findViewById(R.id.directions);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i=new Intent(finalDisplay.this,webView.class);
               i.putExtra(Finalname,finalName);
               i.putExtra(Value,"chrome");
              i.putExtra("from","finalDisplay");
               startActivity(i);
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(finalDisplay.this,webView.class);
                i.putExtra(Finalname,finalName);
                i.putExtra(Value,"wiki");
                i.putExtra("from","finalDisplay");
                startActivity(i);
            }
        });
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(finalDisplay.this,webView.class);
                i.putExtra(Finalname,finalName);
                i.putExtra(Finalurl,imageUrl);
                i.putExtra(Value,"location");
                i.putExtra("from","finalDisplay");
                startActivity(i);
            }
        });
        iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(finalDisplay.this,webView.class);
                i.putExtra(Finalname,finalName);
                i.putExtra(Finalurl,imageUrl);
                i.putExtra(Value,"directions");
                i.putExtra("from","finalDisplay");
                startActivity(i);
            }
        });

    }
}
