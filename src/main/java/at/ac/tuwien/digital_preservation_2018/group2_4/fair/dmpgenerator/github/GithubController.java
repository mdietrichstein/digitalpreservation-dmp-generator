package at.ac.tuwien.digital_preservation_2018.group2_4.fair.dmpgenerator.github;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryContents;
import org.eclipse.egit.github.core.service.ContentsService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/github")
public class GithubController {

	// defs
	private static final Logger log = LoggerFactory.getLogger(GithubController.class.getCanonicalName());
	
	// dependencies
	private final ContentsService contentsService;
	private final RepositoryService repositoryService;
	
	// initialization
	public GithubController(ContentsService contentsService, RepositoryService repositoryService) {
		this.contentsService = contentsService;
		this.repositoryService = repositoryService;
	}
	
	// web
	@RequestMapping(value="/owner/{owner}/repository/{repository}", method=RequestMethod.GET)
	public RepositoryResponse repositoryInformation(
			@PathVariable("owner") String ownerName,
			@PathVariable("repository") String repositoryName,
			@Nullable @RequestParam(value="ref", required=false) String ref
		) {
		
		try {
			Repository repository = repositoryService.getRepository(ownerName, repositoryName);
			return new RepositoryResponse(entriesForPath(contentsService, repository, "", ref));	
		} catch(Exception e) {
			log.error("Error while trying to fetch github repository info", e);
			throw new RuntimeException(e);
		}
	}

	// private helpers
	private static List<RepositoryResponse.RepositoryEntry> entriesForPath(
			ContentsService contentsService,
			Repository repository,
			String path,
			@Nullable String ref) throws IOException {
		
		List<RepositoryResponse.RepositoryEntry> entries = new ArrayList<>();
		
		for(RepositoryContents c : contentsService.getContents(repository, path, ref)) {
			if(c.getType().equals("dir")) {
				entries.addAll(entriesForPath(contentsService, repository, c.getPath(), ref));
			} else {
				entries.add(new RepositoryResponse.RepositoryEntry(
						c.getName(), c.getPath(), c.getType(), c.getSize(), c.getSha()));
			}
		}
		
		return entries;
	}
	
	// responses
	public static class RepositoryResponse {
		public final List<RepositoryEntry> entries;
		
		public RepositoryResponse(List<RepositoryEntry> entries) {
			this.entries = entries;
		}
		
		public static class RepositoryEntry {
			public final String name;
			public final String path;
			public final String type;
			public final long size;
			public final String sha;
			
			public RepositoryEntry(String name, String path, String type, long size, String sha) {
				this.name = name;
				this.path = path;
				this.type = type;
				this.size = size;
				this.sha = sha;
			}
		}
	}
}
