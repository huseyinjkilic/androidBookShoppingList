package dmi.huseyin.kilic.myapplication;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;

public class BookDetailsActivity extends AppCompatActivity {

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        final ImageView bookImage = (ImageView) findViewById(R.id.book_image);
        final TextView bookTitle = (TextView) findViewById(R.id.book_title);
        final TextView bookAuthor = (TextView) findViewById(R.id.book_author);
        final TextView bookPrice = (TextView) findViewById(R.id.book_price);

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String detailBookLink =  extras.getString("bookLink");
            id = Integer.valueOf(detailBookLink.substring(detailBookLink.lastIndexOf('/') + 1, detailBookLink.length()));
        }

        RetroDriver retroDriver = RetroDriver.getInstance();
        OkHttpClient okHttpClientLogin = retroDriver.getOkHttpClientLogin();
        Retrofit retrofit = retroDriver.getRetrofit(okHttpClientLogin);
        BookDetails bookDetails = null;

        ApiService service = retrofit.create(ApiService.class);
        Call<BookDetails> call = service.getBookDetails(id);

        try {
            bookDetails = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(bookDetails.getAuthor() != null) bookAuthor.setText(bookDetails.getAuthor());
        if(bookDetails.getPrice() != null) bookPrice.setText(bookDetails.getPrice().toString());
        if(bookDetails.getTitle() != null) bookTitle.setText(bookDetails.getTitle());


        if(bookDetails.getImage() == "200" )Picasso.with(getApplicationContext()).load(bookDetails.getImage()).into(bookImage);
        else Picasso.with(getApplicationContext()).load("https://www.pyramidions.com/img/android_banner1.png");

    }
}
