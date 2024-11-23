package com.example.circularlistview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private  Context context;
    private ArrayList<Pojo> values;

    public MyAdapter(MainActivity mainActivity,ArrayList<Pojo>xo) {
        values = xo;
        context= mainActivity;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.rows,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder (@NonNull ViewHolder holder, final int position)  {


        Pojo product = values.get(position);

        // Bind data to views
        holder.txtTitle.setText(product.getTitle());
        holder.txtPrice.setText(product.getPrice());
        holder.txtBrand.setText(product.getBrand());
        holder.txtDes.setText(product.getDescription());
        holder.rating.setRating(Float.parseFloat(product.getRating()));
        holder.imageView.setImageBitmap(values.get(position).getIm());
        if (product.getIm() != null) {
            holder.imageView.setImageBitmap(product.getIm());
        } else {
            // Load image asynchronously
            new ImageLoadTask(product.getThumb(), holder.imageView, product).execute();
        }
        // Set image using your existing logic
    }


    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtTitle;
        public TextView txtDes;
        public ImageView imageView;
        public ConstraintLayout constraintLayout;
        public TextView txtPrice;
        public TextView txtBrand;
        public RatingBar rating;

        public View layout;
        public ViewHolder(@NonNull View v) {
            super(v);
            layout=v;
            txtTitle=v.findViewById(R.id.titleID);
            txtDes=v.findViewById(R.id.descriptionID);
            txtPrice=v.findViewById(R.id.priceID);
            txtBrand=v.findViewById(R.id.brandID);
            rating=v.findViewById(R.id.startsID);
            imageView=v.findViewById(R.id.imageID);
            constraintLayout=v.findViewById(R.id.main);
        }
    }

    private class ImageLoadTask extends AsyncTask<String, Void, Bitmap> {
        private String url;
        private ImageView imageView;
        private Pojo product;

        public ImageLoadTask(String url, ImageView imageView, Pojo product) {
            this.url = url;
            this.imageView = imageView;
            this.product = product;
        }
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap = null;
            try {
                URL imgURL = new URL(url);
                HttpsURLConnection connection = (HttpsURLConnection) imgURL.openConnection();
                connection.setRequestMethod("GET");
                InputStream is = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(is);
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                // Update the product with the downloaded image
                product.setIm(bitmap);
                // Update the ImageView
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}






