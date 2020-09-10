package com.example.delta_project;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class favouritesFragment extends Fragment implements favouritesAdapter.OnIemClickListener2 {
    private static final String TAG = "favouritesFragment";
    private RecyclerView rv;
    private  favouritesAdapter ma;
    ArrayList<model> favourites= new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.favourites_fragment,container,false);
        rv=view.findViewById(R.id.favourites_rv);
        ma=new favouritesAdapter(getContext(),favourites);
        rv.setLayoutManager(new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false));
        rv.setAdapter(ma);
        ma.setOnItemClickListener2( favouritesFragment.this);
        Cursor cursor =new DatabaseHelper(getContext()).readAllData();
        while (cursor.moveToNext()){
            model obj=new model(cursor.getString(1),cursor.getString(2));

            favourites.add(obj);
        }



        return view;
    }

    @Override
    public void onItemClick2(int position) {
        Toast.makeText(getContext(),"clicked",Toast.LENGTH_SHORT).show();
    }
}
