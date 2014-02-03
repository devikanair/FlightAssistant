The Bon Voyage travel assistant helps you retrieve details of your flights quickly by providing just the flight number and travel date. It provides details like the number of stops, arrival and departure airports, terminals and time information. It also gives carrier contact information. If your flight is in the next few days, the weather prediction is also provided, so that you can make your travel arrangements accordingly.

About the authors:
Devika Nair Syama (devika.nair.syama@sv.cmu.edu)
Karthika Purushothaman (karthika.purushothaman@sv.cmu.edu)

Technical details:
The jsp form sends user input to a FlightInfoServlet. This servlet invokes functions to call external webservices for flight information and weather information when necessary. The obtained information is populated in a session variable and passed to another page which displays the results to the user. This can be easily extended to provide additional functionality.
This application requires tomcat and has been currently compiled with java 7. The source files are attached, so you should be able to use java6 and above. All dependencies are attached.

Running it in eclipse:
The attached zip is an eclipse project. You can import it to your eclipse workspace and perform "Run As-> Run on Server". This should launch localhost:port/FlightAssistant, which is the home page of the application.

Running the war file:
The war file is also attached, so that you can add it to the webapps folder of any tomcat and run it.

Launching the application:
The application home page can be found at http://hostname:port/FlightAssistant. If you are running tomcat if the default properties, you can see it at http://localhost:8080/FlightAssistant

Enter the flight details and date: 
Eg:
Flight Carrier Code: AA
Flight Number: 120
Year(YYYY): 2014
Month(MM): 02
Day(DD): 03
Click on GET DETAILS

This should show you the details of the flight AA120 on February 03, 2014

