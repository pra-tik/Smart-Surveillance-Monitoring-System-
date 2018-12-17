package com.smartsurveillance.kakashi.smartsurveillance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;



public class MainActivity extends AppCompatActivity {


    public Firebase mRef;
    public TextView textView;

    public Button button;
    public Button button2;
    public String intr="Click on 'Stream Live Video' to watch live streaming";
    public String capturing= "Capturing Footage, you can check this footage by click on View all footages ";
    public String nointr="No Intruder Spotted, Your System is Safe";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);
        Log.v("k","k");
        mRef = new Firebase(  "https://iotlab-c4bd1.firebaseio.com/SmartSurv/pir");



        Intent intent = new Intent(this, TimeService.class);
        startService(intent);

        Button button = (Button)findViewById(R.id.butt);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Dropscreen.class));
            }
        });

        Button button2 = (Button)findViewById(R.id.streamId);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Stream.class));
            }
        });

        mRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {

                String val = dataSnapshot.getValue(String.class);

                TextView textView = (TextView)findViewById(R.id.sText);
                if(val.equals("1")) {
                    textView.setText(capturing);
                }
                else if(val.equals("2"))
                {
                    textView.setText(intr);
                }
                else {
                    textView.setText(nointr);
                }



            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });





    }
}
