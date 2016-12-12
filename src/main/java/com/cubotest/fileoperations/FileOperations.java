package com.cubotest.fileoperations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileOperations 
{
    
	public List<String> readFile(String path) throws Exception{

		List<String> stringList = new ArrayList<String>();

		BufferedReader br = new BufferedReader(new FileReader(path));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		    	stringList.add(line);
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }

		    return stringList;
		} finally {
		    br.close();
		}
	}

}
