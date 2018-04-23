package at.ac.tuwien.digital_preservation_2018.group2_4.fair.dmpgenerator.model;

public class Project {

	private String doi;
	private String title;
	private String rights;
	private String publication_date;
	private String type;
	
	public Project(String doi, String title, String rights, String publication_date, String type) {
		super();
		this.doi = doi;
		this.title = title;
		this.rights = rights;
		this.publication_date = publication_date;
		this.type = type;
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
