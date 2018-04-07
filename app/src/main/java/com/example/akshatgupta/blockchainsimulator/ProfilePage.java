package com.example.akshatgupta.blockchainsimulator;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;

import static java.security.MessageDigest.getInstance;


public class ProfilePage extends AppCompatActivity implements LocationListener {
    TextView tv1,tv2;
    Context mContext;
    //GoogleMap googleMap;
    LocationManager locationManager;
    String locationProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        String password,email;
        String algorithm="SHA-256";
        long minTime=2000L;
        float minDist=10;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        Intent intent = getIntent();
        email = intent.getStringExtra("hashmail");
        password = intent.getStringExtra("hashpass");
        this.tv1=(TextView)findViewById(R.id.tv1);
        this.tv2=(TextView)findViewById(R.id.tv2);
        try{

            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(password.getBytes());

            byte byteData[]=md.digest();
            //convert the byte to hex format method 1
            StringBuffer sb=new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            tv1.setText("Password: "+sb.toString());


            //convert the byte to hex format method 2 different logic
            md.update(email.getBytes());
            byte bd[] = md.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i=0;i<byteData.length;i++) {
                hexString.append(Integer.toString((bd[i] & 0xff) + 0x100, 16).substring(1));
            }
            tv2.setText("Email: "+hexString.toString());
        }
        catch(Exception e){}

    }


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
