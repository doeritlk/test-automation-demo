# Automation Test Demos #

This is a Selenium Webdriver based automated test framework designed for demonstration purposes. The source code is at *https://github.com/doeritlk/test-automation-demo.git*. 

This framework contains:

* Parameterised Table tests - Annotate the tests with `@Table` and test data in `@Row` with columns, external data sources could be plugged in if requires; using table cell values as keys 

* Test Summary in an HTML page - Once tests run, results and the summary will be rendered into an HTML page

* Browser choices - Chrome, Firefox and Headless

* Gerkin language inspired tests - `Given`, `When`, `Then` are java method stubs which are translated in the HTML page.

* Screenshot capture - Captured screenshot will be attached to the HTML page
Web Page Object oriented tests - Test should interact with pages as objects, the signup test is a good example.

* Optional Sequence diagram support - This library can generate a sequence diagram using the hints provided, this is useful if we your system interacts with have multiple dependent systems. 


### How do I get set up? ###
You need to have latest Oracle Java (JDK 1.8+ client VM) and Apache Maven installed in your box. If you use server JDK/JRE, that will cause issues when running tests in headless mode.
 
 In order to build the project, go to project directory and use: 

    mvn clean install

That will buid and run all tests in the project. 

The default `webdriver.type` is set to `Chrome`. You could change the type of driver that you need to run the tests against passing JVM arg `-Dwebdriver.type`. The supported values are `Headless`, `Chrome`, `Firefox`.

For example, if you wish to use Headless driver:

`-Dwebdriver.type=Headless`

The headless mode uses a driver called JBrowserDriver. The version which has been used in this project is not an official release. This is because current release has some known issues which prevent getting element values correctly.

The drivers are in `test-automation-demo/drivers` and they are for the Mac OS. If you wish to use the demo on other platforms, please download OS specific driver, copy to  `test-automation-demo/drivers` directory and update the profile in `pom.xml` to refer to the new driver.

### Dependencies ###
An extension of the BDD test library called yatspec (https://github.com/bodar/yatspec)  has been used in the project. The source code is at *https://github.com/doeritlk/yatspec-ext.git*.
