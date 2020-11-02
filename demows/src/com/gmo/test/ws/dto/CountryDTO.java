package com.gmo.test.ws.dto;

import java.io.Serializable;

import com.gmo.test.ws.model.Country;

/**
 * Data Transfer Object represent country data.
 * @author Gledson Oliveira
 *
 */
public class CountryDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String code;
	
	private String englishName;
	
	private String frenchName;

	public CountryDTO(Country entity) {
		this.code = entity.getCode();
		this.englishName = entity.getEnglishName();
		this.frenchName = entity.getFrenchName();
	}
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getFrenchName() {
		return frenchName;
	}

	public void setFrenchName(String frenchName) {
		this.frenchName = frenchName;
	}
	
	

}
