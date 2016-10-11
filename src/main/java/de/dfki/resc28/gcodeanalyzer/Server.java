/*
 * This file is part of gCodeAnalyzer. It is subject to the license terms in
 * the LICENSE file found in the top-level directory of this distribution.
 * You may not use this file except in compliance with the License.
 */
package de.dfki.resc28.gcodeanalyzer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;

import de.dfki.resc28.gcodeanalyzer.services.Service;

@ApplicationPath("/")
public class Server extends Application 
{
	public static String fBaseURI;

	public Server(@Context ServletContext servletContext) throws URISyntaxException, IOException
	{
		configure();
	}

	@Override
    public Set<Object> getSingletons() 
    {	
		Service bla = new Service();
		return new HashSet<Object>(Arrays.asList(bla));
    }

	public static synchronized void configure() 
    {
        try 
        {
            String configFile = System.getProperty("gcodeanalyzer.configuration");
            java.io.InputStream is;

            if (configFile != null) 
            {
                is = new java.io.FileInputStream(configFile);
                System.out.format("Loading gCodeAnalyzer configuration from %s ...%n", configFile);
            } 
            else 
            {
                is = Server.class.getClassLoader().getResourceAsStream("gcodeanalyzer.properties");
                System.out.println("Loading gCodeAnalyzer configuration from internal resource file ...");
            }

            java.util.Properties p = new Properties();
            p.load(is);

            Server.fBaseURI = getProperty(p, "baseURI", "gcodeanalyzer.baseURI");
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    public static String getProperty(java.util.Properties p, String key, String sysKey) 
    {
        String value = System.getProperty(sysKey);
        if (value != null) 
        {
            return value;
        }
        return p.getProperty(key);
    }
}
