package com.smartsurveillance.kakashi.smartsurveillance;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Stream extends AppCompatActivity {

    public WebView liveVid;
    public Firebase mRef;
    public Firebase mRef2;
    public Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream);

        Firebase.setAndroidContext(this);
        Log.v("k","k");
        mRef = new Firebase(  "https://iotlab-c4bd1.firebaseio.com/SmartSurv/url");

        mRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @SuppressLint("SetJavaScriptEnabled")
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {

                String url = dataSnapshot.getValue(String.class);
                liveVid = (WebView)findViewById(R.id.liveVideo);
                liveVid.getSettings().setJavaScriptEnabled(true);
                liveVid.setWebViewClient(new WebViewClient());
                liveVid.loadUrl(url);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        Button button = (Button)findViewById(R.id.stopButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef3 = database.getReference("SmartSurv/Stream");

                myRef3.setValue("0");

                startActivity(new Intent(Stream.this,MainActivity.class));
            }
        });


    }



}
