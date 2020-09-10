package com.example.delta_project;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class homeFragment extends Fragment implements SliderAdapterExample.OnIemClickListener, modelAdapter.OnIemClickListener1 {
    SliderView sliderView;
    List<SliderItem> sliderItemList;
    private RecyclerView mustvisitrv;
    private modelAdapter ma;
    private modelAdapter ma2;
    private RecyclerView popularrv;
    private TextView viewall;
    private SliderAdapterExample adapter;
    private ProgressBar loader;
    public static final String Extraurl = "imageUrl";
    public static final String Extraname = "stateName";
    public static final String Finalurl = "finalUrl";
    public static final String Finalname = "finalName";
    private FirebaseRecyclerOptions<model> fro;
    private static final String TAG = "homeFragment";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.home_fragment, container, false);
        viewall = view.findViewById(R.id.homeMustVisitviewall);
        mustvisitrv = view.findViewById(R.id.homeMustVisitrv);
        popularrv = view.findViewById(R.id.homePopularrv);


        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Must Visit Destinations"), model.class)
                        .build();
        mustvisitrv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        ma = new modelAdapter(options);
        mustvisitrv.setAdapter(ma);
        ma.setOnItemClickListener1(homeFragment.this);
fro=options;
        final FirebaseRecyclerOptions<model> options2 =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Popular In India"), model.class)
                        .build();
        popularrv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        ma2 = new modelAdapter(options2);
        ma2.setOnItemClickListener1(new modelAdapter.OnIemClickListener1() {
            @Override
            public void onItemClick1(int position) {
//                Toast.makeText(getContext(),"clicked",Toast.LENGTH_SHORT).show();
                model current_item=options2.getSnapshots().get(position);
                Intent i=new Intent(getContext(),popularInIndiaDisplay.class);
                i.putExtra("name",current_item.getName());
                i.putExtra("info",current_item.getInfo());
                i.putExtra("url",current_item.getUrl());
                startActivity(i);
            }
        });
        popularrv.setAdapter(ma2);
//        loader=view.findViewById(R.id.loader);
//        loader.setVisibility(View.INVISIBLE);


        sliderView = view.findViewById(R.id.imageSlider);
        adapter = new SliderAdapterExample(getContext());

        sliderView.setSliderAdapter(adapter);
        adapter.setOnItemClickListener(homeFragment.this);

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();
        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                Log.i("GGG", "onIndicatorClicked: " + sliderView.getCurrentPagePosition());
            }
        });
        renewItems(view);
        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 destinationsFragment df=new destinationsFragment();
                  getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,df).commit();
            }
        });
        return view;


    }

    public void renewItems(View view) {
        sliderItemList = new ArrayList<>();
        SliderItem s1 = new SliderItem();
        s1.setDescription("The Taj Mahal");
        s1.setImageUrl("https://static.toiimg.com/photo/31346158.cms.cms");
        SliderItem s2 = new SliderItem();
        s2.setDescription("The Red Fort");
        s2.setImageUrl("https://images.indianexpress.com/2018/06/red-fort-1200-getty-images.jpg");
        SliderItem s3 = new SliderItem();
        s3.setDescription("The Hawa Mahal");
        s3.setImageUrl("https://www.swantour.com/sites/default/files/hawa-mahal-jaipur.jpg");
        SliderItem s4 = new SliderItem();
        s4.setDescription("The Golden Temple");
        s4.setImageUrl("https://static.toiimg.com/photo/61820954.cms");
        SliderItem s5 = new SliderItem();
        s5.setDescription("The Ellora Caves");
        s5.setImageUrl("https://media.tacdn.com/media/attractions-splice-spp-674x446/0a/01/38/a7.jpg");
        sliderItemList.add(s1);
        sliderItemList.add(s2);
        sliderItemList.add(s3);
        sliderItemList.add(s4);
        sliderItemList.add(s5);

        adapter.renewItems(sliderItemList);
    }

    public void removeLastItem(View view) {
        if (adapter.getCount() - 1 >= 0)
            adapter.deleteItem(adapter.getCount() - 1);
    }

    public void addNewItem(View view) {
        SliderItem sliderItem = new SliderItem();
        sliderItem.setDescription("Slider Item Added Manually");
        sliderItem.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
        adapter.addItem(sliderItem);
    }

    @Override
    public void onItemClick(int position) {
        Intent i = new Intent(getContext(), finalDisplay.class);
        SliderItem currentItem = sliderItemList.get(position);
        i.putExtra("from","sliderItem");
        i.putExtra(Finalname, currentItem.getDescription());
        i.putExtra(Finalurl, currentItem.getImageUrl());
        startActivity(i);
    }

    @Override
    public void onStart() {
        super.onStart();
        ma2.startListening();
        ma.startListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Travel India");
    }

    @Override
    public void onItemClick1(int position) {

        Intent i=new Intent(getContext(),middleDisplay.class);

        model clickedItem= fro.getSnapshots().get(position);
        i.putExtra("Dname",clickedItem.getName());
        Log.i(TAG,clickedItem.getName());
        i.putExtra("Dimageurl",clickedItem.getUrl());
        Log.i(TAG,clickedItem.getUrl());
        i.putExtra("from","HomeFragment");
        i.putExtra("info",clickedItem.getInfo());
        startActivity(i);
    }

}

