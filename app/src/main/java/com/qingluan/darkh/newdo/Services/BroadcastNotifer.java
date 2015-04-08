package com.qingluan.darkh.newdo.Services;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.qingluan.darkh.newdo.arguments.ARGUMENTS;


/**
 * Created by darkh on 11/6/14.
 */
public class BroadcastNotifer {
    private String tag = BroadcastNotifer.class.getName();
    private Context localcontext;

    /*
        this function is for bind context to use ;
     */
    public BroadcastNotifer(Context context){
        this.localcontext = context;

    }

    public BroadcastNotifer(){

    }
    /*
        @para intent is a intent which is filled in 'action' ,'category' and 'data'
     */
    public boolean sendIntent(Intent intent){
       if (this.localcontext!= null){
            this.localcontext.sendBroadcast(intent);
           return true;
       }

        Log.d(tag, "no object for 'localcontext'");
        return  false;


    }

    /*
       recommend use this function ,
       which is for a case that is direct create a intent to fill in broadcast
     */
    public  boolean sendInntent(String jsonData){
        Bundle data_bundle = new Bundle();
        data_bundle.putString(ARGUMENTS.BUNDLE_JSON_KEY,jsonData);

        Intent intent = new Intent();
        intent.setAction(ARGUMENTS.SEND_BROADCAST_ACTION);

        intent.putExtras(data_bundle);
        intent.addCategory(Intent.CATEGORY_DEFAULT);

        if (this.localcontext!= null){
            this.localcontext.sendBroadcast(intent);
            return true;
        }
        return false;
    }

    /*
       need load action and data
     */
    public boolean sendIntent(String action,String jsonData){
        if(this.localcontext == null){

            return  false;
        }
        Bundle data_bundle = new Bundle();
        data_bundle.putString(ARGUMENTS.BUNDLE_JSON_KEY,jsonData);

        Intent intent = new Intent();
        intent.setAction(action);
        intent.putExtras(data_bundle);
        this.localcontext.sendBroadcast(intent);
        Log.d(tag, "send intent to broadcast from " + this.localcontext.toString());
        return true;
    }

    public boolean sendIntentService(Context context,String info){
        Log.d(tag, "starting send info");
        Intent intent = new Intent(context,TalkService.class);
        Bundle info_bundle = new Bundle();
        info_bundle.putString(ARGUMENTS.SEND_MESSAGE,info);
        info_bundle.putInt(ARGUMENTS.SIGNAL_KEY,TalkService.SEND_INFO);
        intent.putExtras(info_bundle);
        context.startService(intent);
        return  true;

    }
}
