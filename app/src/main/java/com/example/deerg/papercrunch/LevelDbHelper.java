package com.example.deerg.papercrunch;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class LevelDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="level_db";
    public static final int DATABASE_VERSION= 1;

    public static final String CREATE_TABLE="create_table" + LevelContract.LevelEntry.TABLE_NAME+"("+
            LevelContract.LevelEntry.LEVEL_ID+"number"+ LevelContract.LevelEntry.SUBLEVEL1+ LevelContract.LevelEntry.SUBLEVEL2+
            LevelContract.LevelEntry.SUBLEVEL3+"text);";


    public static final String DROP_TABLE ="drop table if exists"+ LevelContract.LevelEntry.TABLE_NAME;

    public LevelDbHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        Log.d("Database Operations","Database created.. ");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.d("Databaase Op","Table created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void addLevel(int id,String sublevel,String sublevel2,SQLiteDatabase datavase){
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("sublevel",sublevel);
        contentValues.put("sublevel2",sublevel2);

        datavase.insert(LevelContract.LevelEntry.TABLE_NAME,null,contentValues);

    }

    public Cursor readLevel(SQLiteDatabase database)
    {
        String[]  projections = {"id", "sublevel",
                "sublevel2"};
        Cursor cursor = (Cursor) database.query(LevelContract.LevelEntry.TABLE_NAME,projections,null,null,null,null,null);
        return cursor;
    }


}

