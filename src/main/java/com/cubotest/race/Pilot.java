package com.cubotest.race;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.OptionalDouble;

import java.sql.Time;

public class Pilot implements Comparable<Pilot>{

	private String number;
	private String name;
	private List<Lap> completedLapsList;

	public Pilot(){
		
	}

	public Pilot(String number, String name){
		this.number = number;
		this.name = name;
		completedLapsList = new ArrayList<Lap>();
	}

	public String getNumber(){
		return number;
	}

	public void setNumber(String number){
		this.number = number;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public List<Lap> getLaps(){
		return completedLapsList;
	}

	public void setLaps(List<Lap> completedLapsList){
		this.completedLapsList = completedLapsList;
	}

	public int completedLaps(){
		return completedLapsList.size();
	}

	public Time raceTime(){
		long raceTotalTime = 0;
		for(Lap lap : completedLapsList){
			raceTotalTime+=lap.getTime().getTime();
		}

		Time rTT = new Time(raceTotalTime);
		rTT.setHours(0);
		return rTT;
	}

	public OptionalDouble averageSpeed(){
		return completedLapsList.stream().mapToDouble(l -> l.getSpeed()).average();
	}

	public Time fastestLap(){
		return completedLapsList.stream().map(l -> l.getTime()).min(Time::compareTo).get();
	}

	@Override
	public boolean equals(Object object)
	{
	    boolean isEqual= false;

	    if (object != null && object instanceof Pilot)
	    {
	        isEqual = (this.number.equals(((Pilot) object).number));
	    }

	    return isEqual;
	}

	@Override
	public int compareTo(Pilot p){
		if(this.raceTime() == null || p.raceTime() == null){
			return 0;
		}
    	return this.raceTime().compareTo(p.raceTime());
	}

}