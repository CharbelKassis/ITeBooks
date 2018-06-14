package com.example;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadActivity extends AppCompatActivity {

    WebView web;
    String title;
    String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        Intent intent = getIntent();
        title = intent.getStringExtra("TITLE");
        URL = intent.getStringExtra("URL");
        web = (WebView) findViewById(R.id.webView);
        web.loadUrl(URL);
        web.getSettings().setJavaScriptEnabled(true);

        }
}
