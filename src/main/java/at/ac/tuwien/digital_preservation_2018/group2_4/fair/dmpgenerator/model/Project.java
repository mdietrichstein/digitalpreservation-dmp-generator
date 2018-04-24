package at.ac.tuwien.digital_preservation_2018.group2_4.fair.dmpgenerator.model;

public class Project {

	private String[] creators;
	private String description;
	private String githubUrl;
	private String[] identifiers;
	private String rights;
	private String title;
	private String[] types;
	
	public Project() {
		super();
	}
	public Project(String[] creators, String description, String githubUrl, String[] identifiers, String rights,
			String title, String[] types) {
		super();
		this.creators = creators;
		this.description = description;
		this.githubUrl = githubUrl;
		this.identifiers = identifiers;
		this.rights = rights;
		this.title = title;
		this.types = types;
	}

	public String[] getCreators() {
		return creators;
	}

	public void setCreators(String[] creators) {
		this.creators = creators;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGithubUrl() {
		return githubUrl;
	}

	public void setGithubUrl(String githubUrl) {
		this.githubUrl = githubUrl;
	}

	public String[] getIdentifiers() {
		return identifiers;
	}

	public void setIdentifiers(String[] identifiers) {
		this.identifiers = identifiers;
	}

	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}
	
}
