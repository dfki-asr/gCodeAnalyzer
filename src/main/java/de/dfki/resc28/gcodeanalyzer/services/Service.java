/*
 * This file is part of gCodeAnalyzer. It is subject to the license terms in
 * the LICENSE file found in the top-level directory of this distribution.
 * You may not use this file except in compliance with the License.
 */
package de.dfki.resc28.gcodeanalyzer.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.vocabulary.DCTerms;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.XSD;

import de.dfki.resc28.gcodeanalyzer.Server;
import de.dfki.resc28.gcodeanalyzer.constants.MIME;
import de.dfki.resc28.gcodeanalyzer.vocabularies.GR;


@Path("")
public class Service 
{
	@GET
	@Path("/analyze")
	@Produces({ MIME.CT_APPLICATION_JSON_LD, MIME.CT_APPLICATION_NQUADS, MIME.CT_APPLICATION_NTRIPLES, MIME.CT_APPLICATION_RDF_JSON, MIME.CT_APPLICATION_RDFXML, MIME.CT_APPLICATION_TRIX, MIME.CT_APPLICATION_XTURTLE, MIME.CT_TEXT_N3, MIME.CT_TEXT_TRIG, MIME.CT_TEXT_TURTLE })
	public Response getGCodeInfo( @HeaderParam(HttpHeaders.ACCEPT) @DefaultValue(MIME.CT_TEXT_TURTLE) final String acceptType,
								  @QueryParam("uri") String gCodeUri )
	{
		try
		{
			URL gCodeURL = new URL(gCodeUri);
			InputStream is = gCodeURL.openStream(); 
			de.dietzm.Model gCodeModel = new de.dietzm.Model(gCodeURL.toString());
			gCodeModel.loadModel(is);
			gCodeModel.analyze();
			
			final Model responseModel = ModelFactory.createDefaultModel();
			responseModel.setNsPrefix("xsd", XSD.NS);
			responseModel.setNsPrefix("rdf", RDF.uri);
			responseModel.setNsPrefix("rdfs", RDFS.uri);
			responseModel.setNsPrefix("dcterms", DCTerms.NS);
			responseModel.setNsPrefixes(GR.NAMESPACE);
			
			Resource workPiece = responseModel.createResource(gCodeUri);
			Resource priceSpec = responseModel.createResource(fRequestUrl.getRequestUri().toString());
			
			responseModel.add(priceSpec, RDF.type, GR.UnitPriceSpecification);
			responseModel.add(priceSpec, GR.hasCurrency, responseModel.createLiteral("EUR"));
			responseModel.add(priceSpec, GR.hasCurrencyValue, responseModel.createTypedLiteral(gCodeModel.getPrice()));
			responseModel.add(priceSpec, GR.validFrom, responseModel.createTypedLiteral(Calendar.getInstance()));
			responseModel.add(priceSpec, GR.hasUnitOfMeasurement, responseModel.createLiteral("H87"));
			responseModel.add(priceSpec, DCTerms.source, responseModel.createTypedLiteral(Server.fBaseURI, XSDDatatype.XSDanyURI));
			responseModel.add(priceSpec, DCTerms.references, workPiece);
			
			
			
			
			StreamingOutput out = new StreamingOutput() 
			{
				public void write(OutputStream output) throws IOException, WebApplicationException
				{
					RDFDataMgr.write(output, responseModel, RDFDataMgr.determineLang(null, acceptType, null)) ;
				}
			};
			
			return Response.ok(out)
						   .type(acceptType)
						   .build();
		}
		catch (WebApplicationException e)
		{
			e.printStackTrace();
		} catch (MalformedURLException e) 
		{
			e.printStackTrace();
			throw new WebApplicationException("Invalid gCode URI.", Status.BAD_REQUEST);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			throw new WebApplicationException("gCode not valid.", Status.BAD_REQUEST);
		}
		
		return null;
	}
	
	@Context protected UriInfo fRequestUrl;
}
