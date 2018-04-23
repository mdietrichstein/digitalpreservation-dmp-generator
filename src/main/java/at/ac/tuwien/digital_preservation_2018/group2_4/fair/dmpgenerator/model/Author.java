package at.ac.tuwien.digital_preservation_2018.group2_4.fair.dmpgenerator.model;

public class Author {
	
	private String name;
	private String email;
	private String orcid_id;
	
	public Author(String name, String email, String orcid_id) {
		super();
		this.name = name;
		this.email = email;
		this.orcid_id = orcid_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOrcid_id() {
		return orcid_id;
	}
	public void setOrcid_id(String orcid_id) {
		this.orcid_id = orcid_id;
	}
	
	
	
}
