package com.pushkal.restClient;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RestClient {
	
	public RequestSpecification createRequestSpecification() {
		RequestSpecification reqSpec = RestAssured.given();
		return reqSpec;
	}
	
	public boolean setBasePath(String basePath) {
		if(basePath!=null||!basePath.isEmpty()) {
			try {
				RestAssured.basePath = basePath;
				return true;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Found Exception :"+e.getMessage());
				return false;
			}
			
		}
		else {
			return false;
		}
	}
	
	public void addHeader(RequestSpecification reqSpec, Map<String, String> headers) {
		reqSpec.headers(headers);
	}
	
	public void addBody(RequestSpecification reqSpec, String body) {
		reqSpec.body(body);
	}
	
	
	
	
	
}
