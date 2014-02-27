# Jersey + Arquillian + REST-assured

Demonstrates how the framework REST-assured can be used together with Arquillian to test Restful Web Services.

In the test case, Arquillian handles the deployment of the REST service and runs the actual test cases in a separate JVM (runs as client).
REST-assured is used to invoke the service while JUnit performs the actual assertions.

Run the test case by executing: mvn test<br/>
Run the web app in an embedded Tomcat 7 by executing: mvn tomcat7:run<br/>

**Links**<br/>
REST-assured: http://code.google.com/p/rest-assured/<br/>
Arquillian: http://arquillian.org/<br/>

**Author**<br/>
[Tommy Tynjä](http://twitter.com/tommysdk)
