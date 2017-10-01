package dmi.huseyin.kilic.myapplication.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import dmi.huseyin.kilic.myapplication.DataClasses.Book;
import dmi.huseyin.kilic.myapplication.DataAdapter;
import dmi.huseyin.kilic.myapplication.R;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Book> data;
    private DataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        if (extras != null) data = (ArrayList<Book>) extras.getSerializable("bookData");
        initViews();
    }

    private void initViews(){
        recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new DataAdapter(data, getApplicationContext());
        recyclerView.setAdapter(adapter);
    }
}
