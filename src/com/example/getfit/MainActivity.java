package com.example.getfit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void sendMessage(View view){

	}
	
	public void onAddClick(View view){
		
		// Locate the button in activity_main.xml
		Button add = (Button)findViewById(R.id.buttonAdd);
 
		// Capture button clicks
		add.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
 
				// Start NewActivity.class
				Intent myIntent = new Intent(MainActivity.this,
						AddActivity.class);
				startActivity(myIntent);
			}
		});
	}

	public void onViewClick(View view){
		Button v = (Button)findViewById(R.id.buttonView);
		 
		// Capture button clicks
		v.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
 
				// Start NewActivity.class
				Intent myIntent = new Intent(MainActivity.this,
						ViewActivity.class);
				startActivity(myIntent);
			}
		});
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
