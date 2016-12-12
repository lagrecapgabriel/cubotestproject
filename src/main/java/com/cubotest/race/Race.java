package com.cubotest.race;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.sql.Time;

import java.text.SimpleDateFormat;
import java.text.DecimalFormat;

public class Race{

	private List<Lap> lapList;
	List<Pilot> pilotsList;
	private SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm:ss.SSS");
	private SimpleDateFormat lapFormat = new SimpleDateFormat("mm:ss.SSS");

	public Race(){
		lapList = new ArrayList<Lap>();
		pilotsList = new ArrayList<Pilot>();
	}

	public void buildRace(List<String> raceTextFile) throws Exception{

		raceTextFile.remove(0);

		for(String lapString : raceTextFile){
			List<String> lapInfo = new ArrayList<String>();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < lapString.length(); i++){

				if(lapString.charAt(i)==' ' && lapString.charAt(i+1)==' '){
					if(lapString.charAt(i-1)!=' '){
						lapInfo.add(sb.toString().trim());
						sb = new StringBuilder();
					}
				}else if(i==lapString.length()-1 && lapString.charAt(i)!=' '){
					sb.append(lapString.charAt(i));
					lapInfo.add(sb.toString().trim());
				}
				else{
					sb.append(lapString.charAt(i));
				}
			}

			Lap lap = new Lap(Integer.valueOf(lapInfo.get(2)), 
				Float.valueOf(lapInfo.get(4).replace(',','.')), 
				new Time(hourFormat.parse(lapInfo.get(0)).getTime()), 
				new Time(lapFormat.parse(lapInfo.get(3)).getTime()));

			String[] pilotParts = lapInfo.get(1).split(" – ");
	
			if(pilotsList.contains(new Pilot(pilotParts[0],pilotParts[1]))){
				pilotsList.get(pilotsList.indexOf(new Pilot(pilotParts[0],pilotParts[1]))).getLaps().add(lap);
			}else{
				Pilot pilot = new Pilot(pilotParts[0],pilotParts[1]);
				pilot.getLaps().add(lap);
				pilotsList.add(pilot);
			}
		}
	}

	public void raceResults(){
		System.out.println();
		System.out.println("RESULTADOS DA CORRIDA");
		System.out.println();
		List<Time> fastestLaps = new ArrayList<Time>();
		int pos = 1;
		Pilot firstPilot = new Pilot();
		for(int i=4;i>=0;i--){
			final int aux = i;
			List<Pilot> lapPilotList = 
			pilotsList
			.stream()
			.filter(p -> p.completedLaps() == aux)
			.collect(Collectors.toList());
			Collections.sort(lapPilotList);
			if(i==4){
				firstPilot = lapPilotList.get(0);
			}
			for (Pilot pilot: lapPilotList)
			{
				Time distTime = new Time(pilot.raceTime().getTime() - firstPilot.raceTime().getTime());
				fastestLaps.add(pilot.fastestLap());
				System.out.println("POSICAO #"+pos);
			    System.out.println("PILOTO - "+pilot.getNumber()+" - "+pilot.getName());
			    System.out.println("TOTAL DE VOLTAS - "+pilot.completedLaps());
			    System.out.println("TEMPO TOTAL - "+lapFormat.format(pilot.raceTime()));
			    System.out.println("MELHOR VOLTA - "+lapFormat.format(pilot.fastestLap()));
			    System.out.println("VELOCIDADE MEDIA - "+ new DecimalFormat("##,###").format(pilot.averageSpeed().getAsDouble()));
			    System.out.println("DISTANCIA PRIMEIRO COLOCADO - + "+lapFormat.format(distTime));
			    System.out.println();
			    pos++;
			}
		}
		Time raceFastestLap =  fastestLaps.stream().map(l -> l).min(Time::compareTo).get();
		System.out.println("MELHOR VOLTA DA CORRIDA - "+lapFormat.format(raceFastestLap));
	}
	
}