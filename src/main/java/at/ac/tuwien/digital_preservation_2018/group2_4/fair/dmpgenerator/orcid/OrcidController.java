package at.ac.tuwien.digital_preservation_2018.group2_4.fair.dmpgenerator.orcid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import at.ac.tuwien.digital_preservation_2018.group2_4.fair.dmpgenerator.orcid.OrcidController.OrcidSearchResponse.OrcidSearchResult;

@RestController
@RequestMapping("/orcid")
public class OrcidController {

	// defs
	private static final Logger log = LoggerFactory.getLogger(OrcidController.class.getCanonicalName());
	
	// dependencies
	private final OrcidRESTApi orcidRESTApi;
	
	// initialization
	@Autowired
	public OrcidController(OrcidRESTApi orcidRESTApi) {
		this.orcidRESTApi = orcidRESTApi;
	}
	
	// web
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public OrcidSearchResponse search(@RequestParam("q") String query) {
		
		final JsonNode jsonResponse;
		try {
			jsonResponse = orcidRESTApi.search(query).execute().body();
		} catch (IOException e) {
			log.error("Error while fetching orcid record", e);
			throw new RuntimeException(e);
		}
		
		JsonNode jsonResults = jsonResponse.path("result");
		
		List<OrcidSearchResult> results = new ArrayList<>();
		for(int i=0; i<jsonResults.size(); i++) {
			results.add(convertOrcidSearchResult(jsonResults.get(0)));
		}
		
		return new OrcidSearchResponse(results);
	}
	
	@RequestMapping(value="/profile/{orcidId}", method=RequestMethod.GET)
	public OrcidRecordResponse profile(@PathVariable("orcidId") String orcidId) {
		
		final JsonNode jsonResponse;
		try {
			jsonResponse = orcidRESTApi.record(orcidId).execute().body();
		} catch (IOException e) {
			log.error("Error while fetching orcid record", e);
			throw new RuntimeException(e);
		}
		
		return convertRecordResponse(jsonResponse);
	}
	
	// private helpers
	public static OrcidSearchResult convertOrcidSearchResult(JsonNode root) {
		return new OrcidSearchResult(
					root.path("orcid-identifier").path("uri").textValue(),
					root.path("orcid-identifier").path("path").textValue(),
					root.path("orcid-identifier").path("host").textValue());
	}
	
	public static OrcidRecordResponse convertRecordResponse(JsonNode root) {
		String givenName = root.path("person").path("name").path("given-names").path("value").textValue();
		String familyName = root.path("person").path("name").path("family-name").path("value").textValue();
		String email = root.path("person").path("emails").path("email").path(0).path("email").textValue();
		
		JsonNode work = root.path("activities-summary").path("works").path("group").path(0).path("work-summary").path(0);
		String title = work.path("title").path("title").path("value").textValue();
		String subtitle = work.path("title").path("subtitle").path("value").textValue();
		
		JsonNode externalId = work.path("external-ids").path("external-id").path(0);
		String idType = externalId.path("external-id-type").textValue();
		String id = externalId.path("external-id-value").textValue();
		String idUrl = externalId.path("external-id-url").path("value").textValue();
		
		String publicationYear = work.path("publication-date").path("year").path("value").textValue();
		String publicationMonth = work.path("publication-date").path("month").path("value").textValue();
		String publicationDay = work.path("publication-date").path("day").path("value").textValue();
		
		return new OrcidRecordResponse(
				givenName, familyName, email,
				new OrcidRecordResponse.OrcidRecordProject(
						title, subtitle, id, idType, idUrl,
						publicationYear, publicationMonth, publicationDay)
				);
		
	}
	
	// responses
	public static class OrcidSearchResponse {
		public final List<OrcidSearchResult> results;
		
		public OrcidSearchResponse(List<OrcidSearchResult> results) {
			this.results = results;
		}

		public static class OrcidSearchResult {
			public final String uri;
			public final String path;
			public final String host;
			
			public OrcidSearchResult(String uri, String path, String host) {
				this.uri = uri;
				this.path = path;
				this.host = host;
			}
		}
	}

	public static class OrcidRecordResponse {
		public final String givenName;
		public final String familyName;
		public final String emailAddress;
		public final OrcidRecordProject project;
		
		public OrcidRecordResponse(String givenName, String familyName, String emailAddress, OrcidRecordProject project) {
			this.givenName = givenName;
			this.familyName = familyName;
			this.emailAddress = emailAddress;
			this.project = project;
		}

		public static class OrcidRecordProject {
			public final String title;
			public final String subtitle;
			
			public final String id;
			public final String idType;
			public final String idUrl;
			
			public final String publicationYear;
			public final String publicationMonth;
			public final String publicationDay;
			
			public OrcidRecordProject(String title, String subtitle, String id, String idType, String idUrl,
					String publicationYear, String publicationMonth, String publicationDay) {
				this.title = title;
				this.subtitle = subtitle;
				this.id = id;
				this.idType = idType;
				this.idUrl = idUrl;
				this.publicationYear = publicationYear;
				this.publicationMonth = publicationMonth;
				this.publicationDay = publicationDay;
			}
		}
	}
}
