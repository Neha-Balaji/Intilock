package com.example.intilock;



import android.net.*;
import android.net.wifi.WifiManager;
import android.app.IntentService;
import com.google.firebase.database.*;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;



public class NetworkCheck extends IntentService {


    public String s_gateway;
    WifiManager wifii;
    DhcpInfo d;
    DatabaseReference dbr;


    public NetworkCheck()
    {
        super("NetworkCheck");
    }
    @Override
    protected void onHandleIntent(Intent workIntent) {
        wifii = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        d = wifii.getDhcpInfo();
        s_gateway = String.valueOf(intToIp(d.gateway));
        dbr = FirebaseDatabase.getInstance().getReference();

        String dataString = workIntent.getDataString();


        //Extract the data…
        //final String lockid = bundle.getString("lockid");
        //final String username = bundle.getString("username");
        final String lockid= (String) workIntent.getExtras().get("lockid");
        final String username= (String) workIntent.getExtras().get("username");


        dbr.child(lockid).child("Users").child(username).child("IP").setValue(s_gateway);

    }
    public String intToIp(int i) {

        return ( i & 0xFF)+ "." +
                ((i >> 8 ) & 0xFF) + "." +
                ((i >> 16 ) & 0xFF) + "." +
                ((i >> 24 ) & 0xFF );
    }

}


