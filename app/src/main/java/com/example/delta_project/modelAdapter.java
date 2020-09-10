package com.example.delta_project;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class modelAdapter extends FirebaseRecyclerAdapter <model,modelAdapter.ViewHolder>{
    private OnIemClickListener1 mListener;

    private static final String TAG = "modelAdapter";

    public interface OnIemClickListener1{
        void onItemClick1( int position);
    }
    public void setOnItemClickListener1(OnIemClickListener1 listener){
        mListener=listener;

    }

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public modelAdapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull model model) {
         holder.tv.setText(model.getName());
//        Glide.with(holder.image.getContext()).load(model.getUrl()).placeholder(R.drawable.travelindia2).into(holder.image);
        Picasso.with(holder.image.getContext()).load(model.getUrl()).fit().centerCrop().placeholder(R.drawable.travelindia2).into(holder.image);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        return  new ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.item_name);
            image=itemView.findViewById(R.id.item_image_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(mListener!=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            Log.i(TAG,"" +position);
                            mListener.onItemClick1(position);
                        }
                    }
                }
            });
        }
    }


}
