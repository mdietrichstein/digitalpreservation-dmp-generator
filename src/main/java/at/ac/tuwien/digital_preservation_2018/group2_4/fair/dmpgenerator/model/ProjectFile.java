package at.ac.tuwien.digital_preservation_2018.group2_4.fair.dmpgenerator.model;

public class ProjectFile {
	
	private String checksum;
	private String name;
	private String path;
	private String preservation_duration;
	private String size;
	private String tag;
	
	
	public ProjectFile() {
		super();
	}

	public ProjectFile(String checksum, String name, String path, String preservation_duration, String size,
			String tag) {
		super();
		this.checksum = checksum;
		this.name = name;
		this.path = path;
		this.preservation_duration = preservation_duration;
		this.size = size;
		this.tag = tag;
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
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

	public String getPreservation_duration() {
		return preservation_duration;
	}

	public void setPreservation_duration(String preservation_duration) {
		this.preservation_duration = preservation_duration;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
