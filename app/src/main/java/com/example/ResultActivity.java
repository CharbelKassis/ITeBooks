package com.example;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.json.Search;
import com.example.json.Book;
import com.squareup.picasso.Picasso;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ResultActivity extends AppCompatActivity {

    ListView list;
    Search root;
    List<Book> books;
    MyAdapter adapter;
    TextView description;
    TextView title;
    TextView author;
    ImageView image;
    String search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        books = new ArrayList<>();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);

        Intent intent = getIntent();
        search = intent.getStringExtra("SEARCH");
        new RunAPIResult(search).execute();
    }

    public class RunAPIResult extends AsyncTask<String,Object,Search> {

        String id;

        public RunAPIResult(String id) {
            this.id = id;
        }

        @Override
        protected Search doInBackground(String... params) {

            WebAPI web = new WebAPI(id,0);


            try {
                root = web.getSearch();

                for(int i=0;i<root.Books.size();i++) {

                    WebAPI webBook = new WebAPI(root.Books.get(i).ID);
                    Book book = webBook.getBook();
                    books.add(book);

                }
            }
            catch(IOException e) {}

            return root;
        }

        @Override
        protected void onPostExecute(Search rootList) {
            super.onPostExecute(rootList);
            list = (ListView) findViewById(R.id.listView);
            adapter = new MyAdapter();
            list.setAdapter(adapter);
            BookListener listener = new BookListener();
            list.setOnItemClickListener(listener);
        }
    }

    private class MyAdapter extends BaseAdapter {

        LayoutInflater inflater;

        public MyAdapter() {
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {

            try {
                return root.Books.size();
            }
            catch(NullPointerException e) {
                TextView text = (TextView) findViewById(R.id.notFound);
                text.setText("Sorry. No results with keywords "+ "\"" + search + "\"");
                return 0;
            }
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = convertView;

            if (view == null) {
                view = inflater.inflate(R.layout.resultats, parent, false);
            }

            title = (TextView) view.findViewById(R.id.t);
            title.setText(root.Books.get(position).Title);
            author = (TextView) view.findViewById(R.id.authorView);
            author.setText(books.get(position).Author);
            image = (ImageView) view.findViewById(R.id.image);
            String url = root.Books.get(position).Image;
            Picasso.with(getApplicationContext()).load(url).into(image);

            String id = root.Books.get(position).ID;

            return view;
        }
    }

  class BookListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            String bookID = books.get(position).ID;

            Intent intent = new Intent(ResultActivity.this,DescriptionActivity.class);
            intent.putExtra("BOOK",bookID);
            startActivity(intent);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.result_menu, menu);
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
            case R.id.about: startActivity(new Intent(this,AboutActivity.class)); break;
            case R.id.developpers: startActivity(new Intent(this,DeveloppersActivity.class)); break;
        }

        //if(id == R.id.result)
        //    startActivity(new Intent(this, ResultActivity.class));

        //if(id == R.id.reader)
        //    startActivity(new Intent(this, ReaderActivity.class));

        return super.onOptionsItemSelected(item);
    }
}
