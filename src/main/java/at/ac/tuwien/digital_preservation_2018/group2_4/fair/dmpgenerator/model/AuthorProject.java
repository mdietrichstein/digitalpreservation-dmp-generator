package at.ac.tuwien.digital_preservation_2018.group2_4.fair.dmpgenerator.model;

public class AuthorProject {
	
	private String id;
	private String idType;
	private String idUrl;
	private String publicationDay;
	private String publicationMonth;
	private String publicationYear;
	private String subtitle;
	private String title;
	
	public AuthorProject() {
		super();
	}
	public AuthorProject(String id, String idType, String idUrl, String publicationDay, String publicationMonth,
			String publicationYear, String subtitle, String title) {
		super();
		this.id = id;
		this.idType = idType;
		this.idUrl = idUrl;
		this.publicationDay = publicationDay;
		this.publicationMonth = publicationMonth;
		this.publicationYear = publicationYear;
		this.subtitle = subtitle;
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdUrl() {
		return idUrl;
	}

	public void setIdUrl(String idUrl) {
		this.idUrl = idUrl;
	}

	public String getPublicationDay() {
		return publicationDay;
	}

	public void setPublicationDay(String publicationDay) {
		this.publicationDay = publicationDay;
	}

	public String getPublicationMonth() {
		return publicationMonth;
	}

	public void setPublicationMonth(String publicationMonth) {
		this.publicationMonth = publicationMonth;
	}

	public String getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(String publicationYear) {
		this.publicationYear = publicationYear;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
