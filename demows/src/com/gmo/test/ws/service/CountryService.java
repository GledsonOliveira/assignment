package com.gmo.test.ws.service;

import static com.mongodb.client.model.Filters.eq;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.gmo.test.ws.dto.CountryDTO;
import com.gmo.test.ws.model.Country;
import com.gmo.test.ws.mongo.connection.MongoDBConnection;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

/**
 * 
 * @author Gledson Oliveira
 *
 *	Service responsible for country references in MongoDB Collection
 *
 * 	References: https://mongodb.github.io/mongo-java-driver/4.1/
 */
@RequestScoped
@Path("/rest")
public class CountryService {

	@GET
	@Path("/country/{code}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCountry(@PathParam("code") String code ) {
		
		MongoCollection<Country> collection = MongoDBConnection.getDatabase().getCollection("countries", Country.class);
		
		Country country = collection.find(eq("Code", code.toUpperCase())).first();
		
		if (country == null) 
			return Response.status(404).build();
		
		return Response.ok(new CountryDTO(country)).build();
	}

	@GET
	@Path("/countries")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCountries() {
		MongoCollection<Country> collection = MongoDBConnection.getDatabase().getCollection("countries", Country.class);
		
		FindIterable<Country> countries = collection.find();		
		
		if (countries == null) 
			return Response.status(404).build();
		 
		List<CountryDTO> countriesDTO = StreamSupport.stream(countries.spliterator(), false).map(obj -> new CountryDTO(obj)).collect(Collectors.toList());
		
		return Response.ok(countriesDTO).build();
	}

}
