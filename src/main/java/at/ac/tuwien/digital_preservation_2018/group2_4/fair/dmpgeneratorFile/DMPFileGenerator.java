package at.ac.tuwien.digital_preservation_2018.group2_4.fair.dmpgeneratorFile;

import at.ac.tuwien.digital_preservation_2018.group2_4.fair.dmpgenerator.model.DMP;

public class DMPFileGenerator {
	
	public static String generate(DMP d) {
		return String.format(dmp,
				 d.getProject().getTitle());
	}
	
	private static String dmp = 
			"{\r\n" + 
			"  \"@context\": {\r\n" + 
			"    \"dmp\": \"http://purl.org/madmps#\",\r\n" + 
			"    \"foaf\": \"http://xmlns.com/foaf/0.1/\",\r\n" + 
			"    \"dc\": \"http://purl.org/dc/elements/1.1/\",\r\n" + 
			"    \"dcterms\": \"http://purl.org/dc/terms/\",\r\n" + 
			"    \"premis\": \"http://www.loc.gov/premis/rdf/v1#\"\r\n" + 
			"  },\r\n" + 
			"  \"@id\": \"http://example.org/dmps/mydmp\",\r\n" + 
			"  \"@type\": \"dmp:DataManagementPlan\",\r\n" + 
			"  \"dcterms:title\": \" %s \",\r\n" + 
			"  \"dcterms:description\": \"In this project, we analyze the time series of divorced marriages with respect to their duration in the area of Salzburg Land from 1985 to 2014. Our specific interest concerns the trend over marriages which lasted from 10 to 15 years.\",\r\n" + 
			"  \"dc:creator\": [\r\n" + 
			"    {\r\n" + 
			"      \"@id\": \"orcid.org/0000-0002-5842-5031\",\r\n" + 
			"      \"foaf:name\": \"Rafael Konlechner\",\r\n" + 
			"      \"foaf:mbox\": \"mailto:rafael.konlechner@tuwien.ac.at\"\r\n" + 
			"    },\r\n" + 
			"    {\r\n" + 
			"      \"@id\": \"orcid.org/0000-0002-3611-3483\",\r\n" + 
			"      \"foaf:name\": \"Simon Oblasser\",\r\n" + 
			"      \"foaf:mbox\": \"mailto:simon.oblasser@student.tuwien.ac.at\"\r\n" + 
			"    }\r\n" + 
			"  ],\r\n" + 
			"  \"dcterms:hasVersion\": \"v1.0.2\",\r\n" + 
			"  \"dc:date\": \"2017-06-06\",\r\n" + 
			"  \"dmp:hasDataObject\": [\r\n" + 
			"    {\r\n" + 
			"      \"@id\": \"https://doi.org/10.5281/zenodo.803326\",\r\n" + 
			"      \"@type\": \"dmp:SourceCode\",\r\n" + 
			"      \"dmp:hasIntelectualPropertyRights\": {\r\n" + 
			"        \"dcterms:license\": \"https://opensource.org/licenses/MIT\"\r\n" + 
			"      },\r\n" + 
			"      \"dmp:hasMetadata\": {\r\n" + 
			"        \"dcterms:description\": \"Bundle containing the source code (Jupyter notebook), input data and Dockerfile\",\r\n" + 
			"        \"premis:hasObjectCharacteristics\": {\r\n" + 
			"          \"premis:hasFormat\": \"premis:Format:zip\",\r\n" + 
			"            \"premis:fixity\": {\r\n" + 
			"              \"premis:hasMessageDigestAlgorithm\": \"premis:Fixity:SHA-256\",\r\n" + 
			"              \"premis:messageDigest\": \"66798be94ce2de3d037c08b6297941678d308774b3912d74336c72c975fcf5b3\"\r\n" + 
			"          }\r\n" + 
			"        },\r\n" + 
			"        \"dmp:hasDataVolume\": \"19.6 kB\"\r\n" + 
			"      },\r\n" + 
			"      \"dmp:hasDataRepository\": \"https://github.com/oblassers/fair-data-science\",\r\n" + 
			"      \"dmp:hasBudget\": \"€ 3200\",\r\n" + 
			"      \"hasBudgetJustification\": \"This estimation includes expenses for personell, software and hardware. Given regular circumstances, the data management can be implemented by two contributors in two weeks. In total, 160h of work are estimated, which results in personell costs of € 3.200,-, assuming an hourly wage of 20€ (student workers). No costs for software or hardware arise, since all of the used software and hosting services are free of charge. For the long run, a different repository, more suitable for long term preservatio may be chosen. This could cause additional costs.\",\r\n" + 
			"      \"dmp:hasPreservation\": \"The JSON data source file and all files related to Docker should be preserved. The ability to recreate the two Docker images for mongoDB and Jupyter, as well as the availability of the data source file will allow the reproduction of the experiment.\",\r\n" + 
			"      \"dmp:hasDataSharing\": \"All files required to run the experiment are published on GitHub under the MIT license. A Digital Object Identifier (DOI) was created to unambiguously identify the data and make citation possible. The DOI was generated with Zenodo integrated with GitHub. The DOI always links to the latest release made on GitHub. Zenodo is an established, publicly searchable repository for science data, which supports the findability of our data. In order to enforce the reproducibility of our experiment we provide a Dockerfile along with the data. By following the instructions on GitHub it should be easy to run the experiment. Additionally a web page will be created to inform about the project and all aspects of the Data Management Plan.\",\r\n" + 
			"      \"dmp:hasEthicsAndPrivacy\": \"Ethical issues should not arise, since the data does not contain any sensible or inflicting data to individuals or groups. Our data does only contain aggregated numbers about divorces, without any detailled information. The input data which forms the basis for our experiment is also available on a publicly accessible open data platform.\",\r\n" + 
			"      \"dmp:hasDocumentation\": \"The data is documented in a self-contained way. The Jupyter notebook contains data, code, plots and documentation all in one. The experiment conducted in the Jupyter notebook is documented in a structured way describing each step of the experiment. Additionally a web page is created informing about the project and the details of the Data Management Plan. Documentation about the reproducibility of our experiment is available on the publicly accessible project repository on GitHub.\",\r\n" + 
			"      \"hasDocumentationLink\": \"https://github.com/oblassers/fair-data-science/blob/master/README.md\",\r\n" + 
			"      \"dmp:hasDataCollection\": \"The data will be used in the self-contained, interactive computing environment Jupyter. For this reason, all generated data, including plotting results, will only be transient in the memory. The notebook itself - stored in the .ipynb file format (internally it is JSON) - contains textual descriptions and python code, that transforms and visualizes the data set. The Jupyter notebook can be further transformed into various formats (.py, .html, .md, .rst, .tex, .pdf). The size can be considered as rather small - in the range of 20-500KB. To support the reproducibility of our experiment Dockerfiles are created in order to establish a working environment.\",\r\n" + 
			"      \"dmp:hasDataObject\": [\r\n" + 
			"        {\r\n" + 
			"          \"@type\": \"dmp:Container\",\r\n" + 
			"          \"github\": \"https://github.com/oblassers/fair-data-science/blob/master/Dockerfile\",\r\n" + 
			"          \"dc:title\": \"Dockerfile\",\r\n" + 
			"          \"dmp:hasIntelectualPropertyRights\": {\r\n" + 
			"            \"dcterms:license\": \"https://opensource.org/licenses/MIT\"\r\n" + 
			"          },\r\n" + 
			"          \"dmp:hasMetadata\": {\r\n" + 
			"            \"dcterms:description\": \"Dockerfile\",\r\n" + 
			"            \"premis:hasObjectCharacteristics\": {\r\n" + 
			"              \"premis:fixity\": {\r\n" + 
			"                \"premis:hasMessageDigestAlgorithm\": \"premis:Fixity:SHA-256\",\r\n" + 
			"                \"premis:messageDigest\": \"a16c7c70cccd3b706d0e64038675a0b302c6250a159fd27b4f069565e1464797\"\r\n" + 
			"              }\r\n" + 
			"            },\r\n" + 
			"            \"dmp:hasDataVolume\": \"103 bytes\"\r\n" + 
			"          }\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"          \"@id\": \"https://www.salzburg.gv.at/ogd/7fa00c8b-6189-42b8-af93-cc1ebff0a818/divorce-szg-duration.json\",\r\n" + 
			"          \"@type\": \"dmp:File\",\r\n" + 
			"          \"dc:title\": \"JSON Data File\",\r\n" + 
			"          \"dmp:hasIntelectualPropertyRights\": {\r\n" + 
			"            \"dcterms:license\": \"https://creativecommons.org/licenses/by/3.0/\"\r\n" + 
			"          },\r\n" + 
			"          \"dmp:hasMetadata\": {\r\n" + 
			"            \"dcterms:description\": \"Time series dataset of divorced marriages in Salzburg Land from 1985 to 2014\",\r\n" + 
			"            \"premis:hasObjectCharacteristics\": {\r\n" + 
			"              \"premis:hasFormat\": \"premis:Format:json\",\r\n" + 
			"              \"premis:fixity\": {\r\n" + 
			"                \"premis:hasMessageDigestAlgorithm\": \"premis:Fixity:SHA-256\",\r\n" + 
			"                \"premis:messageDigest\": \"34c140deb5d56c02470741264cdd1e7326e19226cae9b8f050d1cdd95b8f83d9\"\r\n" + 
			"              }\r\n" + 
			"            },\r\n" + 
			"            \"dmp:hasDataVolume\": \"49 kB\"\r\n" + 
			"          }\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"          \"@type\": \"dmp:File\",\r\n" + 
			"          \"github\": \"https://github.com/oblassers/fair-data-science/blob/master/Task-3-Experiment.ipynb\",\r\n" + 
			"          \"dc:title\": \"Jupyter Notebook\",\r\n" + 
			"          \"dmp:hasMetadata\": {\r\n" + 
			"            \"dcterms:description\": \"Jupyter notebook containing the experiment\",\r\n" + 
			"            \"premis:hasObjectCharacteristics\": {\r\n" + 
			"              \"premis:hasFormat\": \"premis:Format:ipynb\",\r\n" + 
			"              \"premis:fixity\": {\r\n" + 
			"                \"premis:hasMessageDigestAlgorithm\": \"premis:Fixity:SHA-256\",\r\n" + 
			"                \"premis:messageDigest\": \"41739c49487f1dbaf4c4496b96a5b3c0c74d1a8849b3ef07778563cb2244bbb0\"\r\n" + 
			"              }\r\n" + 
			"            },\r\n" + 
			"            \"dmp:hasDataVolume\": \"21 kB\"\r\n" + 
			"          }\r\n" + 
			"        }\r\n" + 
			"      ]\r\n" + 
			"    }\r\n" + 
			"  ]\r\n" + 
			"}";

}
