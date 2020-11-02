package com.gmo.test.bean;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Bean responsible to call Jax-RS client
 * @author Gledson Oliveira
 *
 */
@Named
@SessionScoped
public class CountryBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Client jaxrsClient;

	private String countryResource = "http://localhost:8080/demows/ws/rest/country/";
	
	private String allCountriesResource = "http://localhost:8080/demows/ws/rest/countries";
    
	private String result;
	
	private String code;
	
	private String resultAll;

    public CountryBean() {
         jaxrsClient = ClientBuilder.newClient();
    }

    /*
     * Call rest with param 
     */
    public void callCountryRestWSAction() {

    	WebTarget webTarget = jaxrsClient.target(countryResource+code);
        
        Invocation invocation = webTarget.request(MediaType.APPLICATION_JSON).buildGet();

        Response response = invocation.invoke();

        this.result = response.readEntity(String.class);
    }

    /*
     * Call rest and receive all countries
     */
    public void callAllCountriesRestWSAction() {

    	WebTarget webTarget = jaxrsClient.target(allCountriesResource);
        
        Invocation invocation = webTarget.request(MediaType.APPLICATION_JSON).buildGet();

        Response response = invocation.invoke();

        this.resultAll = response.readEntity(String.class);
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCountryResource() {
		return countryResource;
	}

	public String getAllCountriesResource() {
		return allCountriesResource;
	}

	public void setCountryResource(String countryResource) {
		this.countryResource = countryResource;
	}

	public void setAllCountriesResource(String allCountriesResource) {
		this.allCountriesResource = allCountriesResource;
	}        
    
	public String getResultAll() {
		return resultAll;
	}

	public void setResultAll(String resultAll) {
		this.resultAll = resultAll;
	}
	
    public void clear() {
    	this.setCode("");
    	this.setResult("");
    	this.setResultAll("");
    }
}