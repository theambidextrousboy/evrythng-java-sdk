/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.net
 * 
 */
package net.evrythng.thng.api.examples;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.evrythng.thng.api.model.Thng;
import net.evrythng.thng.api.model.Property;
import net.evrythng.thng.api.model.ThngCollection;
import net.evrythng.thng.api.wrapper.ThngAPIWrapper;
import org.apache.http.client.ClientProtocolException;

/**
 * This exemplifies how the wrapper/API can be used.
 * @author <href="http://www.guinard.org>domguinard</a>
 */
public class Examples {
     private static String TOKEN = "";

     private Examples() {
     }
     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Intialize the wrapper (and provinding the API key)
            ThngAPIWrapper wrapper = new ThngAPIWrapper(TOKEN);
            

            // Create a Thng
            Thng myThng = wrapper.createThng(new Thng("My-TV." + 
                    Calendar.getInstance().getTimeInMillis(), "This is my TV.", 
                    true, 10.88, 50.56));
            

            // A Thng is uniquely identified by its auto-generated ID:
            System.out.println(myThng.getId());

            // Get this Thng again given its ID:
            myThng = wrapper.getThng(myThng.getId());
            System.out.println(myThng.getDescription());

            // Add some properties to our Thng
            Property<String> kwh = new Property<String>("KWh", "64");
            
            wrapper.createProperties(myThng,
                    new Property<String>("Address", "Gasometerstrasse 9"),
                    kwh);
            
            wrapper.getProperties(myThng); 
            
            // ... update them...
            kwh.setValue("65");
            wrapper.updateProperty(myThng, kwh);
            
            // ... or get them!
            kwh = wrapper.getProperty(myThng, kwh.getKey()); 
            System.out.println(kwh.getKey() + ":" + kwh.getValue());
            
            // Add the thng to a collection
            ThngCollection homeCollection = wrapper.createCollection(new ThngCollection("Home" + Calendar.getInstance().getTimeInMillis(), "The things in my home."));
            wrapper.addThngsToCollection(homeCollection, myThng);
            
            // Search for our thngs
            Collection<Thng> results = wrapper.search("tv");
            System.out.println(results.toArray()[1].toString());

        } catch (InstantiationException ex) {
            Logger.getLogger(Examples.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Examples.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(Examples.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClientProtocolException ex) {
            Logger.getLogger(Examples.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Examples.class.getName()).log(Level.SEVERE, null, ex);
        } 


    }
}
