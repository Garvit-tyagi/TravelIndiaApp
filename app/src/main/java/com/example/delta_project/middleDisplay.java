package com.example.delta_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.net.http.SslCertificate;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import static com.example.delta_project.destinationsFragment.Dimageurl;
import static com.example.delta_project.destinationsFragment.Dname;

public class middleDisplay extends AppCompatActivity implements middleDisplayAdapter.OnIemClickListener12 {
private TextView middle_name,middle_info;
    private static final String TAG = "middleDisplay";
private String name;
private String imageurl;
String middleinformation;
private ImageView middle_image;
private RecyclerView middle_rv;
private Button addfavourites;
private ProgressBar loader;
private DatabaseHelper db=new DatabaseHelper(this);
    private FirebaseRecyclerOptions<middleDisplayModel> fro;
private middleDisplayAdapter ma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middle_display);
        Intent i=getIntent();
        if(i.getStringExtra("from").equals("destinationsFragment")){
            name=i.getStringExtra(Dname);
            Log.i(TAG,name);
            imageurl=i.getStringExtra(Dimageurl);
            middleinformation=i.getStringExtra("info");
        }
        else if(i.getStringExtra("from").equals("favourites")){
            name=i.getStringExtra("name");
            imageurl=i.getStringExtra("imageurl");
            middleinformation=i.getStringExtra("info");
        }
        else{
            name=i.getStringExtra("Dname");
            imageurl=i.getStringExtra("Dimageurl");
            middleinformation=i.getStringExtra("info");
        }

        middle_name=findViewById(R.id.midlle_name);
        middle_name.setText(name);
        middle_info=findViewById(R.id.middle_info);
        middle_info.setText(middleinformation);
        middle_image=findViewById(R.id.middle_image_view);
        Picasso.with(this).load(imageurl).fit().centerCrop().placeholder(R.drawable.travelindia2).into(middle_image);
        middle_rv=findViewById(R.id.middle_rv);

        FirebaseRecyclerOptions<middleDisplayModel> options =
                new FirebaseRecyclerOptions.Builder<middleDisplayModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child(name), middleDisplayModel.class)
                        .build();
          loader=findViewById(R.id.loader_middle);
          loader.setVisibility(View.INVISIBLE);
        middle_rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        fro=options;

        ma=new middleDisplayAdapter(options);
        middle_rv.setAdapter(ma);
        ma.setOnItemClickListener12(this);

        addfavourites=findViewById(R.id.addfavourites);
        addfavourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToFavourites(name,imageurl,middleinformation);
            }
        });

    }
    void addToFavourites(String n,String i,String info){
        Boolean check;
        check=db.checkEntry(n);

        if(check){
        String res=db.addRecord(n,i,info);
        Log.i(TAG,""+n+i+info);
        Toast.makeText(this,res,Toast.LENGTH_SHORT).show();}
        else{
            Toast.makeText(getApplicationContext(),"already added",Toast.LENGTH_SHORT).show();
        }



    }
    @Override
    public void onStart() {
        super.onStart();
        ma.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        ma.stopListening();
    }

    @Override
    public void onItemClick12(int position) {
        middleDisplayModel currentItem=fro.getSnapshots().get(position);
        Intent intent=new Intent(middleDisplay.this,finalDisplay.class);
        intent.putExtra("from","middleDisplay");
        intent.putExtra("name",currentItem.getName());
        intent.putExtra("url",currentItem.getUrl());
        intent.putExtra("info",currentItem.getInfo());
        startActivity(intent);
//        Toast.makeText(this,"clicked",Toast.LENGTH_SHORT).show();
    }
}
