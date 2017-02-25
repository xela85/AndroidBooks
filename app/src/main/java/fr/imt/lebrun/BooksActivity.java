package fr.imt.lebrun;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import fr.imt.lebrun.fragment.BookDetailFragment;
import fr.imt.lebrun.fragment.BookListFragment;
import fr.imt.lebrun.listeners.BookClickListener;
import fr.imt.lebrun.listeners.BookSelectListener;
import fr.imt.lebrun.model.Book;

public class BooksActivity extends AppCompatActivity implements BookSelectListener {


    private boolean twoPanels;

    private List<Book> books;

    private Book book;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        this.fragmentManager = getSupportFragmentManager();

        if (findViewById(R.id.dual_pane) != null) {
            this.twoPanels = true;
            System.out.println(twoPanels);
        }

        System.out.println("saved instance state: " + savedInstanceState);

        if(savedInstanceState == null) {
            this.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.master, new BookListFragment(), BookListFragment.class.getSimpleName())
                    .commit();
        } else if(savedInstanceState.getParcelable("book") != null) {
            book = savedInstanceState.getParcelable("book");
            if(twoPanels) {
                System.out.println("two panels");
                this.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.master, new BookListFragment(), BookListFragment.class.getSimpleName())
                        .replace(R.id.detail, BookDetailFragment.newInstance(book), BookDetailFragment.class.getSimpleName())
                        .commit();
            } else {
                System.out.println("one panel");
                this.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.master, BookDetailFragment.newInstance(book), BookDetailFragment.class.getSimpleName())
                        .commit();
            }
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("book", book);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBookSelect(Book b) {
        this.book = b;
        if(twoPanels) {
            fragmentManager.beginTransaction()
                    .replace(R.id.detail, BookDetailFragment.newInstance(b), BookDetailFragment.class.getSimpleName())
                    .addToBackStack(BookDetailFragment.class.getSimpleName())
                    .commit();
        } else {
            fragmentManager.beginTransaction()
                    .replace(R.id.master, BookDetailFragment.newInstance(b), BookDetailFragment.class.getSimpleName())
                    .addToBackStack(BookDetailFragment.class.getSimpleName())
                    .commit();
        }
    }
}
