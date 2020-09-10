package com.example.delta_project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.FirebaseDatabase;

public class statesFragment extends Fragment {
    private RecyclerView statesrv;
    private modelAdapter ma;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
getActivity().setTitle("States");
        View view= inflater.inflate(R.layout.states_fragment,container,false);
          statesrv=view.findViewById(R.id.states_rv);
          GridLayoutManager gv=new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("states"), model.class)
                        .build();
     statesrv.setLayoutManager(gv);
    ma=new modelAdapter(options);
    statesrv.setAdapter(ma);


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
}
