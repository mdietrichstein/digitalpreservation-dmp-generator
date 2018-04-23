package at.ac.tuwien.digital_preservation_2018.group2_4.fair.dmpgenerator.model;

public class ProjectFile {
	
	private String name;
	private String path;
	private String size;
	private String checksum;
	private String filetype;
	
	public ProjectFile(String name, String path, String size, String checksum, String filetype) {
		super();
		this.name = name;
		this.path = path;
		this.size = size;
		this.checksum = checksum;
		this.filetype = filetype;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getChecksum() {
		return checksum;
	}
	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}	

}
