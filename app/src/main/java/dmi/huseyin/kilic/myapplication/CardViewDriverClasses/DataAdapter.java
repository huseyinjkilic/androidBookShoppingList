package dmi.huseyin.kilic.myapplication.CardViewDriverClasses;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import dmi.huseyin.kilic.myapplication.DataClasses.Book;
import dmi.huseyin.kilic.myapplication.R;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private List<Book> books;
    private Context context;

    public DataAdapter(List<Book> bookData, Context applicationContext) {
        this.books = bookData;
        this.context = applicationContext;
    }
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, final int i) {

        viewHolder.book_name.setText(books.get(i).getTitle());
        viewHolder.book_price.setText(books.get(i).getPrice().toString());

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
                                                       @Override
                                                       public void onClick(View v) {
                                                           Intent intentWithBookLink = new Intent(context, dmi.huseyin.kilic.myapplication.ActivityClasses.BookDetailsActivity.class);
                                                           intentWithBookLink.putExtra("bookLink", books.get(i).getLink());
                                                           context.startActivity(intentWithBookLink);
                                                       }
                                                   });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView book_name, book_price;
        private LinearLayout linearLayout;
        public ViewHolder(View view) {
            super(view);
            book_name = (TextView)view.findViewById(R.id.book_name);
            book_price = (TextView)view.findViewById(R.id.book_price);
            linearLayout = (LinearLayout) view.findViewById(R.id.regularLayout);
        }
    }

}