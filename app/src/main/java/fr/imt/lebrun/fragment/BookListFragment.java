package fr.imt.lebrun.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fr.imt.lebrun.listeners.BookClickListener;
import fr.imt.lebrun.BookRecyclerAdapter;
import fr.imt.lebrun.R;
import fr.imt.lebrun.listeners.BookSelectListener;
import fr.imt.lebrun.model.Book;
import fr.imt.lebrun.service.PotierService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alexa on 23/02/2017.
 */

public class BookListFragment extends Fragment implements BookClickListener {


    private Context context;

    private BookSelectListener bookSelect;

    private List<Book> books;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        this.bookSelect = (BookSelectListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_list, container, false);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://henri-potier.xebia.fr")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PotierService service = retrofit.create(PotierService.class);

        books = new ArrayList<>();
        final BookRecyclerAdapter adapter = new BookRecyclerAdapter(LayoutInflater.from(context), this, books);

        service.listBooks().enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                books.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        RecyclerView recycler = (RecyclerView) view.findViewById(R.id.book_recycler);
        recycler.setLayoutManager(new LinearLayoutManager(context));
        recycler.setAdapter(adapter);
        return view;
    }

    @Override
    public void onBookClick(int position) {
        bookSelect.onBookSelect(this.books.get(position));
    }
}
