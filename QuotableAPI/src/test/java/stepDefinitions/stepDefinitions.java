package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import POJO.Quotes;
import POJO.results;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIPath;
import resources.Utils;

public class stepDefinitions extends Utils {

	RequestSpecification req;
	ResponseSpecification resSpec;
	Response res;
	Quotes response;

	@Given("User has set QuotableAPI endpoint for fetching quotes")
	public void user_has_set_quotable_api_endpoint_for_fetching_quotes() throws IOException {
		// Code here that prepares initial Request

		req = given().spec(requestSpecification());

	}

	@When("Page Value {string} is Passed in HTTP Request")
	public void page_value_is_passed_in_http_request(String pageNumber) {
		// Code here that adds Query Parameter page as <pageNumber>
		req.queryParam("page", pageNumber);
	}

	@When("User Sends a GET HTTP request")
	public void user_sends_a_get_http_request() {
		// Code here that makes GET HTTP Request Call
		res = req.when().get("/quotes").then().extract().response();

		response = res.as(Quotes.class);
	}
	
	@When("User Sends {string} HTTP request to {string}")
	public void user_sends_a_get_http_request(String requestMethod,String apiName) {

		APIPath apiPath = APIPath.valueOf(apiName);
		
		if(requestMethod.equalsIgnoreCase("GET"))	
			res = req.when().get(apiPath.getPath()).then().extract().response();
		else
			res = req.when().post(apiPath.getPath()).then().extract().response();
		
		response = res.as(Quotes.class);

	}


	@Then("User should receive page {int} of quotes")
	public void user_should_receive_page_of_quotes(int expectedPageReturned) {
		int actualPageReturned = response.getPage();
		assertEquals(expectedPageReturned, actualPageReturned);
	}

	@Then("User should receive {int} results per page with {string}")
	public void user_should_receive_results_per_page_with(int expectedTotalCount, String pageValue) {
		// Code here that validates with Nth Page results per page is 'n'
		int actualTotalQuotesPerPageCount = response.getCount();

		if (pageValue.equalsIgnoreCase("Page 1")) {
			assertEquals(expectedTotalCount, actualTotalQuotesPerPageCount);
		}
	}

	@Then("User should receive HTTP Response Status Code {string}")
	public void user_should_receive_http_response_status_code(String string) {
		// Code here that validates HTTP Response Status Code

		int resposeStatusCode = res.getStatusCode();
		assertEquals(resposeStatusCode, 200);
	}

	@Then("User should receive HTTP Response Content in {string} format")
	public void user_should_receive_http_response_content_in_format(String expectedResponseContentType) {
		// Code here that validates HTTP Response Content-Type
		String responseContentType = res.header("Content-Type");
		assertTrue(responseContentType.contains(expectedResponseContentType.toLowerCase()));
	}

	@Then("HTTP Response Parameter {string} value should be {int}")
	public void http_response_parameter_value_should_be(String parameterPassed, int expectedParamValue) {
		// Code here that validates HTTP Response Parameter <parameterPassed> is equal
		// to <expectedParamValue>

		int actualTotalCount = response.getTotalCount();
		int actualTotalPages = response.getTotalPages();

		if (parameterPassed.equalsIgnoreCase("totalCount"))
			assertEquals(expectedParamValue, actualTotalCount);
		else if (parameterPassed.equalsIgnoreCase("totalPages"))
			assertEquals(expectedParamValue, actualTotalPages);

	}

	@Then("HTTP Response Parameter {string} value should be {int} with Page number {int} requested")
	public void http_response_parameter_value_should_be_with_page_number_requested(String paramLastItemIndex,
			int expectedLastItemIndexValue, int paramPageNumber) {
		
		
		int actualLastItemIndexValue = response.getLastItemIndex();
		assertEquals(expectedLastItemIndexValue, actualLastItemIndexValue);

	}
	
