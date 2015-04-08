package com.qingluan.darkh.newdo.Services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.qingluan.darkh.newdo.arguments.ARGUMENTS;


/**
 * Created by darkh on 11/6/14.
 */
public class RecivedBroadcastReceiver extends BroadcastReceiver {

    String tag = getClass().getName();


    private ReceivedListener listener;

    public  RecivedBroadcastReceiver(){

    }

    public RecivedBroadcastReceiver(ReceivedListener listener){
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle info_bundle =  intent.getExtras();

        try{
            String info = info_bundle.getString(ARGUMENTS.BUNDLE_JSON_KEY);
            this.listener.recieved(info);

        }catch (NullPointerException e){
            Log.d(tag, " listener is null");
            e.printStackTrace();
        }
    }

    public void setReceivedListener(ReceivedListener listener){
        this.listener = listener;
    }

    public  interface  ReceivedListener{
        public void recieved(String info);
    }
}
