package at.ac.tuwien.digital_preservation_2018.group2_4.fair.dmpgeneratorFile;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import at.ac.tuwien.digital_preservation_2018.group2_4.fair.dmpgenerator.model.DMP;
import at.ac.tuwien.digital_preservation_2018.group2_4.fair.dmpgenerator.orcid.OrcidController;
import at.ac.tuwien.digital_preservation_2018.group2_4.fair.dmpgenerator.orcid.OrcidRESTApi;
import at.ac.tuwien.digital_preservation_2018.group2_4.fair.dmpgenerator.orcid.OrcidController.OrcidRecordResponse;

@RestController
@RequestMapping("/dmpfile")
public class DMPFileController {


	// defs
	private static final Logger log = LoggerFactory.getLogger(OrcidController.class.getCanonicalName());

	@RequestMapping(value="/getDMP/{data}", method=RequestMethod.POST)
	public DMPFileResponse profile(@PathVariable("data") String data) {

		ObjectMapper mapper = new ObjectMapper();
		DMP dmp = null;
		try{
			dmp = mapper.readValue(data, DMP.class);
		} catch(Exception e){
			e.printStackTrace();
		}
		
		//TODO: map dmp to output structure (aus angabe)
		
		return new DMPFileResponse();
	}
	
	public static class DMPFileResponse {
		//TODO output structure
	}
}
