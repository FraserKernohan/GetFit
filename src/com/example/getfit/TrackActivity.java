package com.example.getfit;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class TrackActivity extends Activity implements View.OnClickListener {

	private GoalDatabaseManager gds;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_track);
		gds = GoalDatabaseManager.getInstance(this.getApplicationContext());
		Bundle b = this.getIntent().getExtras();
		setTextField(b);
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
	
	private void setTextField(Bundle b){
		TextView title = (TextView)findViewById(R.id.textViewShowName);
		TextView step = (TextView)findViewById(R.id.textViewShowSteps);
		TextView stair = (TextView)findViewById(R.id.textViewShowStairs);
		TextView start = (TextView)findViewById(R.id.textViewShowStart);
		TextView end = (TextView)findViewById(R.id.textViewShowEnd);
		
		title.setText(b.getString("Title"));
		step.setText(String.valueOf(b.getLong("Step")));
		stair.setText(String.valueOf(b.getLong("Stair")));
		start.setText(b.getString("Start"));
		end.setText(b.getString("End"));
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
