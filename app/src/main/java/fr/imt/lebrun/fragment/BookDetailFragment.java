package fr.imt.lebrun.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import fr.imt.lebrun.R;
import fr.imt.lebrun.model.Book;

/**
 * Created by alexa on 23/02/2017.
 */

public class BookDetailFragment extends Fragment {


    private Context context;


    public static BookDetailFragment newInstance(Book book) {
        BookDetailFragment bookDetailFragment = new BookDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("book", book);
        bookDetailFragment.setArguments(args);
        return bookDetailFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_detail, container, false);
        Book book = getArguments().getParcelable("book");

        TextView titleTextView = (TextView) view.findViewById(R.id.book_title);
        TextView synopsisTextView = (TextView) view.findViewById(R.id.book_text);
        ImageView coverImageView = (ImageView) view.findViewById(R.id.book_image);


        titleTextView.setText(book.getTitle());
        synopsisTextView.setText(book.getSynopsis().get(0));

        Glide
                .with(context)
                .load(book.getCover())
                .into(coverImageView);
        return view;
    }

}
