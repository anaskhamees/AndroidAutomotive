package com.example.productscard;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    TextView title, price, brand, description;
    ImageButton nextBtn, prevBtn;
    ImageView image;
    JSONArray arrProducts;
    int arrProductsCounter = 0;
    RatingBar ratingBar;
    String currentThumbnailUrl;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.TitleValuID);
        price = findViewById(R.id.priceValuID);
        brand = findViewById(R.id.brandValuID);
        description = findViewById(R.id.descriptionValuID);
        nextBtn = findViewById(R.id.rightArrowID);
        prevBtn = findViewById(R.id.leftArrowID);
        image = findViewById(R.id.imageID);
        ratingBar = findViewById(R.id.ratingID);
        ratingBar.setEnabled(false);

        // Handler for initial product load
        Handler initialHandler = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message msg) {
                updateUIWithProduct((JSONObject) msg.obj);
            }
        };

        // Fetch products in a separate AsyncTask
        new FetchProductsTask(initialHandler).execute("https://dummyjson.com/products");

        // Next button click listener
        nextBtn.setOnClickListener(view -> {
            if (arrProducts != null && arrProductsCounter < arrProducts.length() - 1) {
                arrProductsCounter++;
                loadProduct(arrProductsCounter);
            }
        });

        // Previous button click listener
        prevBtn.setOnClickListener(view -> {
            if (arrProducts != null && arrProductsCounter > 0) {
                arrProductsCounter--;
                loadProduct(arrProductsCounter);
            }
        });
    }

    // Update UI with product details
    private void updateUIWithProduct(JSONObject product) {
        try {
            title.setText(product.getString("title"));
            price.setText(product.getString("price"));
            brand.setText(product.getString("brand"));
            description.setText(product.getString("description"));
            currentThumbnailUrl = product.getString("thumbnail");

            double rating = product.getDouble("rating");
            ratingBar.setRating((float) Math.min(rating, 5)); // Cap rating at 5

            // Download image asynchronously
            new DownloadImageTask().execute(currentThumbnailUrl);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Load product based on the current counter
    private void loadProduct(int index) {
        try {
            JSONObject product = arrProducts.getJSONObject(index);
            updateUIWithProduct(product);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // AsyncTask to fetch products
    private class FetchProductsTask extends AsyncTask<String, Void, String> {
        private Handler handler;

        public FetchProductsTask(Handler handler) {
            this.handler = handler;
        }

        @Override
        protected String doInBackground(String... urls) {
            return makeServiceCall(urls[0]);
        }

        @Override
        protected void onPostExecute(String jsonStr) {
            try {
                JSONObject bigRootJsonObj = new JSONObject(jsonStr);
                arrProducts = bigRootJsonObj.getJSONArray("products");
                // Load the first product
                if (arrProducts.length() > 0) {
                    Message msg = handler.obtainMessage();
                    msg.obj = arrProducts.getJSONObject(0);
                    handler.sendMessage(msg);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    // AsyncTask to download an image
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            return downloadImage(urls[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                image.setImageBitmap(bitmap);
            } else {
                Log.e("DownloadImageTask", "Failed to download image");
            }
        }
    }

    // Helper method to download an image
    private Bitmap downloadImage(String url) {
        try {
            URL imageUrl = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) imageUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            return BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Network request helper
    private String makeServiceCall(String reqUrl) {
        String response = null;
        try {
            URL url = new URL(reqUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
        } catch (IOException e) {
            Log.e("HttpHandler", "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e("HttpHandler", "Exception: " + e.getMessage());
        }
        return response;
    }

    // Helper to convert InputStream to String
    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
