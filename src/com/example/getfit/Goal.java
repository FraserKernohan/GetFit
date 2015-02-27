package com.example.getfit;

import java.util.Arrays;

public class Goal {
	
	private long id;
	private String goalTitle;
	private long stepCount;
	private long stairCount;
	private String startDate;
	private String endDate;
	private String state;
	
	public long getId(){
		return this.id;
	}
	
	public void setId(long l){
		this.id = l;
	}
	
	public String getTitle(){
		return goalTitle;
	}
	
	public void setTitle(String s){
		this.goalTitle = s;
	}
	
	public long getStepCount(){
		return stepCount;
	}
	
	public void setStepCount(long l){
		this.stepCount = l;
	}
	
	public boolean updateStepCount(long l){
		if((this.stepCount-l) > 0 ){
			this.stepCount -= l;
			return false;
		} else {
			this.stepCount = 0;
			return true;
		}
	}
	
	public long getStairCount(){
		return stairCount;
	}
	
	public void setStairCount(long l){
		this.stairCount = l;
	}
	
	public boolean updateStairCount(long l){
		if((this.stairCount-l) > 0){
			this.stairCount -= l;
			return false;
		} else {
			return true;
		}
	}
	
	public String getStartDate(){
		return startDate;
	}
	
	public void setStartDate(String s){
		this.startDate = s;
	}
	
	public String getEndDate(){
		return endDate;
	}
	
	public void setEndDate(String s){
		this.endDate = s;
	}
	
	public String getState(){
		return this.state;
	}
	
	public void setState(String in){
		this.state = in;
	}
}
