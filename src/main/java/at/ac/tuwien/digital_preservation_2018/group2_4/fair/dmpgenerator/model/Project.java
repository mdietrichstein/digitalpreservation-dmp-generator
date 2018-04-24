package at.ac.tuwien.digital_preservation_2018.group2_4.fair.dmpgenerator.model;

public class Project {

	private String doi;
	private String title;
	private String description;
	private String rights;
	private String publication_date;
	private String type;
	private String zenodo_link;
	private String github_link;
	
	public Project(String doi, String title, String description, String rights, String publication_date, String type, String zenodo_link, String github_link) {
		super();
		this.doi = doi;
		this.title = title;
		this.description = description;
		this.rights = rights;
		this.publication_date = publication_date;
		this.type = type;
		this.zenodo_link = zenodo_link;
		this.github_link = github_link;
	}
	
	
	public String getZenodo_link() {
		return zenodo_link;
	}
	public void setZenodo_link(String zenodo_link) {
		this.zenodo_link = zenodo_link;
	}
	public String getGithub_link() {
		return github_link;
	}
	public void setGithub_link(String github_link) {
		this.github_link = github_link;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDoi() {
		return doi;
	}
	public void setDoi(String doi) {
		this.doi = doi;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRights() {
		return rights;
	}
	public void setRights(String rights) {
		this.rights = rights;
	}
	public String getPublication_date() {
		return publication_date;
	}
	public void setPublication_date(String publication_date) {
		this.publication_date = publication_date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
