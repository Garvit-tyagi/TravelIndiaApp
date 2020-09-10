package com.example.delta_project;

import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class favouritesAdapter extends RecyclerView.Adapter<favouritesAdapter.ExampleViewHolder> {

    private Context mcontext;
    private ArrayList<model>  favouritesList;
    private OnIemClickListener2 mListener;

    public interface OnIemClickListener2{
        void onItemClick2( int position);
    }
    public void setOnItemClickListener2(OnIemClickListener2 listener){
        mListener=listener;
    }

    public  favouritesAdapter(Context context, ArrayList<model> favouritesList){
        mcontext=context;
        this.favouritesList=favouritesList;
    }
    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mcontext).inflate(R.layout.recyclerview_item,parent,false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, final int position) {
        model currentItem= favouritesList.get(position);
        String Name=currentItem.getName();
        String imageUrl=currentItem.getUrl();
        holder.itemName.setText(Name);
        Picasso.with(mcontext).load(imageUrl).fit().centerCrop().placeholder(R.drawable.travelindia2).into(holder.itemimageView);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(mcontext);
                dialog.setTitle("Delete ...");
                dialog.setMessage("Are you sure you want to delete ?");
                dialog.setIcon(android.R.drawable.ic_menu_delete);
                dialog.setPositiveButton("Delete",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog1, int which) {
                                DatabaseHelper db = new DatabaseHelper(mcontext);

                                db.deleteitem(favouritesList.get(position));
                                favouritesList.remove(favouritesList.get(position));
                                notifyDataSetChanged();
                            }
                        });
                dialog.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                AlertDialog alertDialog = dialog.create();
                alertDialog.setCancelable(false);
                alertDialog.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return favouritesList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView itemimageView;
        public TextView itemName;



        public ExampleViewHolder(@NonNull View itemView) {

            super(itemView);
            itemimageView = itemView.findViewById(R.id.item_image_view);
            itemName = itemView.findViewById(R.id.item_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener!=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            mListener.onItemClick2(position);
                        }
                    }
                }
            });
        }

    }
}
