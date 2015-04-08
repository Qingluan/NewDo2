package com.qingluan.darkh.newdo.Services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.qingluan.darkh.newdo.arguments.ARGUMENTS;
import com.qingluan.darkh.newdo.network.NetworkHandler;


/**
 * Created by darkh on 11/6/14.
 * this is for running in background
 */
public class RecivedIntentService extends IntentService {
    /*
        this arguments for control signal
     */
    public  static final int SIGNAL_CONNECT = 1;

    /*
        follow is normal arguments
     */
    private static final String tag = RecivedIntentService.class.getName();
    private BroadcastNotifer notifer;
    private int now_signal = 0;
    private NetworkHandler client;
    private String url = null;

    public RecivedIntentService(){
        super("RecivedIntentService");

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(tag, "service init start ok");
        notifer = new BroadcastNotifer(getApplicationContext());

        Bundle info_bundle = intent.getExtras();
        this.now_signal = info_bundle.getInt(ARGUMENTS.SIGNAL_KEY);
        switch (this.now_signal){
            case RecivedIntentService.SIGNAL_CONNECT :
                Log.d(tag, "start connecting websocket");
                this.url = info_bundle.getString(ARGUMENTS.KEY_DOWNLOAD_URL);
                String filename = info_bundle.getString(ARGUMENTS.KEY_DOWNLOAD_FILE_NAME);

                client = new NetworkHandler(getApplicationContext(), this.url,filename);
                client.setAsyncHttpHandler(new NetworkHandler.AsyncHttpListener() {
                    @Override
                    public void afterSave(String file_path) {
                        /*
                            do some
                         */
                        notifer.sendIntent(ARGUMENTS.INFO_ACTION,file_path);

                    }
                });

            break;

            default:
                Log.d(tag, "Never see this");
                break;
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(tag, "destroy");
    }
}
