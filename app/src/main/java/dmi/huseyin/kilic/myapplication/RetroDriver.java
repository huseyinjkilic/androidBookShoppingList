package dmi.huseyin.kilic.myapplication;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kilic on 1.10.2017.
 */


/*
* TODO: Yapılacaklar
 * Login sırasında kullanıcı adı ve şifre buraya yollanacak.
 * Parametre olarak Book listesi geri döndürülecek.
 * Eğer hatalıysa null döndürülecek.
 *
 * Book list'ten ayrıntıya geçerkende bu class constructor'ı çağrılacak.
 * Bu class singleton yapilacak. Böylece username & password sürekli istenmeyecek.
*
* */


public class RetroDriver {

    private String userName;
    private String password;
    private final String BASE_URL = "http://assignment.gae.golgek.mobi/api/";
    private OkHttpClient okHttpClientLogin;
    private Retrofit retrofit;

    private static RetroDriver instance = null;
    protected RetroDriver() {
    }
    public static RetroDriver getInstance() {
        if(instance == null) {
            instance = new RetroDriver();
        }
        return instance;
    }

    public OkHttpClient getOkHttpClientLogin() {

        OkHttpClient okHttpClientLogin = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                        Credentials.basic(userName, password));
                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        }).build();

        return okHttpClientLogin;
    }

    public void setPassword(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Retrofit getRetrofit(OkHttpClient okHttpClientLogin) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClientLogin)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

}
