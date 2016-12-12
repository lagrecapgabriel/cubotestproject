package com.cubotest.race;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

import java.sql.Time;

import org.junit.Test;
import static org.junit.Assert.*;

public class PilotTest{

	@Test
	public void completedLaps(){
		List<Lap> completedLapsList = new ArrayList<Lap>();
		Lap lap = new Lap(1, 2, new Time(20,20,20), new Time(20,20,20));
		completedLapsList.add(lap);
		assertEquals(1, completedLapsList.size());

	}

	@Test
	public void raceTime(){
		List<Lap> completedLapsList = new ArrayList<Lap>();
		Lap lap = new Lap(1, 2, new Time(20,20,20), new Time(20,20,20));
		completedLapsList.add(lap);

		long raceTotalTime = 0;
		for(Lap lapAux : completedLapsList){
			raceTotalTime+=lapAux.getTime().getTime();
		}

		Time rTT = new Time(raceTotalTime);
		assertEquals(new Time(20,20,20), rTT);
	}

	@Test
	public void averageSpeed(){
		List<Lap> completedLapsList = new ArrayList<Lap>();
		Lap lap = new Lap(1, 2, new Time(20,20,20), new Time(20,20,20));
		completedLapsList.add(lap);
		assertEquals((double) 2.0, completedLapsList.stream().mapToDouble(l -> l.getSpeed()).average().getAsDouble(), 0);
	}

	@Test
	public void fastestLap(){
		List<Lap> completedLapsList = new ArrayList<Lap>();
		Lap lap = new Lap(1, 2, new Time(20,20,20), new Time(20,20,20));
		completedLapsList.add(lap);
		assertEquals(new Time(20,20,20),completedLapsList.stream().map(l -> l.getTime()).min(Time::compareTo).get());
	}
	

}