package com.gmo.test.junit;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.ws.rs.core.Response;
import com.gmo.test.ws.service.CountryService;

@RunWith(JUnitPlatform.class)
public class CountryRestTest {
	
	@DisplayName("CountryService.getCountries() == status 200")
    @Test
    void getAllCountries() {
		CountryService service = new CountryService();
		Response res = service.getCountries();
		assertEquals( "should return 200",  200, res.getStatus());
		assertNotNull( "should return countries",  res.getEntity());
    }
	
	@DisplayName("CountryService.getCountry() == status 200")
    @Test
    void getAllCountry() {
		CountryService service = new CountryService();
		Response res = service.getCountry("PT");
		assertEquals( "should return 200",  200, res.getStatus());
		assertNotNull( "should return country",  res.getEntity());
    }
	
	@DisplayName("CountryService.getCountry() == status 404")
    @Test
    void getAllCountryNotFound() {
		CountryService service = new CountryService();
		Response res = service.getCountry("XX");
		assertEquals( "should return 404",  404, res.getStatus());		
    }
	
	
}
