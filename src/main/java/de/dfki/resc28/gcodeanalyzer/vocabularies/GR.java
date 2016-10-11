/**
 * This file has been automatically generated using Grover (https://github.com/rmrschub/grover).
 * It contains static constants for the terms in the GR vocabulary.
 */
package de.dfki.resc28.gcodeanalyzer.vocabularies;

import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.shared.PrefixMapping;

public final class GR 
{
  public static final String PREFIX = "GR";
  public static final PrefixMapping NAMESPACE = PrefixMapping.Factory.create().setNsPrefix(PREFIX, CONSTANTS.NS);

  /** 
   * Classes as org.apache.jena.rdf.model.Resource
   */
  public static final Resource UnitPriceSpecification = resource(CONSTANTS.CLASS_UnitPriceSpecification);
  public static final Resource Offering = resource(CONSTANTS.CLASS_Offering);

  /** 
   * Properties as org.apache.jena.rdf.model.Property
   */
  public static final Property hasPriceSpecification = property(CONSTANTS.PROP_hasPriceSpecification);
  public static final Property hasUnitOfMeasurement = property(CONSTANTS.PROP_hasUnitOfMeasurement);
  public static final Property validThrough = property(CONSTANTS.PROP_validThrough);
  public static final Property validFrom = property(CONSTANTS.PROP_validFrom);
  public static final Property hasCurrency = property(CONSTANTS.PROP_hasCurrency);
  public static final Property hasCurrencyValue = property(CONSTANTS.PROP_hasCurrencyValue);


  /**
   * Returns a Jena resource for the given namespace name 
   * @param nsName  the full namespace name of a vocabulary element as a string
   * @return the vocabulary element with given namespace name as a org.apache.jena.rdf.model.Resource
   */
  private static final Resource resource(String nsName)
  {
    return ResourceFactory.createResource(nsName); 
  }

  /**
   * Returns a Jena property for the given namespace name
   * @param nsName  the full namespace name of a vocabulary element as a string
   * @return the vocabulary element with given namespace name as a org.apache.jena.rdf.model.Property
   */
  private static final Property property(String nsName)
  { 
    return ResourceFactory.createProperty(nsName);
  }

  private static final class CONSTANTS 
  {
    /**
     * Vocabulary namespace URI as string 
     */
    private static final String NS = "http://purl.org/goodrelations/v1#";

    /**
     * Local and namespace names of RDF(S) classes as strings 
     */
    private static final String CLASS_LNAME_UnitPriceSpecification = "UnitPriceSpecification";
    private static final String CLASS_UnitPriceSpecification = nsName(CLASS_LNAME_UnitPriceSpecification);
    private static final String CLASS_LNAME_Offering = "Offering";
    private static final String CLASS_Offering = nsName(CLASS_LNAME_Offering);

    /**
     * Local and namespace names of RDF(S) properties as strings 
     */
    private static final String PROP_LNAME_hasPriceSpecification = "hasPriceSpecification";
    private static final String PROP_hasPriceSpecification = nsName(PROP_LNAME_hasPriceSpecification);
    private static final String PROP_LNAME_hasUnitOfMeasurement = "hasUnitOfMeasurement";
    private static final String PROP_hasUnitOfMeasurement = nsName(PROP_LNAME_hasUnitOfMeasurement);
    private static final String PROP_LNAME_validThrough = "validThrough";
    private static final String PROP_validThrough = nsName(PROP_LNAME_validThrough);
    private static final String PROP_LNAME_validFrom = "validFrom";
    private static final String PROP_validFrom = nsName(PROP_LNAME_validFrom);
    private static final String PROP_LNAME_hasCurrency = "hasCurrency";
    private static final String PROP_hasCurrency = nsName(PROP_LNAME_hasCurrency);
    private static final String PROP_LNAME_hasCurrencyValue = "hasCurrencyValue";
    private static final String PROP_hasCurrencyValue = nsName(PROP_LNAME_hasCurrencyValue);

 
    /**
     * Returns the full namespace name of a vocabulary element as a string
     * @param localName  the local name of a vocabulary element as a string
     * @return the full namespace name of a vocabulary element as a string
     */
    private static String nsName(String localName) 
    {
      return NS + localName;
    }
  }
}