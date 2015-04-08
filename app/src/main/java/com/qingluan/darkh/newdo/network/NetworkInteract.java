package com.qingluan.darkh.newdo.network;

import android.util.Log;

import com.qingluan.darkh.newdo.tools.JsonTools;


/**
 * Created by darkh on 11/9/14.
 */
public class NetworkInteract {
    String tag = NetworkHandler.class.getName();
    private JsonTools jsonTools;
    public NetworkInteract(){
        jsonTools = new JsonTools();

    }

    public String RegisterRespond(String device_id){
        jsonTools.addData("des","register");
        JsonTools sub_obj  = new JsonTools();
        sub_obj.addData("id",device_id);
        sub_obj.addData("type","device");
        jsonTools.addData("device",sub_obj.getJsonObj());
        return jsonTools.toString();
    }

    public String ListRespond(String target ,String[] files){
        JsonTools jsonTools = new JsonTools();
        String files_string_ = "";
        for (String file : files){
            files_string_ += file +" ";
        }
        Log.d(tag, files_string_);
        jsonTools.addData("des","list_respond");
        jsonTools.addData("files",files_string_);
        jsonTools.addData("target",target);
        return jsonTools.toString();
    }



}
