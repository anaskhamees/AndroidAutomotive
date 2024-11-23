package com.example.mcv;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONObject;

import java.util.List;
import model.Product;


class FavProductsAdaptor extends RecyclerView.Adapter<FavProductsAdaptor.MyViewHolder> {
    private final Context context;
    private static JSONObject testobj;
    private static final String TAG = "FavProductsAdaptor";
    private List<Product> products;
    private RemoveFavProduct myRemoveFavProduct;

    public FavProductsAdaptor(Context context, RemoveFavProduct InterfaceRef)
    {

        this.context = context;
        //this.products = products;
        this.myRemoveFavProduct = InterfaceRef;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setAdapter(List<Product> products)
    {
        this.products = products;;
        //Log.i("TAG", myDataSet.toString());
        notifyDataSetChanged();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView editTitle;
        public TextView editPrice;
        public TextView editBrand;
        public TextView editDescription;
        public RatingBar ratingBar;
        public ImageView img;
        public Button btnRemoveFromFav;

        public ConstraintLayout constraintLayout;
        public View layout;

        @SuppressLint("WrongViewCast")
        public MyViewHolder(View v) {
            super(v);

            layout = v;
            editTitle = v.findViewById(R.id.titleID);
            editPrice = v.findViewById(R.id.priceID);
            editBrand = v.findViewById(R.id.brandID);
            editDescription = v.findViewById(R.id.descriptionID);
            btnRemoveFromFav = v.findViewById(R.id.btnRemovID);
            img = v.findViewById(R.id.imageID);
            ratingBar = v.findViewById(R.id.startsID);
            ratingBar.setEnabled(false);
            constraintLayout = v.findViewById(R.id.rowContentFavProductID);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.row_content_favproducts, recyclerView, false);
        MyViewHolder vh = new MyViewHolder(v);
        Log.i(TAG, "onCreateViewHolder");
        return vh;
    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        if(products!=null)
        {
            //this.temp=products.size();
            holder.editTitle.setText(products.get(position).getTitle());
            holder.editPrice.setText(Double.toString(products.get(position).getPrice()));
            holder.editBrand.setText(products.get(position).toString());
            holder.editDescription.setText(products.get(position).getDescription());
            float rating = (float) ((products.get(position).getRating()) /2);
            holder.ratingBar.setRating(rating);

            Glide.with(context)
                    .load(products.get(position).getThumbnail())
                    .apply(new RequestOptions()
                            .override(200, 200)
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .error(R.drawable.ic_launcher_foreground))
                    .into(holder.img); // Use holder.imageView instead of holder.itemView

            holder.btnRemoveFromFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myRemoveFavProduct.deleteFavOnClick(products.get(position));
                }
            });



        }

    }
    @Override
    public int getItemCount() {
        return 30;
    }

}


