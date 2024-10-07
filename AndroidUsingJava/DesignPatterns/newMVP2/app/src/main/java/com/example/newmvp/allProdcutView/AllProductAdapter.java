package com.example.newmvp.allProdcutView;

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
import com.example.newmvp.model.Product;

import java.util.List;

public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.ViewHolder>{
    private final Context context;
    private List<Product> values;
    private static final String TAG = "RecycleView";
    AddProductToFav listener;

    public AllProductAdapter(Context context , AddProductToFav listener) {
        this.context = context;
        this.listener = listener;
    }
    public void setList(List<Product> updatedList){
        values = updatedList;
    }

    @NonNull
    @Override
    public AllProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_content_allproducts,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AllProductAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(values.get(position).getTitle());
        holder.brand.setText(values.get(position).getBrand());
        holder.description.setText(values.get(position).getDescription());
        holder.price.setText(String.valueOf(values.get(position).getPrice()));
        holder.rating.setRating((float)values.get(position).getRate());
        Glide.with(context).load(values.get(position).getImgUrl()).apply(new RequestOptions().override(200,200))
                .placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_foreground)
                .into(holder.thumbnail);
        holder.rating.setIsIndicator(true);
        holder.addFav.setOnClickListener(new View.OnClickListener() {
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
        Button addFav;
        public ViewHolder(View itemView){
            super(itemView);
            layout = itemView;
            title = (TextView) layout.findViewById(R.id.titleID);
            brand = (TextView) layout.findViewById(R.id.brandID);
            price = (TextView) layout.findViewById(R.id.priceID);
            description = (TextView) layout.findViewById(R.id.descriptionID);
            rating = (RatingBar) layout.findViewById(R.id.startsID);
            thumbnail = (ImageView) layout.findViewById(R.id.imageID);
            addFav = (Button) layout.findViewById(R.id.btnAddFavID);
        }

    }
}
