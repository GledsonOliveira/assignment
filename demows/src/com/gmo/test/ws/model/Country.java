package com.gmo.test.ws.model;


import java.io.Serializable;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

/**
 * MongoDB Collection Reference
 * @author Gledson Oliveira
 *
 */
public final class Country implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ObjectId id;
	
	@BsonProperty(value = "Code")
	private String code;
	
	@BsonProperty(value = "English Name")
	private String englishName;
	
	@BsonProperty(value = "French Name")
	private String frenchName;

	public ObjectId getId() {
		return id;
	}
	
	public void setId(ObjectId id) {
		this.id = id;
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
