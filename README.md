# Digital Preservation - DMP Generator

## How to run

1. Check out project
2. Run `./gradlew clean eclipse` in terminal
3. In Eclipse: `File -> Import -> General -> Existing Projects into Workspace` and choose the root folder of this repository
4. In Eclipse: Find `DmpGeneratorApplication.java` and right-click then `Run As -> Java Application`

The webapp should now run at `http://localhost:8080`

## Web Resources

Html/Css/Javascript files are located at `src/main/resources/static`

## API Endpoints

The following API endpoints are implemented:

**Orcid Search**

`http://localhost:8080/orcid/search?q=Marc%20Dietrichstein`

**Orcid Profile Retrieval**

`http://localhost:8080/orcid/profile/0000-0003-4890-3498`

**Zenodo Metdata Retrieval**

`http://localhost:8080/oai_pmh/record/?identifier=10.5281/zenodo.1209833&type=doi`

**Github Repository Info**

`http://localhost:8080/github/owner/mdietrichstein/repository/digitalpreservation-dmp`
or
`http://localhost:8080/github/owner/mdietrichstein/repository/digitalpreservation-dmp?ref=1.0.0`

## Docker

1. Build a docker image: `./gradlew build docker`
2. Run it: `docker run -p 8080:8080 at.ac.tuwien.digital_preservation_2018.group2_4.fair/dmp-generator`
3. Open `http://localhost:8080` in your browser


