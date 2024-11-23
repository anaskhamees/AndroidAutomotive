    package com.example.circularlistview;
    import java.io.BufferedInputStream;


    import android.os.Bundle;
    import android.util.Log;

    import android.widget.Toast;

    import androidx.appcompat.app.AppCompatActivity;

    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStream;
    import java.io.InputStreamReader;
    import java.net.URL;
    import java.util.ArrayList;


    import javax.net.ssl.HttpsURLConnection;

    public class MainActivity extends AppCompatActivity {

        ArrayList<com.example.circularlistview.Pojo> productList;
        RecyclerView recyclerView;
        MyAdapter adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            recyclerView = findViewById(R.id.recView);
            recyclerView.setHasFixedSize(true);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(RecyclerView.HORIZONTAL);
            recyclerView.setLayoutManager(layoutManager);

            productList = new ArrayList<>();
            adapter = new MyAdapter(this, productList);

            loadProductsAndUpdateUI();
        }

        private void loadProductsAndUpdateUI() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    loadProducts();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (!productList.isEmpty()) {

                                recyclerView.setAdapter(adapter);

                            } else {
                                Toast.makeText(MainActivity.this, "No products found.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }).start();
        }

        private void loadProducts() {
            HttpHandler sh = new HttpHandler();
            String url = "https://dummyjson.com/products";
            String jsonStr = sh.makeServiceCall(url);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray products = jsonObj.getJSONArray("products");
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);

                        String title = c.getString("title");
                        String brand = c.getString("brand");
                        String description = c.getString("description");
                        String price = c.getString("price"); // Ensure this is available
                        String rating = c.getString("rating");
                        String thumbnail = c.getString("thumbnail");

                        Pojo product = new Pojo();
                        product.setTitle(title);
                        product.setBrand(brand);
                        product.setDescription(description);
                        product.setPrice(price);
                        product.setRating(rating);
                        product.setThumb(thumbnail);
                        productList.add(product);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        public class HttpHandler {
            private final String TAG = HttpHandler.class.getSimpleName();

            public HttpHandler() {
            }

            public String makeServiceCall(String reqUrl) {
                String response = null;
                try {
                    URL url = new URL(reqUrl);
                    HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    InputStream in = new BufferedInputStream(conn.getInputStream());
                    response = convertStreamToString(in);
                } catch (Exception e) {
                    Log.e(TAG, "Exception: " + e.getMessage());
                }
                return response;
            }
        }

//

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
