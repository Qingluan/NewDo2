package com.qingluan.darkh.newdo.Services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by darkh on 3/15/15.
 */
public class BroadReciver extends BroadcastReceiver{
    private BroadcastReceiverListener listener;
    private  Context context;

    public BroadReciver(Context context, BroadcastReceiverListener listener){
        this.listener = listener;
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String getString = intent.getStringExtra("DATA");

        listener.onString(getString);
    }

    public void  setListener(BroadcastReceiverListener receiverListener){
        this.listener = receiverListener;
    }

    public  interface BroadcastReceiverListener{
        public void onString(String data);
    }

}
