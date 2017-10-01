package dmi.huseyin.kilic.myapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class BookResponse {

    @SerializedName("books")
    @Expose
    private ArrayList<Book> books = new ArrayList<>();

    /**
     * @return The books
     */
    public ArrayList<Book> getContacts() {
        return books;
    }

    /**
     * @param books The contacts
     */
    public void setContacts(ArrayList<Book> books) {
        this.books = books;
    }
}
