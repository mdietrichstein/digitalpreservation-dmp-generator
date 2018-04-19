package at.ac.tuwien.digital_preservation_2018.group2_4.fair.dmpgenerator.oai_pmh;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OAIPMHApi {
	@GET("/oai2d?verb=GetRecord&metadataPrefix=oai_dc")
	public Call<Record> getRecord(@Query("identifier") String identifier);
}
