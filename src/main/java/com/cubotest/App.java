package com.cubotest;

import java.util.List;

import com.cubotest.fileoperations.FileOperations;
import com.cubotest.race.Race;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	String path = args[0];
    	FileOperations fileOp = new FileOperations();

    	List<String> testList = fileOp.readFile(path);

    	Race amilRace = new Race();

    	amilRace.buildRace(testList);
    	amilRace.raceResults();
    }
}
