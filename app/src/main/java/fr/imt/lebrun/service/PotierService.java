package fr.imt.lebrun.service;

import java.util.List;

import fr.imt.lebrun.model.Book;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by alexa on 23/02/2017.
 */

public interface PotierService {

    @GET("books")
    Call<List<Book>> listBooks();

}
