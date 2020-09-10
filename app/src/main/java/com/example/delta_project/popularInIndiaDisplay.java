package com.example.delta_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class popularInIndiaDisplay extends AppCompatActivity {
    private static final String TAG = "popularInIndiaDisplay";
private TextView name,info;
private ImageView image;
private RecyclerView rv;
private middleDisplayAdapter ma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_in_india_display);
        name=findViewById(R.id.pii_name);
        info=findViewById(R.id.pii_info);
        rv=findViewById(R.id.pii_rv);
        image=findViewById(R.id.pii_image_view);
        Intent i=getIntent();
        name.setText(i.getStringExtra("name"));
        info.setText(i.getStringExtra("info"));
        Picasso.with(this).load(i.getStringExtra("url")).fit().centerCrop().placeholder(R.drawable.travelindia2).into(image);
        final FirebaseRecyclerOptions<middleDisplayModel> options =
                new FirebaseRecyclerOptions.Builder<middleDisplayModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child(name.getText().toString().trim()), middleDisplayModel.class)
                        .build();
        rv.setLayoutManager(new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false));
        ma=new middleDisplayAdapter(options);
        rv.setAdapter(ma);
        ma.setOnItemClickListener12(new middleDisplayAdapter.OnIemClickListener12() {
            @Override
            public void onItemClick12(int position) {
//                Toast.makeText(popularInIndiaDisplay.this,"clicked",Toast.LENGTH_SHORT).show();
                middleDisplayModel current_item=options.getSnapshots().get(position);
                Intent i=new Intent(popularInIndiaDisplay.this,finalDisplay.class);
                 i.putExtra("from","pii");
                 i.putExtra("name",current_item.getName());
                 i.putExtra("info",current_item.getInfo());
                 i.putExtra("url",current_item.getUrl());
                Log.i(TAG,"passed");
                 startActivity(i);

            }
        });


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
}
