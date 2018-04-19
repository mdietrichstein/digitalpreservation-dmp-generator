package at.ac.tuwien.digital_preservation_2018.group2_4.fair.dmpgenerator.oai_pmh;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import at.ac.tuwien.digital_preservation_2018.group2_4.fair.dmpgenerator.oai_pmh.Record.Metadata;

@RestController
@RequestMapping("/oai_pmh")
public class OAIPMHApiController {
	
	// defs
	private static final Logger log = LoggerFactory.getLogger(OAIPMHApiController.class.getCanonicalName());
	
	// dependencies
	private final OAIPMHApi oaiPMHApi;
	
	// initialization
	@Autowired
	public OAIPMHApiController(OAIPMHApi oaiPMHApi) {
		this.oaiPMHApi = oaiPMHApi;
	}
	
	// web
	@RequestMapping(value="/record", method=RequestMethod.GET)
	public OAIPMHRecordResponse getRecord(
			@RequestParam(value="identifier", required=true) String identifier,
			@RequestParam(value="type", required=true) RecordIdType type) {
		
		final String recordIdentifier;
		
		if(type == RecordIdType.doi) {
			// Convert e.g. '10.5281/zenodo.1209833' to 'oai:zenodo.org:1209833'
			String pathPart = identifier.split("/")[1];
			String idPart = pathPart.split("\\.")[1];
			recordIdentifier = String.format("oai:zenodo.org:%s", idPart);
		} else {
			recordIdentifier = identifier;
		}
		
		final Record record;
		
		try {
			record = oaiPMHApi.getRecord(recordIdentifier).execute().body();
		} catch (IOException e) {
			log.error("Error while fetching oai pmh record", e);
			throw new RuntimeException(e);
		}
		
		return convert(record);
	}
	
	// private helpers
	private static OAIPMHRecordResponse convert(Record record) {
		
		Metadata metadata = record.metadata;
		
		String githubUrl = null;
		
		if(metadata.relations != null) {
			for(String relation : metadata.relations) {
				if(StringUtils.startsWithIgnoreCase(relation, "url:https://github.com/")) {
					githubUrl = relation.substring(4);
					break;
				}
			}
		}
		
		return new OAIPMHRecordResponse(
				metadata.title, metadata.description,
				AccessRights.get(metadata.rights), 
				githubUrl,
				metadata.creators, metadata.identifiers, metadata.types);
	}
	
	// structs
	public enum RecordIdType {
		oai, doi
	}
	
	// https://wiki.surfnet.nl/display/standards/info-eu-repo/#info-eu-repo-AccessRights
	public enum AccessRights {
		closedAccess("info:eu-repo/semantics/closedAccess"),
		embargoedAccess("info:eu-repo/semantics/embargoedAccess"),
		restrictedAccess("info:eu-repo/semantics/restrictedAccess"),
		openAccess("info:eu-repo/semantics/openAccess");
		
		public final String uri;
		
		private AccessRights(String uri) {
			this.uri = uri;
		}
		
		public static AccessRights get(String uri) {
			for(AccessRights accessRights : values()) {
				if(accessRights.uri.equals(uri)) {
					return accessRights;
				}
			}
			
			return null;
		}
	}
	
	// responses
	public static class OAIPMHRecordResponse {
		public final String title;
		public final String description;
		public final AccessRights rights;
		public final String githubUrl;
		public final List<String> creators;
		public final List<String> identifiers;
		public final List<String> types;
		
		public OAIPMHRecordResponse(
				String title, String description, AccessRights rights, String githubUrl,
				List<String> creators, List<String> identifiers, List<String> types) {
			this.title = title;
			this.description = description;
			this.rights = rights;
			this.githubUrl = githubUrl;
			this.creators = creators;
			this.identifiers = identifiers;
			this.types = types;
		}
	}
}
