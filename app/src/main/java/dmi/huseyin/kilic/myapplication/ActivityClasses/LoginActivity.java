package dmi.huseyin.kilic.myapplication.ActivityClasses;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dmi.huseyin.kilic.myapplication.DataClasses.BookDetails;
import dmi.huseyin.kilic.myapplication.RestDriverClasses.ApiService;
import dmi.huseyin.kilic.myapplication.DataClasses.Book;
import dmi.huseyin.kilic.myapplication.R;
import dmi.huseyin.kilic.myapplication.RestDriverClasses.RetroDriver;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class LoginActivity extends AppCompatActivity  {

    private EditText mUserName;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUserName = (EditText) findViewById(R.id.username);
        mPasswordView = (EditText) findViewById(R.id.password);
        Button mButton = (Button) findViewById(R.id.user_sign_in_button);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }



        mButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {

                    RetroDriver retroDriver = RetroDriver.getInstance();
                    retroDriver.setPassword(mUserName.getText().toString(), mPasswordView.getText().toString());
                    OkHttpClient okHttpClientLogin = retroDriver.getOkHttpClientLogin();
                    Retrofit retrofit = retroDriver.getRetrofit(okHttpClientLogin);

                    ApiService service = retrofit.create(ApiService.class);
                    Call<List<Book>> call = service.getAllBooks();

                    try {
                        List<Book> bookData = call.execute().body();
                        if(bookData !=null) {
                            ArrayList<Book> arrayListBookData = new ArrayList<>();
                            for (Book books : bookData) {
                                arrayListBookData.add(books);
                            }
                            Intent listInent = new Intent(getApplicationContext(), MainActivity.class);
                            listInent.putExtra("bookData", arrayListBookData);
                            startActivity(listInent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Authentication Error", Toast.LENGTH_LONG).show();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        });
    }
}