package at.ac.tuwien.digital_preservation_2018.group2_4.fair.dmpgenerator.orcid;

import com.fasterxml.jackson.databind.JsonNode;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OrcidRESTApi {
	@Headers("Accept: application/json")
	@GET("search/")
	public Call<JsonNode> search(@Query("q") String query);
	
	@Headers("Accept: application/json")
	@GET("{orcid_id}/record")
	public Call<JsonNode> record(@Path("orcid_id") String orcidId);
}
