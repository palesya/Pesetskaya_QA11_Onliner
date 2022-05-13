# Pesetskaya_QA11_Onliner

## Description :
Java project for test automation, covering functional, GUI, API testing of portal onliner.by.
A Maven framework with Selenium tests written in Java with Allure reports of test results.

## Stack :
- Java 11
- Maven
- Selenium
- TestNG
- Rest-assured
- Mysql-connector-java
- Allure
- Cucumber
- Selenide
- Java.net (http-client)
- Lombok

## Patterns :
- Page Object
- Page Factory
- Builder
- Chain of Invocation (Responsibility)
- Value Object

## Browsers :
- FIREFOX
- CHROME

## Architecture:
### main:

| Package/Entity Name    | Purpose      | 
| ------------- | ------------- | 
| BaseTest(BaseObjects)   |   Parenting class for all tests. Uses Listener (Utilities) that gets properties with PropertyReader(Properties), start and close driver  | 
| SelenideBaseTest(BaseObjects)   | The same as BaseTest but for Selenide tests, uses ListenerSelenide (Utilities) |
| CucumberSteps           | Contains classes with tests that use Cucumber library         | 
| Driver           | Contains classes for driver management    | 
| ChromeDriverManager(Driver)           | Creates Chrome Driver with properties    | 
| DriverManager(Driver)          | Contains methods for create, get and close driver    | 
| DriverManagerFactory(Driver)          | Contains method that returns DriverManager depending on driver type (CHROME/FIREFOX). Can be expanded. Then appropriate DriverManagers should be created (e.g. IEDriverManager)     | 
| FirefoxDriverManager(Driver)           | Creates Firefox Driver with properties    | 
| Entity          | Contains Cart and User classes that use Lombok library for further object creation        | 
| PageObjects         | Pages that are created based on BasePage using pattern PageObject         | 
| BasePage (PageObjects) | Abstract class for all PageObjects. Contains main methods such as open, getWebElement, click, isElementExists, getProperty, getText and so on |
| Property Reader (Properties)  |  Read properties from .properties file. These properties are used in Listener.  | 
| Listener (Utilities)   | Implements ITestListener. Contains method onStart that set date format for "logger-time", uses propertyReader to get properties from .properties files, sets driver. OnFinish closes driver         | 
| ListenerSelenide (Utilities)   | The same as Listener but for Selenide tests |
| HttpHelper (Utilities)   | Abstract parenting class for tests that uses http client creation |
| RestAssuredHelper (Utilities)   | Abstract parenting class for tests that uses Rests assured library |
| resources package  | Contains .properties files with main data for tests (e.g. type of driver, base url and so on) |

### test:

| Package/Entity Name    | Purpose      | 
| ------------- | ------------- | 
| OnlinerTests   | Classes with tests |
| Request   | Contains .json file with requests that are used in tests |
| feature (resources)   | Contains .feature files for Cucumber tests  |
| allure.properties (resources)   | Configurations for allure reporting  |
| log4j.properties (resources)   | Configurations for log4j reporting  |
| .xml files (resources)   | Are used to run tests  |


### other:

| Package/Entity Name    | Purpose      | 
| ------------- | ------------- | 
| Jenkinsfile   | Is used to run tests in Jenkins |
| .gitignore   | Contains files and folders that should not be reported in github|
| pom.xml   | Contains information about the project and configuration details used by Maven to build the project |
| PostmanCollections   | .json files of postman collections used in project |

## Logs :
All tests write logs while they run which provide further information about what happened if they failed. These files reside in the ./logs directory and can be viewed by navigating to that folder.

## Screenshots :
Screenshots are made for test with issue and are located in the directory: src/test/java/Screenshots

## Test cases :
link to test-cases: https://docs.google.com/spreadsheets/d/1tKShkrhP-pfD0D8fYY8Dg7-G_Cm1GRxNhYcHV54QrzA/edit#gid=0

## How to run tests :
Test runs are configured in src/test/resources/{name}.xml files
To see what tests are included open .xml file and see the class name, e.g. class name="OnlinerTests.TestsSelenide"
API tests (APITestsHTTPClient and APITestsRestAssured) should be run directly from test classes.


<h3 align="left"> Allure results </h3>
Results for allure reports are located in target/allure results.
To create allure-report run the following command in Terminal:

allure generate D:\Pesetskaya_QA11_Onliner\target\allure-results -c -o D:\Pesetskaya_QA11_Onliner\target\report

the first path is where results are stored, the second one is for report data



<h3 align="left">Languages and Tools:</h3>
<p align="left"> <a href="https://www.java.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="40" height="40"/> </a> <a href="https://www.jenkins.io" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/jenkins/jenkins-icon.svg" alt="jenkins" width="40" height="40"/> </a> <a href="https://postman.com" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/getpostman/getpostman-icon.svg" alt="postman" width="40" height="40"/> </a> <a href="https://www.selenium.dev" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/detain/svg-logos/780f25886640cef088af994181646db2f6b1a3f8/svg/selenium-logo.svg" alt="selenium" width="40" height="40"/> </a> </p>


