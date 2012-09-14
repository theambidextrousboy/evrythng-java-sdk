package com.evrythng.api.wrapper.test.service;

import com.evrythng.api.wrapper.ApiManager;
import com.evrythng.api.wrapper.Configuration;
import com.evrythng.api.wrapper.service.ThngService;
import com.evrythng.api.wrapper.util.JSONUtils;

import com.evrythng.thng.resource.model.store.Location;
import com.evrythng.thng.resource.model.store.Property;
import com.evrythng.thng.resource.model.store.PropertyValue;
import com.evrythng.thng.resource.model.store.Thng;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author tpham
 */
public class ThngServiceTest {
    
    private ApiManager apiManager;
    private ThngService thngService;
     
    private static final Logger logger = LoggerFactory.getLogger(ThngServiceTest.class);

    @Before
    public void init() {
    	Configuration config = new Configuration("MaEDwlYA2Xxp2oSzt9uxbenTTyiYdZgnnVQlGnmnDCiLgfUO2pKzpbhzAeotwpc0KV9J8M2QssIdxOOt");
    	config.setBaseDomainUrl("http://api.staging.evrythng.net");
    	
        apiManager = new ApiManager(config);
        thngService = apiManager.thngService();        
    }

    @Test
    public void getAllThngsSize() {
        int size =thngService.getAllThngsSize();
        Assert.assertTrue(size == 355);
    }
    
    @Test
    public void getAllThngs() {
    	List<Thng> things = thngService.getAllThngs();
    	
    	debug("List of thngs", things.get(0).getProperties().size());
    }

    @Ignore
    @Test
    public void getAllThngsWithPage() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void getAllThngsWithPageAndSize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void createThng() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void getThngById() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void updateThngById() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void deleteThngById() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void getThngPropertiesSize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void getThngPropertyKeys() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void getThngProperties() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void getThngPropertiesWithPage() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void getThngPropertiesWithPageAndSize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void pushThngProperty() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void pushThngProperties() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void deleteThngProperties() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void getThngPropertyValuesSize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void getLatestThngPropertyValue() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void getThngPropertyValues() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void getThngPropertyValuesWithPage() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void getThngPropertyValuesWithPageAndSize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void getThngPropertyValuesFrom() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void getThngPropertyValuesBetween() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void getThngPropertyValuesTo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void pushThngPropertyValueByKey() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void pushThngPropertyValueByKeyAt() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void pushThngPropertyValuesByKey() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void deleteAllThngPropertyValuesByKey() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void deleteThngPropertyValuesByKeyTo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void getThngLocationSize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void getLatestThngLocation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void getThngLocations() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void getThngLocationsWithPage() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void getThngLocationsWithPageAndSize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void getThngLocationsFrom() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void getThngLocationsBetween() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void getThngLocationsTo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void pushThngLocation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void pushThngLocationAt() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void deleteAllThngLocation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void deleteThngLocationTo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    /**
	 * Outputs a debugging message to the current {@link #logger} using the
	 * provided {@code label} and {@code object} (as a JSON readable
	 * content).
	 * 
	 * @param label
	 * @param object
	 */
	protected void debug(String label, Object object) {
		logger.debug("{}: {}", label, JSONUtils.toString(object));
	}
}
