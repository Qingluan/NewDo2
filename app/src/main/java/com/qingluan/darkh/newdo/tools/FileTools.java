package com.qingluan.darkh.newdo.tools;

import android.content.Context;
import android.os.Environment;


import com.qingluan.darkh.newdo.Services.BroadcastNotifer;
import com.qingluan.darkh.newdo.arguments.ARGUMENTS;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by darkh on 11/9/14.
 */
public class FileTools {
    private String filename;
    private BroadcastNotifer notifer;
    public String root_path;

    public FileTools(Context context,String file_name){
        this.filename = file_name;
        notifer = new BroadcastNotifer(context);
        //get sd path
        root_path  = ARGUMENTS.FILE_ROOT_PATH;
    }

    private boolean CheckSDCard(){
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            notifer.sendIntent(ARGUMENTS.INFO_ACTION,"SD is ok");
            File file = new File(root_path);
            if (file.exists()){
                return true;
            }else{
                notifer.sendIntent(ARGUMENTS.INFO_ACTION,"no such directory ,creating ...");
                if (file.mkdirs()){
                    notifer.sendIntent(ARGUMENTS.INFO_ACTION,"file directory is ok");
                    return true;
                }else{
                    notifer.sendIntent(ARGUMENTS.INFO_ACTION,"Creating file is failure");
                    return false;
                }


            }


        }else{
            notifer.sendIntent(ARGUMENTS.INFO_ACTION,"no sd card found");
            return false;
        }
    }

    public FileTools(Context context){
        notifer = new BroadcastNotifer(context);
        //get sd  path
        root_path  = ARGUMENTS.FILE_ROOT_PATH;
    }
    public void saveFile(byte[] bytes ,String filename){
        this.filename = filename;
        if (CheckSDCard()){
            File file = new File(root_path+"/"+this.filename);
            DataOutputStream dopt;
            notifer.sendIntent(ARGUMENTS.INFO_ACTION,"Starting downloading  "+filename);
            try{
                dopt = new DataOutputStream(new FileOutputStream(file));
                dopt.write(bytes);
                notifer.sendIntent(ARGUMENTS.INFO_ACTION,"file "+ filename+"downloaded");
            }catch (IOException e ){
                e.printStackTrace();
            }
        }
    }

    public String[] listFiles(){
        if(CheckSDCard()){
            File file = new File(this.root_path);
            return file.list();
        }
        return null;
    }
}
