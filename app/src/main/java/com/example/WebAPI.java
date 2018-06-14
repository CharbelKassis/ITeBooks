package com.example;

import com.example.json.Book;
import com.example.json.Search;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Dorin on 7 April.
 */

public class WebAPI {
    public String url;
    //DBHelper dbh;

    // public WebAPI(Context context){
    public WebAPI(String id){
        url = "http://it-ebooks-api.info/v1/book/" + id; // url du json
        //dbh = new DBHelper(context);
    }

    public WebAPI(String id, int n) {
        url= "http://it-ebooks-api.info/v1/search/" +id;
        //http://it-ebooks-api.info/v1/book/3095231871
    }

    public Book getBook() throws IOException{
        // recuperer le contenu html a partir d'une url
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        String json = response.body().string();

        // parser le contenu json a partir de la String json
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<Book> jsonAdapter = moshi.adapter(Book.class);
        Book book = jsonAdapter.fromJson(json);

        return book;
    }

    public Search getSearch() throws  IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        String json = response.body().string();

        // parser le contenu json a partir de la String json
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<Search> jsonAdapter = moshi.adapter(Search.class);
        Search root = jsonAdapter.fromJson(json);

        return root;
    }
}
