package testutils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jayway.jsonpath.*;

public class RestAPI {

	public static String setRequestTemplateforJson(String filepath) throws IOException {

		BufferedReader bf = new BufferedReader(new FileReader(filepath));

		StringBuilder sb = new StringBuilder();

		String line = bf.readLine();

		while (line != null) {

			sb.append(line);
			sb.append(System.lineSeparator());
			line = bf.readLine();
		}

		String template = sb.toString();

		return template;

	}
	
	
	public static String SetValuestoTemplateJson(String Json, HashMap<String, Object> values) {

		Set<String> keys = values.keySet();

		String jsonstring = Json;
		for (String string : keys) {

			DocumentContext doc = com.jayway.jsonpath.JsonPath.parse(jsonstring).set("$." + string, values.get(string));

			jsonstring = doc.jsonString();

		}

		return jsonstring;
	}
	
	public static String SetValuetoTemplateJson(String Json, String path, String value) {

		DocumentContext doc = com.jayway.jsonpath.JsonPath.parse(Json).set("$." + path, value);
		
		return doc.jsonString();
	}
	
	public static Response callPostMethod(String baseURI, String path, HashMap<String, String> header,
			String Request)
	{
		RestAssured.baseURI = baseURI;
		
		
		Response res = RestAssured.given().body(Request).when().contentType(ContentType.JSON).headers(header).post(path);
		
	
		return res;
		
	}
	
	public static Response callgetMethod(String baseURI, String path,
			String Qp,String value)
	{
		RestAssured.baseURI = baseURI;
		
		HashMap<String,String> qparam = new HashMap<String,String>();
		qparam.put(Qp, value);
		
		System.out.println("Base"+baseURI);
		
		Set<String> sa = qparam.keySet();
		for (String string : sa) {
			System.out.println(qparam.get(string));
		}
		
		Response res = RestAssured.given().queryParameters(qparam).when().get(path);
		
	
		return res;
		
	}
	
	public static Response callputMethod(String baseURI, String path, HashMap<String, String> header,
			String Request)
	{
		RestAssured.baseURI = baseURI;
		
		Response res = RestAssured.given().body(Request).when().contentType(ContentType.JSON).headers(header).put(path);
		
		return res;
		
	}
	
	public static Object getData(Response res, String path) {

		

		Object data = res.getBody().jsonPath().get(path);

		System.out.println(data.toString());
		return data;
		
		
	}


}