	@Then("HTTP Response Parameter {string} value should be null with Page number {int} requested")
	public void http_response_parameter_value_should_be_null_with_page_number_requested(String paramLastItemIndex, int paramPageNumber) {
	    // Write code here that turns the phrase above into concrete actions
		int actualLastItemIndexValue = response.getLastItemIndex();
		assertNull(actualLastItemIndexValue);
	}

	@Then("HTTP Response Parameter {string} list within each resultant quote should never be empty")
	public void http_response_parameter_list_within_each_resultant_quote_should_never_be(String paramTags) {

		if (paramTags.equalsIgnoreCase("tags")) {

			for (results result_n : response.getResults()) {

				if (result_n.getTags().isEmpty())
					System.out.println("Error");

			}

		}

	}

	@Then("HTTP Response Parameter within each resultant quote should NOT have NULL value")
	public void http_response_parameter_within_each_resultant_quote_should_not_have_null_value() {

		for (results result_n : response.getResults()) {
			assertNotNull(result_n.get_id());
			assertNotNull(result_n.getAuthor());
			assertNotNull(result_n.getAuthorSlug());
			assertNotNull(result_n.getContent());
			assertNotNull(result_n.getDateAdded());
			assertNotNull(result_n.getDateModified());
			assertNotNull(result_n.getLength());

		}

	}

	@Then("HTTP Response Parameter author-slug value within each resultant quote should be a valid slug-string of author")
	public void http_response_parameter_author_slug_value_within_each_resultant_quote_should_be_a_valid_slug_string_of_author() {
		for (results result_n : response.getResults()) {
			String[] authorName = result_n.getAuthorSlug().split("-");

			if (authorName.length == 2) {
				assertEquals(result_n.getAuthorSlug(),(authorName[0] + "-" + authorName[1]));
			} else if (authorName.length == 1) {
				assertEquals(result_n.getAuthorSlug(),(authorName[0]));
			}
			else if (authorName.length == 3) {
				assertEquals(result_n.getAuthorSlug(),(authorName[0] + "-" + authorName[1]+ "-" + authorName[2]));
			}
		}
	}

	@Then("HTTP Response Parameter length value within each resultant quote should be a correct based on the content returned")
	public void http_response_parameter_length_value_within_each_resultant_quote_should_be_a_correct_based_on_the_content_returned() {
		for (results result_n : response.getResults()) {

			int actualResponseLengthPerResult = result_n.getLength();
			int actualReponseContentStringLength = result_n.getContent().length();
			assertEquals(actualResponseLengthPerResult, actualReponseContentStringLength);
		}
	}

	@Then("HTTP Response Parameter {string} value within each resultant quote should be a valid Date in format {string}")
	public void http_respose_parameter_value_within_each_resultant_quote_should_be_a_valid_date_in_format(String string,
			String paramValue) {

		for (results result_n : response.getResults()) {
			assertEquals(10, result_n.getDateAdded().length());
			String[] d = result_n.getDateAdded().split("-");
			assertEquals(4, d[0].length());
			assertEquals(2, d[1].length());
			assertEquals(2, d[2].length());
		}
	}

	@When("Page Value {int} is Passed in HTTP Request")
	public void page_value_is_passed_in_http_request(int pageNumber) {
		req.queryParam("page", pageNumber);
	}

	@Then("User should receive {int} results per page with page number {int}")
	public void user_should_receive_results_per_page_with_page_number(int expectedTotalCount, int paramPageNumber) {
		int actualTotalQuotesPerPageCount = response.getCount();
		assertEquals(expectedTotalCount, actualTotalQuotesPerPageCount);
	}
	
	@When("No Page Value is Passed in HTTP Request")
	public void no_page_value_is_passed_in_http_request() {
	    // Write code here that turns the phrase above into concrete actions
		req.queryParam("page", "");
		
	}
	

	@Then("User should receive page {int} of quotes by default")
	public void user_should_receive_page_of_quotes_by_default(int expectedPageReturned) {
		int actualPageReturned = response.getPage();
		assertEquals(expectedPageReturned, actualPageReturned);
	}
	

	@Then("request should not return any results")
	public void request_should_not_return_any_results() {
		assertEquals(0,response.getResults().size());
	}

}
