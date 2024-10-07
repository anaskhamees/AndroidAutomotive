package com.example.newmvp.favProductView;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.newmvp.R;
import com.example.newmvp.allProdcutView.AddProductToFav;
import com.example.newmvp.model.Product;

import java.util.List;

public class FavProductAdapter extends RecyclerView.Adapter<FavProductAdapter.ViewHolder> {
    private final Context context;
    private List<Product> values;
    private static final String TAG = "RecycleView";
    AddProductToFav listener;
    public FavProductAdapter(Context m_context , AddProductToFav listener){
        context = m_context;
        this.listener = listener;
    }
    public void setList(List<Product> updatedList){
        values = updatedList;
    }

    @NonNull
    @Override
    public FavProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_content_favproducts,  parent,false);
        FavProductAdapter.ViewHolder vh = new FavProductAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull FavProductAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.title.setText(values.get(position).getTitle());
        holder.brand.setText(values.get(position).getBrand());
        holder.description.setText(values.get(position).getDescription());
        holder.price.setText(String.valueOf(values.get(position).getPrice()));
        holder.rating.setRating((float)values.get(position).getRate());
        Glide.with(context).load(values.get(position).getImgUrl()).apply(new RequestOptions().override(200,200))
                .placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_foreground)
                .into(holder.thumbnail);
        holder.rating.setIsIndicator(true);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onProductClickListener(values.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView thumbnail;
        public TextView title;
        public TextView brand;
        public TextView price;
        public TextView description;
        public RatingBar rating;
        public View layout;
        public Button delete;
        public ViewHolder(View itemView){
            super(itemView);
            layout = itemView;
            title = (TextView) layout.findViewById(R.id.titleID);
            brand = (TextView) layout.findViewById(R.id.brandID);
            price = (TextView) layout.findViewById(R.id.priceID);
            description = (TextView) layout.findViewById(R.id.descriptionID);
            rating = (RatingBar) layout.findViewById(R.id.startsID);
            thumbnail = (ImageView) layout.findViewById(R.id.imageID);
            delete = (Button) layout.findViewById(R.id.btnRemovID);
        }


    }
}