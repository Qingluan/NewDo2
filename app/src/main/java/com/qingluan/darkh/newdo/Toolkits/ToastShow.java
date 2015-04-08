package com.qingluan.darkh.newdo.Toolkits;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by darkh on 4/8/15.
 */
public class ToastShow {
    private Context context;
    private Toast toast = null;
    public ToastShow(Context context) {
        this.context = context;
    }
    public void show(String text) {
        if(toast == null)
        {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        }
        else {
            toast.setText(text);
        }
        toast.show();
    }
}
