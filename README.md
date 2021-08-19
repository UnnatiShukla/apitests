# apitests


This is automation suite is designed to test the List Quotes API and generates HTML report. The project has scenarios that queries List Quotes API to get results pagewise within the feature files: src/test/resources/pageWiseListingOfQuotes.feature

Technology used to build this:
- Java 1.8+
- Maven 
- Eclipse 
- Cucumber
- Testng
- For HTMl Repots - damianszczepanik / maven-cucumber-reporting


In order to execute the automation suite I have used Jenkins - execution logs can be found at https://github.com/UnnatiShukla/apitests/blob/main/PageWiseTests.pdf Repo path

For Scenarios will be executed. Report file can be found '\target\cucumber-html-reports/overview-features.htm' 

Note: scenarios need to have the tag in order to be triaged in test executions

Source Repo Structure info : 

QuotableAPI
- pom.xml
- src
    - main
        - java
           - POJO [$package contains POJO classes to handle serialization/deserialization mapping of request response abstractly
    - test
        - java
          - cucumberOptions [$package contains Test Runner Files
          - features [$package contains Feature files with scenarios listed with parameter examples
          - stepDefinitions [$package contains Step definitions file - having technical steps written via each methods
          -  resources [$package contains Utility Files, TestData, APIData Files
             -  Utils Class - Utility File to handle generic HHTP Request spec and driving global properties
             -  APIPath Enum Class - Manages Endpoint Paths
             -  global Property File - Manages property values like urls for possible test environments/credentials
             -  TestDataBuild Class - Manages API Payload if any
- target - This contains report files
