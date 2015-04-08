package com.qingluan.darkh.newdo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by darkh on 1/29/15.
 */
public class DbHelper extends SQLiteOpenHelper {
    private static String DatabaseName = "book.db";
    private static String TableName = "Books";
    public static  String ARG_NAME = "record_name";
    public static  String ARG_CONTENT = "content";
    public static  String ARG_TIME = "time";
    public static  String ARG_ID = "id";
    public SQLiteDatabase public_db;
    Context context;
    private  final String CreateTableString = "create table " + TableName +
                                "("+
                                "id integer primary key autoincrement ,"+
                                "record_name text default Untitle ,"+
                                "time text ,"+
                                "content text "+
                                ")";

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DbHelper(Context context){
        super(context,DatabaseName,null,1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.public_db = sqLiteDatabase;
        this.public_db.execSQL(CreateTableString);
    }

    public boolean insertRecord(String title,String content){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ARG_NAME,title);
        values.put(ARG_CONTENT,content);
        values.put(ARG_TIME,System.currentTimeMillis());

        if ( db.insert(TableName,null,values) != -1) {
            return true;
        }else {
            return false;
        }

    }


    private Cursor query(String tableName){
        SQLiteDatabase db = getReadableDatabase();
        return db.query(tableName,null,null,null,null,null,null,null);
    }

    public String search( String queryArg,String columnName,String resultType) throws Exception {
        Cursor c = this.query(TableName);
        c.moveToFirst();
        if (c.getCount() == 0){
            c.close();
            public_db.close();
            return null;
        }

        if (c.getString(c.getColumnIndex(columnName)).equals(queryArg)){
            String value = c.getString(c.getColumnIndex(resultType));
            c.close();
            this.public_db.close();

            Log.d("value", "found : "+value);
            return value;
        }

        String value = null;
        while (c.moveToNext()){

            if (c.getString(c.getColumnIndex(columnName)).equals(queryArg)){
                value = c.getString(c.getColumnIndex(resultType));
                c.close();
                this.public_db.close();

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

    public List<Integer> getAllId(){
        SQLiteDatabase db  = getReadableDatabase();
        Cursor c= this.query(TableName);
        List<Integer> idS = new ArrayList<Integer>();
        c.moveToFirst();
        if (c.getCount() != 0){
            int id = c.getInt(c.getColumnIndex("id"));
            idS.add(id);
            while ( c.moveToNext()){
                int column_id = c.getColumnIndex("id");
                int ID = c.getInt(column_id);
                column_id = c.getColumnIndex(ARG_NAME);
                String value = c.getString(column_id);
                Log.d("DBDBDBDB",String.valueOf(ID)+ "|"+ value+"\n\n\n");
                Log.d("SVSVSVS",this.search(2,ARG_NAME));
                idS.add(ID);
            }
        }
        return  idS;
    }


    public String searchById(int id,String resultType){
//        Log.d("MainActivity",this.search(2,"id","content"));
        return this.search(id,resultType);
    }

    public int getId(String arg,String queryType) {
        try {
            return Integer.valueOf(this.search(arg, queryType, DbHelper.ARG_ID));

        }catch (Exception e){
            return  0;
        }
    }

    public int getCount(){
        if (context != null){
            Log.d("Test","not null for context pass to Database");
        }else{
            Log.d("Test","null for context pass to Database");
        }
        SQLiteDatabase db = this.getReadableDatabase();
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
}
