package at.ac.tuwien.digital_preservation_2018.group2_4.fair.dmpgenerator;

import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.ContentsService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import at.ac.tuwien.digital_preservation_2018.group2_4.fair.dmpgenerator.oai_pmh.OAIPMHApi;
import at.ac.tuwien.digital_preservation_2018.group2_4.fair.dmpgenerator.orcid.OrcidRESTApi;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

@Configuration
@SuppressWarnings("deprecation")
public class DmpGeneratorConfiguration {

	@Bean
	public OkHttpClient okHttpClient() {
		HttpLoggingInterceptor logging = new HttpLoggingInterceptor();  
		logging.setLevel(Level.BODY);

		return new OkHttpClient.Builder()
								.addInterceptor(logging)
								.build();
	}
	
	@Bean
	public OrcidRESTApi orcidRESTApi(OkHttpClient okHttpClient) {
		Retrofit retrofit = new Retrofit.Builder()
        						.baseUrl("https://pub.orcid.org/v2.0/")
        						.client(okHttpClient)
        						.addConverterFactory(JacksonConverterFactory.create())
        						.build();
		
		return retrofit.create(OrcidRESTApi.class);
	}
	
	@Bean
	public OAIPMHApi oaiPMHApi(OkHttpClient okHttpClient) {
		Retrofit retrofit = new Retrofit.Builder()
								.baseUrl("https://zenodo.org/")
								.client(okHttpClient)
								.addConverterFactory(
										SimpleXmlConverterFactory.createNonStrict(
												new Persister(new AnnotationStrategy())
								)).build();

		
		return retrofit.create(OAIPMHApi.class);
	}
	
	@Bean
	public GitHubClient githubClient() {
		GitHubClient gitHubClient = new GitHubClient();
		return gitHubClient;
	}
	
	@Bean
	public ContentsService contentsService(GitHubClient gitHubClient) {
		ContentsService contentsService = new ContentsService(gitHubClient);
		return contentsService;
	}
	
	@Bean
	public RepositoryService repositoryService(GitHubClient gitHubClient) {
		RepositoryService repositoryService = new RepositoryService(gitHubClient);
		return repositoryService;
	}
}
