package com.cubotest.race;

import java.sql.Time;

public class Lap{

	private int number;
	private float speed;
	private Time hour;
	private Time time;

	public Lap(int number, float speed, Time hour, Time time){
		this.number = number;
		this.speed = speed;
		this.hour = hour;
		this.time = time;
	}

	public int getNumber(){
		return number;
	}

	public void setNumber(int number){
		this.number=number;
	}

	public float getSpeed(){
		return speed;
	}

	public void setSpeed(float speed){
		this.speed=speed;
	}

	public Time getHour(){
		return hour;
	}

	public void setHour(Time hour){
		this.hour=hour;
	}

	public Time getTime(){
		return time;
	}

	public void setTime(Time time){
		this.time=time;
	}
}