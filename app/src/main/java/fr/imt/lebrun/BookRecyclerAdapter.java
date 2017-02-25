package fr.imt.lebrun;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fr.imt.lebrun.listeners.BookClickListener;
import fr.imt.lebrun.model.Book;

/**
 * Created by alexa on 23/02/2017.
 */

public class BookRecyclerAdapter extends RecyclerView.Adapter<BookRecyclerAdapter.BookViewHolder> {

    private LayoutInflater inflater;

    private final List<Book> books;

    private final BookClickListener clickListener;

    public BookRecyclerAdapter(LayoutInflater inflater, BookClickListener listener, List<Book> books) {
        this.inflater = inflater;
        this.books = books;
        this.clickListener = listener;
    }


    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_view_item_book, parent, false);
        return new BookViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        BookItemView itemView = (BookItemView) holder.itemView;
        itemView.bindView(books.get(position));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    static class BookViewHolder extends RecyclerView.ViewHolder {
        BookViewHolder(View itemView, final BookClickListener listener) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onBookClick(getAdapterPosition());
                }
            });
        }
    }

}
