package at.ac.tuwien.digital_preservation_2018.group2_4.fair.dmpgeneratorFile;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import at.ac.tuwien.digital_preservation_2018.group2_4.fair.dmpgenerator.model.DMP;
import at.ac.tuwien.digital_preservation_2018.group2_4.fair.dmpgenerator.orcid.OrcidController;

@RestController
@RequestMapping("/dmpfile")
public class DMPFileController {


	// defs
	private static final Logger log = LoggerFactory.getLogger(OrcidController.class.getCanonicalName());

	@RequestMapping(value="/getDMP", method=RequestMethod.POST, consumes = "text/plain")
	public String profile(@RequestBody String data) {

		ObjectMapper mapper = new ObjectMapper();
		DMP dmp = null;
		try{
			dmp = mapper.readValue(data, DMP.class);
		} catch(Exception e){
			log.error("Error while mapping the dmp to JSON",e);
		}
		
		return DMPFileGenerator.generate(dmp);
	}
	
}
