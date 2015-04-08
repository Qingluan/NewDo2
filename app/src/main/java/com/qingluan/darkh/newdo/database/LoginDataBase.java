package com.qingluan.darkh.newdo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by darkh on 2/14/15.
 */
public class LoginDataBase extends SQLiteOpenHelper {
    private Context context;
    private static String DatabaseName = "acount.db";
    private  String ARG_ID ="id";
    private static String TableName = "LoginAccount";
    public SQLiteDatabase localdb = null;
    public final  String CreateTableString = "create table " + TableName +
            "("+
            "id integer primary key autoincrement ,"+
            "name text  ,"+
            "password text ,"+
            "rank int default 1 "+
            ")";
    public LoginDataBase(Context context){
        super(context,DatabaseName,null,2);
        this.context = context;
    }
    public LoginDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        localdb = sqLiteDatabase;
        localdb.execSQL(CreateTableString);

    }



    private Cursor query(String tableName){
        SQLiteDatabase db = getReadableDatabase();
        return db.query(tableName,null,null,null,null,null,null,null);
    }

    public String search( String queryArg,String columnName,String resultType){
        localdb = getReadableDatabase();
        Cursor c = this.query(TableName);
        c.moveToFirst();
        if (c.getCount() == 0){
            c.close();
            localdb.close();
            return null;
        }

        if (c.getString(c.getColumnIndex(columnName)).equals(queryArg)){
            String value = c.getString(c.getColumnIndex(resultType));
            c.close();
            this.localdb.close();

            Log.d("value", "found : " + value);
            return value;
        }

        String value = null;
        while (c.moveToNext()){

            if (c.getString(c.getColumnIndex(columnName)).equals(queryArg)){
                value = c.getString(c.getColumnIndex(resultType));
                c.close();
                this.localdb.close();

            }
        }

        return value;


    }

    public String search( int queryArg,String resultType){
        Cursor c = this.query(TableName);

        if (c.getCount() == 0){
            c.close();
//            this.public_db.close();
            return null;

        }
        c.moveToFirst();

        if (c.getInt(c.getColumnIndex(ARG_ID)) == queryArg){
            String value = c.getString(c.getColumnIndex(resultType));
//            this.public_db.close();
            return value;
        }

        String value = null;
        while (c.moveToNext()){

            if (c.getInt(c.getColumnIndex(ARG_ID)) == queryArg ){
                value = c.getString(c.getColumnIndex(resultType));
                c.close();
//                this.public_db.close();
                return value;
            }
        }

        return value;


    }


    public String searchById(int id,String resultType){
//        Log.d("MainActivity",this.search(2,"id","content"));
        return this.search(id,resultType);
    }

    public int getId(String arg,String queryType){
        return Integer.valueOf( this.search(arg,queryType,DbHelper.ARG_ID) );
    }

    public int getCount(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = this.query(TableName);
        int size = c.getCount();
        c.close();
        db.close();
        return  size;
    }


    public void UpdateById (String obj_id ,String typeString  ,String value){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("update "+TableName+" set "+typeString+" =     '"+value+"' where id = "+obj_id+"; ");
        //int result = db.execSQL(TBL_NAME_MISSION, values, "mission    _object_id='"+obj_id+"'", null);
        db.close();
        //return result;
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }

    public boolean insert(ContentValues values) {
        SQLiteDatabase db = getWritableDatabase();
        if (db.insertOrThrow(TableName,null,values) != -1 ){
            return  true;
        }
        return  false;
    }
}
