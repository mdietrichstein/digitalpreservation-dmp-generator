package at.ac.tuwien.digital_preservation_2018.group2_4.fair.dmpgenerator.model;

import java.util.ArrayList;

public class DMP {
	
	private Author author;
	private ArrayList<ProjectFile> files;
	private Project project;
	
	public DMP() {
		super();
	}
	public DMP(Author author, Project project, ArrayList<ProjectFile> files) {
		super();
		this.author = author;
		this.project = project;
		this.files = files;
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
