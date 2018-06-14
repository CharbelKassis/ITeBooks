package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

//import com.example.*;

/**
 * Created by Dorin on 5 April.
 */
public class Temp extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.description_temp)
            startActivity(new Intent(this, DescriptionActivity.class));

        if(id == R.id.library_temp)
            startActivity(new Intent(this, SuggestionActivity.class));

        if(id == R.id.reader_temp)
            startActivity(new Intent(this, ReaderActivity.class));

        if(id == R.id.result_temp)
            startActivity(new Intent(this, ResultActivity.class));

        if(id == R.id.search_temp)
            startActivity(new Intent(this, SearchActivity.class));

        return super.onOptionsItemSelected(item);
    }

    public void tmpShow (View view) {

        String button_text;
        button_text = ((Button) view).getText().toString();
        if (button_text.equals("Description"))
        {
            Intent intent = new Intent (this, DescriptionActivity.class);
            startActivity(intent);
        }

        else if (button_text.equals("Library"))
        {
            Intent intent = new Intent (this, SuggestionActivity.class);
            startActivity(intent);
        }

        else if (button_text.equals("Reader"))
        {
            Intent intent = new Intent (this, ReaderActivity.class);
            startActivity(intent);
        }

        else if (button_text.equals("Result"))
        {
            Intent intent = new Intent (this, ResultActivity.class);
            startActivity(intent);
        }

        else if (button_text.equals("Search"))
        {
            Intent intent = new Intent (this, SearchActivity.class);
            startActivity(intent);
        }
    }
}
