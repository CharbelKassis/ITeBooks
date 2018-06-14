package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//import com.example.R;

/**
 * Created by Dorin on 5 April.
 */
public class ShareActivity extends AppCompatActivity implements View.OnClickListener {

    Button b_share;
    EditText subject, text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        b_share = (Button)findViewById(R.id.send);
        subject = (EditText)findViewById(R.id.subject);
        text = (EditText)findViewById(R.id.text);

        b_share.setOnClickListener(this);
    }

    public void onClick(View v){
        String sub = subject.getText().toString();
        String txt = text.getText().toString();
        Intent share_i = new Intent(Intent.ACTION_SEND);
        share_i.putExtra(Intent.EXTRA_SUBJECT, txt);
        share_i.setType("text/plain");
        try {
            startActivity(Intent.createChooser(share_i, getResources().getText(R.string.send)));
        } catch(Exception e) {
            Log.e("SHARE", e.getMessage());
        }
    }
}
