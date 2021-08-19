# apitests

QuotableAPI 
|-- pom.xml
 -- src
    |-- main
    |   `-- java
    |       `-- POJO [$package contains POJO classes to handle serialization/deserialization mapping of request response abstractly
    |-- test
    |   `-- java
    |       `-- cucumberOptions [$package contains Test Runner Files
    |       `-- features [$package contains Feature files with scenarios listed with parameter examples
    |       `-- stepDefinitions [$package contains Step definitions file - having technical steps written via each methods
    |       `-- resources [$package contains Utility Files, TestData, APIData Files
    |       	`-- Utils Class - Utility File to handle generic HHTP Request spec and driving global properties
    |       	`-- APIPath Enum Class - Manages Endpoint Paths
    |       	`-- global Property File - Manages property values like urls for possible test environments/credentials
    |       	`-- TestDataBuild Class - Manages API Payload if any
