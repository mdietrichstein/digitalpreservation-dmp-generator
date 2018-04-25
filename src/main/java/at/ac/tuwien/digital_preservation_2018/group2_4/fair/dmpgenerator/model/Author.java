package at.ac.tuwien.digital_preservation_2018.group2_4.fair.dmpgenerator.model;

public class Author {

	private String emailAddress;
	private String familyName;
	private String givenName;
  private String orcidId;
	private AuthorProject project;

	public Author() {
		super();
	}

	public Author(String emailAddress, String familyName, String givenName, String orcidId, AuthorProject project) {
		super();
		this.orcidId = orcidId;
		this.emailAddress = emailAddress;
		this.familyName = familyName;
		this.givenName = givenName;
		this.project = project;
	}

	public AuthorProject getProject() {
		return project;
	}

	public void setProject(AuthorProject project) {
		this.project = project;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailaddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

  public void setOrcidId(String orcidId) {
    this.orcidId = orcidId;
  }

  public String getOrcidId() {
    return orcidId;
  }
}
