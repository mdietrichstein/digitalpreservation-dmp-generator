package at.ac.tuwien.digital_preservation_2018.group2_4.fair.dmpgenerator.model;

import java.util.ArrayList;

public class DMP {
	
	private String preservatino_url;
	private String document_date;
	private Author author;
	private Project project;
	private ArrayList<ProjectFile> files;
	
	public DMP(String preservatino_url, String document_date, Author author, Project project,
			ArrayList<ProjectFile> files) {
		super();
		this.preservatino_url = preservatino_url;
		this.document_date = document_date;
		this.author = author;
		this.project = project;
		this.files = files;
	}
	
	public String getPreservatino_url() {
		return preservatino_url;
	}
	public void setPreservatino_url(String preservatino_url) {
		this.preservatino_url = preservatino_url;
	}
	public String getDocument_date() {
		return document_date;
	}
	public void setDocument_date(String document_date) {
		this.document_date = document_date;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public ArrayList<ProjectFile> getFiles() {
		return files;
	}
	public void setFiles(ArrayList<ProjectFile> files) {
		this.files = files;
	}
}
