package com.example.getfit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

@SuppressLint("UseSparseArrays")
public class GoalDatabaseManager {

  // Database fields
  private SQLiteDatabase database;
  private DatabaseHelper dbHelper;
  private String[] allColumns = { DatabaseHelper.COLUMN_ID,
      DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_STEPS,
      DatabaseHelper.COLUMN_STAIRS, DatabaseHelper.COLUMN_START,
      DatabaseHelper.COLUMN_END};
  
  private static GoalDatabaseManager gds;
  private HashMap<Long, Goal> goals;

  public static synchronized GoalDatabaseManager getInstance(Context context) {
	  if(gds == null){
		  gds = new GoalDatabaseManager(context);
	  } 
		  return gds;
  }

  private GoalDatabaseManager(Context context){
	  dbHelper = new DatabaseHelper(context);
	  goals = new HashMap<Long, Goal>();
	  open();
	  Thread thread = new Thread()
      {
          @Override
          public void run() {
        	  restoreMap();
          }
      };
      thread.start();
  }

  public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
    dbHelper.close();
  }

  public Goal createGoal(String name, long step, long stair, String startDate, String endDate) throws ParseException {
    ContentValues values = new ContentValues();
    values.put(DatabaseHelper.COLUMN_NAME, name);
    values.put(DatabaseHelper.COLUMN_STEPS, step);
    values.put(DatabaseHelper.COLUMN_STAIRS, stair);
    values.put(DatabaseHelper.COLUMN_START, startDate);
    values.put(DatabaseHelper.COLUMN_END, endDate);
    
    long insertId = database.insert(DatabaseHelper.TABLE_GOALS, null,
        values);
    Cursor cursor = database.query(DatabaseHelper.TABLE_GOALS,
        allColumns, DatabaseHelper.COLUMN_ID + " = " + insertId, null,
        null, null, null, null);
    
    cursor.moveToFirst();
    Goal newGoal = cursorToGoal(cursor);
    goals.put(insertId, newGoal);
    cursor.close();
    return newGoal;
  }

  public void deleteGoal(Goal goal) {
    long id = goal.getId();
    System.out.println("Comment deleted with id: " + id);
    database.delete(DatabaseHelper.TABLE_GOALS, DatabaseHelper.COLUMN_ID
        + " = " + id, null);
  }

  public Map<Long,Goal> getAllGoals() {
    return goals;
  }
  
  public Long getMostRecentId(){
	  Cursor cursor = database.query(DatabaseHelper.TABLE_GOALS,
		        allColumns, null, null, null, null, null, null);
	  cursor.moveToLast();
	  Goal goal = cursorToGoal(cursor);
	  return goal.getId();
  }
  
  public ArrayList<Goal> getListValues(){
	  return new ArrayList<Goal>(goals.values());
  }
  
  private void restoreMap(){
	  Cursor cursor = database.query(DatabaseHelper.TABLE_GOALS,
		        allColumns, null, null, null, null, null, null);
	  cursor.moveToFirst();
	  while(!cursor.isAfterLast()){
		  Goal g = cursorToGoal(cursor);
		  goals.put(g.getId(), g);
		  cursor.moveToNext();
	  }
	  cursor.close();
  }
  
  public Goal getGoalFromId(int position){
	  Cursor cursor = database.query(DatabaseHelper.TABLE_GOALS,
		        allColumns, null, null, null, null, null, null);
	  cursor.moveToPosition(position);
	  Goal temp = cursorToGoal(cursor);
	  cursor.close();
	  return temp;
  }
  
  public boolean checkDates(String start, String end) throws ParseException{
	  SimpleDateFormat temp = new SimpleDateFormat("dd/mm/yyyy");
	  //If the start date is before the current date or the end date is before the current date then we dont want to make that goal
	  if(temp.parse(start).after(temp.parse(end))){
		  return false;
	  } else {
		  return true;
	  }
	  
  }
  
  private Goal cursorToGoal(Cursor cursor) {
    Goal goal = new Goal();
    
    goal.setId(cursor.getLong(0));
    goal.setTitle(cursor.getString(1));
    goal.setStepCount(cursor.getLong(2));
    goal.setStairCount(cursor.getLong(3));
    goal.setStartDate(cursor.getString(4));
    goal.setEndDate(cursor.getString(5));
    
    return goal;
  }
} 
