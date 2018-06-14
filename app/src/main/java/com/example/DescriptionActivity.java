package com.example;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.json.Book;
import com.squareup.picasso.Picasso;
import java.io.IOException;

public class DescriptionActivity extends AppCompatActivity implements View.OnClickListener {
    Book book;

    TextView title;
    TextView description;
    TextView author;
    TextView isbn;
    TextView year;
    TextView publisher;
    TextView download_link;
    TextView page;
    TextView subtitle;
    ImageView image;


    Button download;
    Button buy;
    WebView webv;

    String link;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);

        download = (Button) findViewById(R.id.download);
        buy = (Button) findViewById(R.id.buy);
        //webv = (WebView) findViewById(R.id.webView2);
        //webv = (WebView) findViewById(R.id.webView);

        download.setOnClickListener(this);
        buy.setOnClickListener(this);


        Intent intent = getIntent();
        String code = intent.getStringExtra("BOOK");


        title = (TextView) findViewById(R.id.title);
        subtitle = (TextView) findViewById(R.id.subtitle);
        description = (TextView) findViewById(R.id.description);
        author = (TextView) findViewById(R.id.author);
        isbn = (TextView) findViewById(R.id.isbn);
        year = (TextView) findViewById(R.id.year);
        publisher = (TextView) findViewById(R.id.publisher);
        page = (TextView) findViewById(R.id.page);
        //download = (TextView) findViewById(R.id.download);
        image = (ImageView) findViewById(R.id.image);
        RunAPI run = new RunAPI(code); // ici sera id2

        run.execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.description_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        // Choix des menus
        switch(item.getItemId()) {
            case R.id.library: startActivity(new Intent(this,SuggestionActivity.class)); break;
            case R.id.search: startActivity(new Intent(this,SearchActivity.class)); break;
            case R.id.share: startActivity(new Intent(this,ShareActivity.class)); break;
            case R.id.about: startActivity(new Intent(this,AboutActivity.class)); break;
            case R.id.developpers: startActivity(new Intent(this,DeveloppersActivity.class)); break;
        }

        //if(id == R.id.result)
        //    startActivity(new Intent(this, ResultActivity.class));

        //if(id == R.id.reader)
        //    startActivity(new Intent(this, ReaderActivity.class));

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        Uri uri;

        switch (v.getId()){
            case R.id.download:
                // buton download
                //webv.loadUrl(book.Download);
                /*uri = Uri.parse(book.Download);
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;

            case R.id.buy:
                // buton buy
                uri = Uri.parse(book.Download);
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;*/

                intent = new Intent(DescriptionActivity.this,DownloadActivity.class);
                intent.putExtra("TITLE",book.Title);
                intent.putExtra("URL",book.Download);
                startActivity(intent);
        }
    }


    public class RunAPI extends AsyncTask<String, Object, Book> {

        private final String id;

        public RunAPI(String id) {
            this.id = id;
        }

        @Override
        protected Book doInBackground(String... params) {
            WebAPI webBook = new WebAPI(id);
            try {
                book = webBook.getBook();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Book root) {
            super.onPostExecute(root);
            title.setText(book.Title);
            subtitle.setText(book.SubTitle);
            description.setText(book.Description);
            author.setText(book.Author);
            isbn.setText(book.ISBN);
            year.setText(book.Year);
            publisher.setText(book.Publisher);
            page.setText(book.Page);
            //download.setText(book.Download);
            String url = book.Image;
            Picasso.with(getApplicationContext()).load(url).into(image);
        }
    }
}
