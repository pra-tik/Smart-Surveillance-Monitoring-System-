package com.smartsurveillance.kakashi.smartsurveillance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.session.AppKeyPair;

public class Dropscreen extends AppCompatActivity {
    public WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dropscreen);
        webView = (WebView)findViewById(R.id.wb);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.dropbox.com/sh/rpeu1nnjm7j7ymr/AACX-h9jC9GDL9mQ7HNqr2fIa?dl=0");
    }


}
