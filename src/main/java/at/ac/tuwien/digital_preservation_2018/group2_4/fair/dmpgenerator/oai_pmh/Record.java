package at.ac.tuwien.digital_preservation_2018.group2_4.fair.dmpgenerator.oai_pmh;

import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

@Root(strict=false)
@NamespaceList({
	@Namespace(prefix="dc", reference = "http://purl.org/dc/elements/1.1/"),
	@Namespace(prefix="oai_dc", reference = "http://www.openarchives.org/OAI/2.0/oai_dc/"),
})
public class Record {

	@Element(name="header")
    @Path("GetRecord/record")
    public Header header;
	
	@Element(name="dc")
    @Path("GetRecord/record/metadata")
    public Metadata metadata;
	
	@Root(strict=false)
	public static class Header {
		@Element(name="identifier")
		public String identifier;
		
		@Element(name="datestamp")
		public String datestamp;
		
		@Element(name="setSpec")
		public String spec;
	}
	
	@Root(strict=false)
	public static class Metadata {
		@ElementList(entry="creator", inline=true)
		public List<String> creators;
		
		@Element(name="date")
		public String date;

		@Element(name="description")
		public String description;
		
		@ElementList(entry="identifier", inline=true)
		public List<String> identifiers;

		@ElementList(entry="relation", inline=true)
		public List<String> relations;

		@Element(name="rights")
		public String rights;
		
		@Element(name="title")
		public String title;
		
		@ElementList(entry="type", inline=true)
		public List<String> types;
	}
}
