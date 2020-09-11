package com.example.delta_project;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.FirebaseDatabase;

public class statesFragment extends Fragment {
    private static final String TAG = "statesFragment";
    private RecyclerView statesrv;
    private EditText et;
    private modelAdapter ma;
    private ProgressBar loader;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("States");
        View view = inflater.inflate(R.layout.states_fragment, container, false);
        et=view.findViewById(R.id.edittext_states);
        statesrv = view.findViewById(R.id.states_rv);
        GridLayoutManager gv = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        final FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("states"), model.class)
                        .build();
        loader = view.findViewById(R.id.loader_states);
        loader.setVisibility(View.INVISIBLE);
        statesrv.setLayoutManager(gv);
        ma = new modelAdapter(options);
        statesrv.setAdapter(ma);
        ma.setOnItemClickListener1(new modelAdapter.OnIemClickListener1() {
            @Override
            public void onItemClick1(int position) {
                model c=options.getSnapshots().get(position);
             Intent i=new Intent(getContext(),webView.class);
             i.putExtra("from","statesFragment");
             i.putExtra("name",c.getName());

             startActivity(i);


            }
        });
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String string =et.getText().toString();
                processSearch(string);
            }
        });
//        et.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

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

//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        Log.i(TAG,"created");
//        getActivity().getMenuInflater().inflate(R.menu.search,menu);
//        MenuItem item=menu.getItem(R.id.search);
//        SearchView searchView=(SearchView) item.getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                processSearch(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                processSearch(newText);
//                return false;
//            }
//        });
//
//        super.onCreateOptionsMenu(menu, inflater);
//    }

    private void processSearch(String newText) {
        final FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("states").orderByChild("name").startAt(newText).endAt(newText+"\uf8ff"), model.class)
                        .build();
        ma=new modelAdapter(options);
        ma.startListening();
        statesrv.setAdapter(ma);
        ma.setOnItemClickListener1(new modelAdapter.OnIemClickListener1() {
            @Override
            public void onItemClick1(int position) {
                model c=options.getSnapshots().get(position);
                Intent i=new Intent(getContext(),webView.class);
                i.putExtra("from","statesFragment");
                i.putExtra("name",c.getName());

                startActivity(i);


            }
        });
    }
}
