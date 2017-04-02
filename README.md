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
You need to have oracle java (JDK 1.8+) and Apache Maven installed in your box. In order oo build the project, go to project directory and use: 

    mvn clean install

You could provide the type of driver that you need to run the tests against passing JVM arg `-Dwebdriver.type`. The supported values are `Headless`, `Chrome`, `Firefox`. You also need to provide paths to driver if you select Chrome or Firefox. 

For example, if you wish to use Chrome:

`-Dwebdriver.type=Chrome` `-Dwebdriver.path=<path-to-driver>`

The headless mode uses a driver called JBrowserDriver. There are two known limitations in Headless mode. The screen shot contains garbled characters, the other one is getting text from elements intermittently return empty value.

### Dependencies ###
An extension of the BDD test library called yatspec (https://github.com/bodar/yatspec)  has been used in the project. The source code is at *https://github.com/doeritlk/yatspec-ext.git*.
