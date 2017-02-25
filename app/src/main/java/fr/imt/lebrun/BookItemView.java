package fr.imt.lebrun;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import fr.imt.lebrun.model.Book;

/**
 * Created by alexa on 23/02/2017.
 */

public class BookItemView extends RelativeLayout {


    private TextView nameTextView;
    private ImageView imageView;

    public BookItemView(Context context) {
        super(context);
    }

    public BookItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BookItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        nameTextView = (TextView) findViewById(R.id.book_item_name);
        imageView = (ImageView) findViewById(R.id.book_item_image);


    }

    public void bindView(Book book) {
        this.nameTextView.setText(book.getTitle());
        Glide
                .with(getContext())
                .load(book.getCover())
                .into(imageView);
    }

}
