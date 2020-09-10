package com.example.delta_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.List;


public class destinationsFragment extends Fragment implements modelAdapter.OnIemClickListener1{
    private RecyclerView rv;
    private static final String TAG = "destinationsFragment";
    private modelAdapter ma;
    private FirebaseRecyclerOptions<model> fro;
    ArrayList<model> yourStringArray=new ArrayList<>();

    public static final String Dname="dname";
    public static final String Dimageurl="dimagename";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.destinations_fragment,container,false);
        getActivity().setTitle("Destinations");
        rv=view.findViewById(R.id.destinations_rv);
        GridLayoutManager gv=new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Destinations"), model.class)
                        .build();
        GenericTypeIndicator<List<String>> t = new GenericTypeIndicator<List<String>>() {};



        rv.setLayoutManager(gv);

        fro=options;

        ma=new modelAdapter(options);
        rv.setAdapter(ma);

    ma.setOnItemClickListener1(this);
        return view;
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
    public void onItemClick1(int position) {
        Intent i=new Intent(getContext(),middleDisplay.class);

        model clickedItem= fro.getSnapshots().get(position);
        i.putExtra(Dname,clickedItem.getName());
        Log.i(TAG,clickedItem.getName());
        i.putExtra(Dimageurl,clickedItem.getUrl());
        Log.i(TAG,clickedItem.getUrl());
        i.putExtra("info",clickedItem.getInfo());
        i.putExtra("from","destinationsFragment");
        startActivity(i);

    }
}
