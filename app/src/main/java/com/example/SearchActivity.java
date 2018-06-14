package com.example;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

//import com.example.R;

public class SearchActivity extends AppCompatActivity {

    //private Spinner spinner;
    private SearchView searchView;
    private SearchView.OnQueryTextListener queryListener;
    private View.OnClickListener clickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);

        // Menu de selection de type Spinner pour choisir entre "Keyword" et "Google"
        //spinner = (Spinner)findViewById(R.id.spinner);
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.options,android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner.setAdapter(adapter);

        // La boite de recherche
        searchView = (SearchView)findViewById(R.id.searchView);

        // Generer les listeners et l'ajouter au searchView
        generateQueryListener();
        generateClickListener();
        searchView.setOnQueryTextListener(queryListener);
        searchView.setOnClickListener(clickListener);
    }

    private void generateClickListener() {

        clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SearchView searchView = (SearchView) v;
                searchView.setIconified(!searchView.isIconified());
            }
        };
    }

    private void generateQueryListener() {

        queryListener = new SearchView.OnQueryTextListener() {

            private Intent intent;
            private String searchValue;

            @Override
            public boolean onQueryTextSubmit(String query) {

                searchValue = query;
                keywordSearch();
                return true;
            }

            // Renvois le text de la recherche vers l'activite ResultActivity
            private void keywordSearch() {

                intent = new Intent(SearchActivity.this,ResultActivity.class);
                intent.putExtra("SEARCH", searchValue);
                startActivity(intent);

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        };

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);
        return true;
    }

    // buttons menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {

            case R.id.library: startActivity(new Intent(this,SuggestionActivity.class));break;
            case R.id.about: startActivity(new Intent(this,AboutActivity.class));break;
            case R.id.developpers: startActivity(new Intent(this,DeveloppersActivity.class)); break;

        }

        return super.onOptionsItemSelected(item);
    }
}
