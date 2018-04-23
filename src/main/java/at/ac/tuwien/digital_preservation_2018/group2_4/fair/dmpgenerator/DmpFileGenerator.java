package at.ac.tuwien.digital_preservation_2018.group2_4.fair.dmpgenerator;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import at.ac.tuwien.digital_preservation_2018.group2_4.fair.dmpgenerator.model.*;

public class DmpFileGenerator {
	
	public void createFile(String jsondata)
	{		
		ObjectMapper mapper = new ObjectMapper();
		DMP dmp = null;
		try{
			dmp = mapper.readValue(jsondata, DMP.class);
		} catch(Exception e){
			e.printStackTrace();
		}
		
		
		//TODO: convert to machine-readable JSON
		//TODO: create file for machine-readable JSON and send to client
	}

}
