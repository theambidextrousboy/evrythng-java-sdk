/*
 * (c) Dominique Guinard (www.guinard.org)
 * 
 */
package net.evrythng.thng.api.wrapper.tests;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.evrythng.thng.api.model.Thng;
import net.evrythng.thng.api.model.Property;
import net.evrythng.thng.api.model.ThngCollection;
import net.evrythng.thng.api.search.GeoCode;
import net.evrythng.thng.api.search.SearchParameter.Type;
import net.evrythng.thng.api.wrapper.ThngAPIWrapper;
import org.apache.http.client.ClientProtocolException;

/**
 *
 * @author domguinard
 */
public class WrapperTest {

    private static String TOKEN = "305ff6dbc030ca8a323c112ca22d816a9831fe0c";

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
            Collection<Thng> results = wrapper.search("tv", new GeoCode(10, 49, 5), Type.MINE);
            System.out.println(results.toArray()[1].toString());

        } catch (URISyntaxException ex) {
            Logger.getLogger(WrapperTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClientProtocolException ex) {
            Logger.getLogger(WrapperTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WrapperTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(WrapperTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(WrapperTest.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
