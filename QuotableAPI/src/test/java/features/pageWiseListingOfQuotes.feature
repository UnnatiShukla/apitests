Feature: Testing QuotableAPI's List Quotes based on page values 

@ListQuotesPageWise
Scenario:
Verify whether User Gets the First of quotes, with 20 results per page when no page value is passed

	Given User has set QuotableAPI endpoint for fetching quotes 
	When No Page Value is Passed in HTTP Request 
	And User Sends "GET" HTTP request to "ListQuotesAPI"
	Then User should receive page 1 of quotes by default

@ListQuotesPageWise
Scenario Outline:
Verify whether User Gets the Nth page of quotes, with n results per page depending on the page user has requested 

	Given User has set QuotableAPI endpoint for fetching quotes 
	When Page Value <PageNo> is Passed in HTTP Request 
	And User Sends "GET" HTTP request to "ListQuotesAPI"
	Then User should receive page <ExpectedPageNo> of quotes 
	And User should receive <ExpectedResultsPerPage> results per page with page number <PageNo>
	
	#General Checks
	And User should receive HTTP Response Status Code "200" 
	And User should receive HTTP Response Content in "JSON" format
	And HTTP Response Parameter "totalPages" value should be <ExpectedTotalPages>
	And HTTP Response Parameter "lastItemIndex" value should be <ExpectedLastItemIndex> with Page number <PageNo> requested
	And HTTP Response Parameter "tags" list within each resultant quote should never be empty
	And HTTP Response Parameter within each resultant quote should NOT have NULL value
	And HTTP Response Parameter author-slug value within each resultant quote should be a valid slug-string of author
	And HTTP Response Parameter length value within each resultant quote should be a correct based on the content returned
	And HTTP Response Parameter "dateAdded" value within each resultant quote should be a valid Date in format "YYYY-MM-DD"
	And HTTP Response Parameter "dateModified" value within each resultant quote should be a valid Date in format "YYYY-MM-DD"
	
Examples:
|Example| PageNo | ExpectedPageNo 			| ExpectedResultsPerPage 	| ExpectedTotalCount 	| ExpectedTotalPages 	| ExpectedLastItemIndex | 
|	2 	| 1      | 1 						| 20			  			|  1885 				| 95 					| 20 					|
|	3	| 2      | 2 						| 20			  			|  1885 				| 95 					| 40 					|
|	4	| 10     | 10			 			| 20       	  				|  1885 				| 95 					| 200 					|
|	5	| 94 	 | 94			 			| 20       	  				|  1885 				| 95 					| 1880 					|
|	6	| 95 	 | 95			 			| 5	       	  				|  1885 				| 95 					| 0 					|
|	7	| -1	 | 1			 			| 20	       	  			|  1885 				| 95 					| 20 					|
		
@ListQuotesPageWise	@SmokeTest	
Scenario:
Verify whether User tries to get the Page which is greater than total number pages, request will not return any results

	Given User has set QuotableAPI endpoint for fetching quotes 
	When Page Value 96 is Passed in HTTP Request 
	And User Sends "GET" HTTP request to "ListQuotesAPI"
	Then request should not return any results
	
	#General Checks
	And User should receive HTTP Response Status Code "200" 
	And User should receive HTTP Response Content in "JSON" format
	And HTTP Response Parameter "totalPages" value should be 95
	And HTTP Response Parameter "lastItemIndex" value should be 0 with Page number 96 requested
	And User should receive 0 results per page with page number 96	
	
	