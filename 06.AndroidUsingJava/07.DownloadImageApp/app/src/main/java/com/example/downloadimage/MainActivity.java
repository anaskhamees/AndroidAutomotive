package com.example.downloadimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.os.AsyncTask;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;



public class MainActivity extends AppCompatActivity {
    Button btnDownload;
    EditText edtURL;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btnDownload=findViewById(R.id.downloadBtnID);
        edtURL=findViewById(R.id.urlTxtID);
        imageView=findViewById(R.id.imageID);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadTask task=new DownloadTask();
                task.execute(edtURL.getText().toString());
      ;      }
        });

    }


    private Bitmap download(String url) throws IOException {
        Bitmap bitmap;
            URL imageURL = new URL("https://w7.pngwing.com/pngs/499/159/png-transparent-ocean-seawater-wind-wave-ocean-marine-mammal-computer-wallpaper-coastal-and-oceanic-landforms.png");
            HttpsURLConnection connection = (HttpsURLConnection) imageURL.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            InputStream inputSteam = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputSteam);


        return bitmap;
    }



    class DownloadTask extends AsyncTask<String,Void, Bitmap>{
        protected Bitmap doInBackground(String... urls)
        {
            Bitmap result=null;
            try {
                result = download(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            publishProgress();
            return result;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }
    }

}
