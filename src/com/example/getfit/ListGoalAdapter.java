package com.example.getfit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListGoalAdapter extends ArrayAdapter<Goal>{
	
	private Context context;
	private ArrayList<Goal> allGoals;
	private SimpleDateFormat sd;
	private Calendar c;
	private Date start;
	private Date end;
	private LayoutInflater mInflater;
	private boolean mNotifyOnChange = true;

	public ListGoalAdapter(Context context, ArrayList<Goal> Goals) {
	    super(context, R.layout.my_row_layout);
	    this.context = context;
	    this.allGoals = Goals;
	    this.mInflater = LayoutInflater.from(context);
		this.sd = new SimpleDateFormat("dd/mm/yyyy");
		this.c = Calendar.getInstance();
		}

	@Override
	public int getCount() {
	    return allGoals.size();
	}

	@Override
	public Goal getItem(int position) {
	    return allGoals.get(position);
	}

	@Override
	public long getItemId(int position) {
	    return position;
	}

	@Override
	public int getPosition(Goal item) {
	    return allGoals.indexOf(item);
	}

	@Override
	public int getViewTypeCount() {
	    return 1; //Number of types + 1 !!!!!!!!
	}

	@Override
	public int getItemViewType(int position) {
	    return 1;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent){
	    final ViewHolder holder;
	    int type = getItemViewType(position);
	    if (convertView == null) {
	        holder = new ViewHolder();
	        switch (type) {
	        case 1:
	            convertView = mInflater.inflate(R.layout.my_row_layout,parent, false);
	            holder.name = (TextView) convertView.findViewById(R.id.textView_name);
	            holder.date = (TextView) convertView.findViewById(R.id.textView_date);
	            break;
	        }
	        convertView.setTag(holder);
	    } else {
	        holder = (ViewHolder) convertView.getTag();
	    }
	    Goal temp = allGoals.get(position);
		try {
			start = sd.parse(temp.getStartDate());
			end = sd.parse(temp.getEndDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date time = c.getTime();
		 if(start.after(time) && end.after(start)){
			 temp.setState("ACTIVE");
		 } else if (start.before(c.getTime())) {
			 temp.setState("PENDING");
		 } else if (time.after(end)){
			 temp.setState("OVERDUE");
		 }
		 
	    holder.name.setText(allGoals.get(position).getTitle() + ":  " +  allGoals.get(position).getState());
	    holder.date.setText(allGoals.get(position).getEndDate());
	    holder.pos = position;
	    return convertView;
	}

	@Override
	public void notifyDataSetChanged() {
	    super.notifyDataSetChanged();
	    mNotifyOnChange = true;
	}

	public void setNotifyOnChange(boolean notifyOnChange) {
	    mNotifyOnChange = notifyOnChange;
	}


	//---------------static views for each row-----------//
	     static class ViewHolder {

	         TextView name;
	         TextView date;
	         int pos; //to store the position of the item within the list
	         long id;
	     }
	}


