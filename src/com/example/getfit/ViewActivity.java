package com.example.getfit;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ViewActivity extends Activity {

	private GoalDatabaseManager gds;
	private ListView listview;
	private ListGoalAdapter lga;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view);
		gds = GoalDatabaseManager.getInstance(this.getApplicationContext());
		lga = new ListGoalAdapter(this, gds.getListValues());

		listview = (ListView) findViewById(R.id.listView1);
		listview.setAdapter(lga);
		registerClick();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view, menu);
		return true;
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

	public void registerClick(){
		listview.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> adapter, View v, int position,
					long l) {
				Intent intent = new Intent();
				Goal temp = gds.getGoalFromId(position);
				intent.setClass(ViewActivity.this, TrackActivity.class);
				intent.putExtras(makeBundle(temp));
				startActivity(intent);
			}
		});
	}
	
	private Bundle makeBundle(Goal temp){
		Bundle b = new Bundle();
		b.putLong("Id", temp.getId());
		b.putString("Title", temp.getTitle());
		b.putLong("Step", temp.getStepCount());
		b.putLong("Stair", temp.getStairCount());
		b.putString("Start", temp.getStartDate());
		b.putString("End", temp.getEndDate());
		return b;
	}
}	
