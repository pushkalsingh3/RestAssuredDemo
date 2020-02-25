package com.pushkal.restClient;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {
	
	public RequestSpecification createRequestSpecification(String contentType) {
		RequestSpecification reqSpec = RestAssured.given();
		if (contentType != null) {
			if (contentType.equalsIgnoreCase("JSON")) {
				reqSpec.contentType(ContentType.JSON);
			} else if (contentType.equalsIgnoreCase("XML")) {
				reqSpec.contentType(ContentType.XML);
			} else if (contentType.equalsIgnoreCase("TEXT")) {
				reqSpec.contentType(ContentType.TEXT);
			}
		}
		return reqSpec;
	}
	
	public void setBaseURI(RequestSpecification reqSpec, String baseURI) {
		if(!baseURI.isEmpty() && reqSpec!=null) {
			try {
				reqSpec.baseUri(baseURI);
			} catch (Exception e) {
				// TODO: handle exception\
				System.out.println("Unable to set baseURI in request specification :"+e.getMessage());
			}
			
		}
	}
	
	public void setBasePath(RequestSpecification reqSpec, String basePath) {
		if(!basePath.isEmpty() && reqSpec!=null) {
			try {
				reqSpec.basePath(basePath);
			} catch (Exception e) {
				// TODO: handle exception\
				System.out.println("Unable to set basePath in request specification :"+e.getMessage());
			}
			
		}
	}
	
	public void addHeader(RequestSpecification reqSpec, Map<String, String> headers) {
		if(!headers.isEmpty()||headers!=null && reqSpec!=null) {
			try {
				reqSpec.headers(headers);
			}
			catch (Exception e) {
				// TODO: handle exception
				System.out.println("Unable to set headers in request specification :"+e.getMessage());
			}
		}
			
		
	}
	
	public void addAuthorization(RequestSpecification reqSpec, String token) {
		if(!token.isEmpty() && reqSpec!=null) {
			try {
				reqSpec.auth().oauth2(token);
			}
			catch (Exception e) {
				// TODO: handle exception
				System.out.println("Unable to set OAuth 2 token in request specification :"+e.getMessage());
			}
		}
		
	}
	
	public void addBody(RequestSpecification reqSpec, String body) {
		if(!body.isEmpty() && reqSpec!=null) {
			try {
				reqSpec.body(body);
			}
			catch (Exception e) {
				// TODO: handle exception
				System.out.println("Unable to set body in request specification :"+e.getMessage());
			}
		}
		
	}
	
	public Response executeRequest(String httpMethod, String baseURI, String basePath, String token,
			Map<String, String> headers, String body) {
		RequestSpecification reqSpec = createRequestSpecification();
		setBaseURI(reqSpec, baseURI);
		setBasePath(reqSpec, basePath);
		addAuthorization(reqSpec, token);
		addHeader(reqSpec, headers);
		
		Response response = null;
		switch (httpMethod) {
		case "GET":
			response = reqSpec.get();
			break;
		case "POST":
			response = reqSpec.post();
			break;
		case "PUT":
			response = reqSpec.put();
			break;
		case "DELETE":
			response = reqSpec.delete();
			break;

		default:
			System.out.println("Please pass the corrent http method....");
			break;
		}
		
		return response;
	}
	
	
	
}
