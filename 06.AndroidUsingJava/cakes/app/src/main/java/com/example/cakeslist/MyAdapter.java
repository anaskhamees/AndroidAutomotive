package com.example.cakeslist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
   private final Context context;

   private List<Cake> values;
   private static final String TAG = "RecyclerView";

    public MyAdapter(Context context,List<Cake>values) {
        this.context = context;
        this.values=values;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtTitle;
        public TextView txtDescription;
        public ImageView imageView;
        public ConstraintLayout constraintLayout ;
        public View layout;
       public ViewHolder(@NonNull View v) {
           super(v);
           layout = v;
           txtTitle =v.findViewById(R.id.textView2);
           txtDescription = v.findViewById(R.id.textView3);
           imageView = v.findViewById(R.id.imageView);
       }

   }

    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recycler, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recycler.getContext());
        View v = inflater.inflate(R.layout.customlayout,recycler,false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "onCreateViewHolder: ");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        holder.txtTitle.setText(values.get(position).getTitle());
        holder.txtDescription.setText(values.get(position).getDescription());
        holder.imageView.setImageResource(values.get(position).getThumbnail());
        Log.i(TAG, "onBindViewHolder: ");
    }

/*
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


    }

*/
    @Override
    public int getItemCount() {
        return values.size();
    }
}
