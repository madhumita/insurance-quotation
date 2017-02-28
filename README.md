# insurance-quotation
Spring Boot Spring data project with Rest Api for a Customer to request a Quote
It uses inmemory DB H2.
The commands to build it using maven is and deploy it in Tomcat that comes with spring boot starter:

mvn clean install      (optional)
mvn spring-boot:run -Dserver.port=8383    (port where we want tomcat to run


Following are the availble apis(asuming we are running the default tomcat provided by spring boot on localhost):

Insert Insurer 

URL:
http://localhost:8383/rest/api/insurer

Sample JSON for storing some Insurers before Customer requests quotation:

{
	"name":"InsurerB",
	"website":"www.insurerB.com",
	"excludedPostCodes":[{"postCode":2005},{"postCode":2100}],
	"excludedOccupations":[{"occupation":"Barber"}],
	"minimumTurnOver":126000
}


Get all Insurers in DB

http://localhost:8383/rest/api/insurer/get

Finally the api to retrieve quote for Customer with Insurers eligible for Panel

http://localhost:8383/rest/api/customer/quotation

Sample JSON
{ 
    "name":"Madhu",
    "postCode":"2007",
    "occupation":"Barber",
    "turnOver" : 116000
 }


The Project is under construction with TODO items as follows:

#Test Coverage is pending for unit and integration tests
#No authorization or security has been implemented yet
#Unable to insert insurers with duplicate postcode or occupation that already exist in DB (known bug)



