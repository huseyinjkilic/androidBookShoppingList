package dmi.huseyin.kilic.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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



        mButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {

                RetroDriver retroDriver = RetroDriver.getInstance();
                    retroDriver.setPassword(mUserName.getText().toString(), mPasswordView.getText().toString());
                OkHttpClient okHttpClientLogin = retroDriver.getOkHttpClientLogin();
                Retrofit retrofit = retroDriver.getRetrofit(okHttpClientLogin);
                ApiService service = retrofit.create(ApiService.class);
                Call<List<Book>> call = service.getAllBooks();
                call.enqueue(new Callback<List<Book>>() {

                        @Override
                        public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                            try {
                                List<Book> bookData = response.body();
                                ArrayList<Book> arrayListBookData = new ArrayList<>();

                                //TODO: Buna daha guzel bir cozum bulunacak..
                                for (Book books: bookData
                                     ) {
                                    arrayListBookData.add(books);
                                }

                                if(bookData.size() == 0) {
                                    Toast.makeText(getApplicationContext(), "Error with authentication", Toast.LENGTH_LONG).show();
                                } else {
                                    Intent listInent = new Intent(getApplicationContext(), MainActivity.class);
                                    listInent.putExtra("bookData", arrayListBookData);
                                    startActivity(listInent);
                                }
                            } catch (Exception e) {
                                Log.d("onResponse", "There is an error");
                                e.printStackTrace();
                            }
                        }
                        @Override
                        public void onFailure(Call<List<Book>> call, Throwable t) {
                            Log.d("onFailure", t.toString());
                        }
                    });
                }
        });
    }


}

