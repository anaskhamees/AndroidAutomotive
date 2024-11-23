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

import java.util.List;
import model.Product;
class AllProductsAdaptor extends RecyclerView.Adapter<AllProductsAdaptor.ViewHolder> {


    private final Context context;
    private static final String TAG = "AllProductsAdaptor";
    private List<Product> products;
    private AddFavProduct myAddFavProduct;

    public AllProductsAdaptor(Context context, List<Product> products, AddFavProduct InterfaceRef)
    {

        this.context = context;
        this.products = products;
        this.myAddFavProduct = InterfaceRef;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView editTitle;
        public TextView editPrice;
        public TextView editBrand;
        public TextView editDescription;
        public RatingBar ratingBar;
        public ImageView img;
        public Button btnAddToFav;

        public ConstraintLayout constraintLayout;
        public View layout;

        @SuppressLint("WrongViewCast")
        public ViewHolder(View v) {
            super(v);

            layout = v;
            editTitle = v.findViewById(R.id.titleID);
            editPrice = v.findViewById(R.id.priceID);
            editBrand = v.findViewById(R.id.brandID);
            editDescription = v.findViewById(R.id.descriptionID);
            btnAddToFav = v.findViewById(R.id.btnAddFavID);
            img = v.findViewById(R.id.imageID);
            ratingBar = v.findViewById(R.id.startsID);
            ratingBar.setEnabled(false);
            constraintLayout = v.findViewById(R.id.rowContentAllProductID);


        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.row_content_allproducts, recyclerView, false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "onCreateViewHolder AllproductsAdaptor");
        return vh;
    }

    // Update the contents of the view (invoked by the layout manager)

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(ViewHolder holder,int position) {

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
                .into(holder.img);

        holder.btnAddToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAddFavProduct.addFavOnClick(products.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 30;
    }

}


