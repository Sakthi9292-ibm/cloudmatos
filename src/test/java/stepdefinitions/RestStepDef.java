package stepdefinitions;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jayway.restassured.response.Response;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testutils.RestAPI;
import testutils.TestProperties;

public class RestStepDef {

	private String request = null;
	private Response response = null;
	private String BE_URL = "";
	RestAPI restutils = new RestAPI();

	TestProperties tp = new TestProperties();

	@Given("Client system is pointing to {string}")
	public void client_system_is_pointing_to(String string) {
		
		BE_URL = string;
	   
	}


	@When("Client triggers the request to get service {string} with id {string}")
	public void client_triggers_the_request_to_get_service_with_id(String string, String string2) {
		response= restutils.callgetMethod(BE_URL, string,"id","2");
		
		System.out.println("GetRepsonse "+response.then().log().body());
	}
	@Then("Server should respond with {string} and values")
	public void server_should_respond_with_and_values(String string, io.cucumber.datatable.DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
		
		assertEquals(response.statusCode(), Integer.parseInt(string));
		
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
	    
	    for (Map<String, String> columns : rows) {
	       
	    	System.out.println(columns.get("jsonpath"));
	    	System.out.println(columns.get("values"));
	    	
	    	Object data = restutils.getData(response,columns.get("jsonpath"));
	    	
	    	
	    	
//	    	assertEquals(columns.get("values").toString(), data.toString());
	    	
	    }
		
	   
	}

}
