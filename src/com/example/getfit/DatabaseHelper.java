package com.example.getfit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

  public static final String TABLE_GOALS = "goals";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_NAME = "name";
  public static final String COLUMN_STEPS = "steps";
  public static final String COLUMN_STAIRS = "stairs";
  public static final String COLUMN_START = "start";
  public static final String COLUMN_END = "end";
  

  private static final String DATABASE_NAME = "goals.db";
  private static final int DATABASE_VERSION = 1;

  // Database creation sql statement
  private static final String DATABASE_CREATE = "create table "
      + TABLE_GOALS   + "(" 
      + COLUMN_ID     + " integer primary key autoincrement, " 
      + COLUMN_NAME   + " text not null, " 
      + COLUMN_STEPS  + " INTEGER, " 
      + COLUMN_STAIRS + " INTEGER, " 
      + COLUMN_START  + " text not null, "
      + COLUMN_END    + " text not null);";

  public DatabaseHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(DATABASE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(DatabaseHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_GOALS);
    onCreate(db);
  }

} 