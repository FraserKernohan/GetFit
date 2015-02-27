package com.example.getfit;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

@SuppressLint("UseSparseArrays")
public class AddActivity extends Activity {

	private EditText title;
	private EditText steps;
	private EditText stairs;
	private DatePicker s;
	private DatePicker e;
	
	private GoalDatabaseManager gds;
	private Map<Long, Goal> GoalMap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_);
		setTitle("Get Fit");
		gds = GoalDatabaseManager.getInstance(this.getApplicationContext());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add, menu);
		return true;
	}

	public void addActivity(View view) throws ParseException{
		 title = (EditText) findViewById(R.id.editTextName);
		 steps = (EditText) findViewById(R.id.editTextNumber);
		 stairs = (EditText) findViewById(R.id.editTextStairs);
		 
		 s = (DatePicker) findViewById(R.id.datePickerStart);
		 int startDay = s.getDayOfMonth();
		 int startMonth = s.getMonth()+1;
		 int startYear = s.getYear();
		 
		 e = (DatePicker) findViewById(R.id.datePickerEnd);
		 int endDay = e.getDayOfMonth();
		 int endMonth = e.getMonth()+1;
		 int endYear = e.getYear();
		 
		 String name = title.getText().toString();
		 long step = Long.parseLong(steps.getText().toString());
		 long stair = Long.parseLong(stairs.getText().toString());
		 String startDate = (startDay + "/" + startMonth + "/" + startYear);
		 String endDate = (endDay + "/" + endMonth + "/" + endYear);

		// if (gds.checkDates(startDate, endDate)){
			 gds.createGoal(name, step, stair, startDate, endDate);
		 //	}

		 finish();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
