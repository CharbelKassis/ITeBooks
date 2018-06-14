package com.example;

        import android.content.Context;
        import android.content.Intent;
        import android.os.AsyncTask;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.HorizontalScrollView;
        import android.widget.ImageView;

        import com.example.json.Book;
        import com.example.json.Search;
        import com.squareup.picasso.Picasso;

        import java.io.IOException;
        import java.util.ArrayList;

//import com.example.R;

public class SuggestionActivity extends AppCompatActivity {

    ArrayList<HorizontalScrollView> scrolls;
    ArrayList<RecyclerView> lists;
    ArrayList<WebAPI> webs;
    ArrayList<Search> searchs;
    ArrayList<ArrayList<Book>> books;
    BaseAdapter myAdapter;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);

        lists = new ArrayList<>();
        searchs = new ArrayList<>();
        webs = new ArrayList<>();
        books = new ArrayList<>();

        new RunAPI().execute();

    }

    private class MyRecycleAdapter extends RecyclerView.Adapter<MyRecycleAdapter.MyViewHolder> {

        int type;

        public MyRecycleAdapter(int type) {

            this.type = type;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            private ImageView image;

            public MyViewHolder(View itemView) {
                super(itemView);
                image = (ImageView) itemView.findViewById(R.id.image);
            }
        }
        @Override
        public MyRecycleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            View contactView = inflater.inflate(R.layout.suggestion_list_layout, parent, false);
            MyViewHolder viewHolder = new MyViewHolder(contactView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(MyRecycleAdapter.MyViewHolder holder, int position) {

            ImageView imageView = holder.image;
            String url = searchs.get(type).Books.get(position).Image;
            Picasso.with(getApplicationContext()).load(url).into(imageView);
            imageView.setOnClickListener(new BookListener(type, position));
        }

        @Override
        public int getItemCount() {
            return 5;
        }
    }

    private class RunAPI extends AsyncTask<Object,Object,Search> {
        @Override
        protected Search doInBackground(Object... params) {

            webs.add(0,new WebAPI("linux",0));
            webs.add(1, new WebAPI("java", 0));


            try {

                for(WebAPI web : webs)

                    searchs.add(web.getSearch());

                for(int i=0; i<searchs.size();i++) {

                    books.add(i, new ArrayList<Book>());

                    for (int j = 0; j < searchs.get(i).Books.size(); j++)

                        books.get(i).add(j, searchs.get(i).Books.get(j));

                }

            }
            catch(IOException e) {}

            return null;
        }

        @Override
        protected void onPostExecute(Search search) {
            super.onPostExecute(search);

                lists.add((RecyclerView) findViewById(R.id.list0));
                lists.add((RecyclerView) findViewById(R.id.list1));


            for(int i=0;i<lists.size(); i++) {

                lists.get(i).setAdapter(new MyRecycleAdapter(i));
                LinearLayoutManager layoutManager  = new LinearLayoutManager(SuggestionActivity.this, LinearLayoutManager.HORIZONTAL, false);
                lists.get(i).setLayoutManager(layoutManager);
            }

        }

    }

    class BookListener implements View.OnClickListener {

        int type;
        int position;

        public BookListener(int type, int position) {

            this.type = type;
        }

        @Override
        public void onClick(View v) {

            String bookID = books.get(type).get(position).ID;

            Intent intent = new Intent(SuggestionActivity.this,DescriptionActivity.class);
            intent.putExtra("BOOK",bookID);
            startActivity(intent);

        }
    }

}

