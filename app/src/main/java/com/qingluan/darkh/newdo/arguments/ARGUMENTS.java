package com.qingluan.darkh.newdo.arguments;

import android.os.Environment;

/**
 * Created by darkh on 11/6/14.
 */
public class ARGUMENTS {
    public static final String DEVICE_ID = "1234567";
    public static final String BUNDLE_SEND_KEY = "bundle_key";
    public static final String BUNDLE_JSON_KEY = "json_key";

    public static final String GET_BROADCAST_ACTION ="com.qingluan.android.GetInfo.BROADCAST";
    public static final String SEND_BROADCAST_ACTION = "com.qingluan.android.SendInfo.BROADCAST";
    public static final String START_WEBSOCKET_ACTION = "com.qingluan.android.StartConnect.BROADCAST";
    public static final String INFO_ACTION = "com.qingluan.android.Information.BROADCAST";
    public static final String VIDEO_CONTROL_ACTION  = "com.qingluan.android.VideoControl.BROADCAST";
    public static final String VIDEO_ACTION = "com.qingluan.android.Video.BROADCAST";
    public static final String HANDLE_ACTION = "com.qingluan.android.Video.Handle";

    public static final String URL_KEY = "url";
    public static final String IP = "182.92.160.84:9180";
    public static final String WS_CONNECT_URL = "ws://"+ IP +"/websocket";
    public static final String HTTP_IP = "http://"+IP;


    public static final String SIGNAL_KEY = "signal";

    public static final String SEND_INFO_KEY = "send_info";
    public static final String FILE_ROOT_PATH = Environment.getExternalStoragePublicDirectory("TvControl").getPath()+"/video";

    public static final String KEY_DOWNLOAD_URL = "download_url";
    public static final String KEY_DOWNLOAD_FILE_NAME = "download_filename";

    // routing
    public static final String JSON_DOWNLOAD = "download";
    public static final String SEND_MESSAGE = "send message";
}
