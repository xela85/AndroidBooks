package fr.imt.lebrun.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by alexa on 23/02/2017.
 */

public class Book implements Parcelable {

    private String isbn;
    private String title;
    private String cover;
    private int price;
    private List<String> synopsis;

    public Book(String isbn, String title, String cover, int price, List<String> synopsis) {
        this.isbn = isbn;
        this.title = title;
        this.cover = cover;
        this.price = price;
        this.synopsis = synopsis;
    }

    protected Book(Parcel in) {
        isbn = in.readString();
        title = in.readString();
        cover = in.readString();
        price = in.readInt();
        synopsis = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(isbn);
        dest.writeString(title);
        dest.writeString(cover);
        dest.writeInt(price);
        dest.writeStringList(synopsis);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<String> getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(List<String> synopsis) {
        this.synopsis = synopsis;
    }
}
