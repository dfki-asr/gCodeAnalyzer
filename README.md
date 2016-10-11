# gCodeAnalyzer
gCodeAnalyzer is a Linked Data API for analyzing gcodes.


## Installation & Running
```
mvn clean generate-resources license:add-third-party package tomcat7:run
```

You can modify gCodeAnalyzer's listening port in the `pom.xml`
```xml
<build>
	...
	<plugins>
		...
		<plugin>
			<groupId>org.apache.tomcat.maven</groupId>
			<artifactId>tomcat7-maven-plugin</artifactId>
			<version>2.2</version>  	
            		<configuration>
				<server>gCodeAnalyzer</server>
				<port>8080</port>
				<path>/</path>
			</configuration>
		</plugin>
		...
	</plugins>
</build>
```

## Usage
Get gCodeAnalyzer up and running. HTTP GET requests can be made against gCodeAnalyzer' form-style GET API as follows
```
GET analyze?uri=GCODE HTTP/1.1
Server: http:localhost:8080
Accept: text/turtle
```
where `GCODE` is the URI of your gCode resource.

Currently, gCodeAnalyzer responses with some price estimations for your gCode input.
```
@prefix rdf:   <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .
@prefix xsd:   <http://www.w3.org/2001/XMLSchema#> .
@prefix dcterms: <http://purl.org/dc/terms/> .
@prefix rdfs:  <http://www.w3.org/2000/01/rdf-schema#> .
@prefix GR:    <http://purl.org/goodrelations/v1#> .

<http://localhost:8080/analyze?uri=http://localhost/~resc01/3301.gcode>
        a                        GR:UnitPriceSpecification ;
        dcterms:references       <http://localhost/~resc01/3301.gcode> ;
        dcterms:source           "http://gcodeanalyzer-frontend"^^xsd:anyURI ;
        GR:hasCurrency           "EUR" ;
        GR:hasCurrencyValue      "0.09273803"^^xsd:float ;
        GR:hasUnitOfMeasurement  "H87" ;
        GR:validFrom             "2016-10-11T20:03:52.867Z"^^xsd:dateTime .
```

At some point, more gCode statistics will be added. Maybe you want to contribute?

## Dependencies
gCodeAnalyzer depends on [gCodeInfo](https://github.com/rmrschub/GCodeInfo), a mavenized fork of [GCodeInfo]{https://github.com/dietzm/GCodeInfo}.

## Contributing
Contributions are very welcome.

## License
gCodeAnalyzer is subject to the license terms in the LICENSE file found in the top-level directory of this distribution.
You may not use this file except in compliance with the License.

## Third-party Contents
This source distribution includes the third-party items with respective licenses as listed in the THIRD-PARTY file found in the top-level directory of this distribution.

## Acknowledgements
This work has been supported by the [German Ministry for Education and Research (BMBF)](http://www.bmbf.de/en/index.html) (FZK 01IMI3001 J) as part of the [ARVIDA](http://www.arvida.de/) project.
