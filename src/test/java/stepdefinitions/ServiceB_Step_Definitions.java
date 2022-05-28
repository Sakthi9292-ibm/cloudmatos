package stepdefinitions;

import static org.junit.Assert.assertEquals;
//import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.testng.asserts.SoftAssert;

import com.jayway.restassured.response.Response;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import testutils.RestAPI;
import testutils.TestProperties;

public class ServiceB_Step_Definitions {

	private String request = null;
	private Response response = null;
	private String BE_URL = "";
	RestAPI restutils = new RestAPI();

	TestProperties tp = new TestProperties();

	@Given("user is trying to access service B with authentication")
	public void user_is_trying_to_access_service_b_with_authentication() {

	}
	
	@When("user calls service B with methodtype {string}")
	public void user_calls_service_b_with(String string) throws IOException {
		request = null;
		response = null;
		HashMap header = new HashMap<String, String>();
		header.put("Content-Type", "application/json");
		header.put("Authorization", "Basic qweo1j23o1j231=");
		request = restutils.setRequestTemplateforJson("src/test/resources/templates/ServiceB_schema.txt");
		System.out.println("Request is : " + request);
		if (string == "POST") {
			response = restutils.callPostMethod(tp.readConfig("serviceB_endpoint"), "v1", header, request);
		} else {
			response = restutils.callputMethod(tp.readConfig("serviceB_endpoint"), "v1", header, request);
		}

	}

	@Then("Service B should respond with {string} response code")
	public void service_b_should_respond_with_response_code(String string) {

		assertEquals(response.getStatusCode(), Integer.parseInt(string));
	}

	@Given("user is trying to access service B without authentication")
	public void user_is_trying_to_access_service_b_without_authentication() throws IOException {
		request = null;
		response = null;
		HashMap header = new HashMap<String, String>();
		header.put("Content-Type", "application/json");
		request = restutils.setRequestTemplateforJson("src/test/resources/templates/ServiceB_schema.txt");
		System.out.println("Request is : " + request);

		response = restutils.callPostMethod(tp.readConfig("serviceB_endpoint"), "v1", header, request);

	}

	@Then("Service B should send {string} reponsecode")
	public void service_b_should_send_reponsecode(String string) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(), Integer.parseInt(string));
	}

	@When("user calls service B with data")
	public void user_calls_service_b_with_data(io.cucumber.datatable.DataTable dataTable) throws IOException {

		request = restutils.setRequestTemplateforJson("src/test/resources/templates/ServiceB_schema.txt");

		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

		for (Map<String, String> columns : rows) {

			request = restutils.SetValuetoTemplateJson(request, columns.get("tag").toString().replace("\"", ""),
					columns.get("value"));

		}

		HashMap header = new HashMap<String, String>();
		header.put("Content-Type", "application/json");
		header.put("Authorization", "Basic qweo1j23o1j231=");

		response = restutils.callPostMethod(tp.readConfig("serviceB_endpoint"), "v1", header, request);
	}

	@Then("service B should response with")
	public void service_b_should_response_with(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

		for (Map<String, String> columns : rows) {

			String data = restutils.getData(response, columns.get("tag").toString().replace("\"", "").toString())
					.toString();

			Assert.assertEquals(columns.get("value"), data);
		}

	}

}
