package com.qingluan.darkh.newdo.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by darkh on 11/7/14.
 */
public class LogTools {
    public static void ToastLog(Context context,String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
