package dmi.huseyin.kilic.myapplication.RestDriverClasses;

import java.util.List;

import dmi.huseyin.kilic.myapplication.DataClasses.Book;
import dmi.huseyin.kilic.myapplication.DataClasses.BookDetails;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ApiService {
    @GET("v1/secure/items")
    Call<List<Book>> getAllBooks();

    @GET("v1/secure/items/{id}")
    Call<BookDetails> getBookDetails(@Path("id") int id);
}
