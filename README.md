# insurance-quotation
Spring Boot/ Spring data project with Rest Api for a Customer to request a Quote
It uses inmemory DB H2.
The commands to build it using maven and deploy it in Tomcat that comes with spring boot starter:

mvn clean install      (optional)
mvn spring-boot:run -Dserver.port=8383    (port where we want tomcat to run

The application contains a data.sql that helps to insert initial data in appropriate tables.

Following are the availble apis(assuming we are running the default tomcat provided by spring boot on localhost):


<h2>REST API(s) exposed by the Application</h2>

<p>Api to retrieve quote for Customer with Insurers eligible for Panel:

URL:
http://localhost:8383/rest/api/customer/quotation

Sample JSON
{ 
    "name":"Madhu",
    "postCode":"2007",
    "occupation":"BARBER",
    "turnOver" : 116000
 }
</p>

<p>
To help the application to achieve the functionality of above API following apis are available:

Get all Insurers in DB:

Url: http://localhost:8383/rest/api/insurer/getInsurers

Get all PostCodes in DB:

Url: http://localhost:8383/rest/api/insurer/getPostCodes

Get all Occupations in DB:

Url: http://localhost:8383/rest/api/insurer/getOccupations

Insert Multiple Insurers :

Url:
http://localhost:8383/rest/api/insurer/addMultiple

[{
	"name":"InsurerA",
	"website":"www.insurerA.com",
	"excludedPostCodes":[{"postCode":2106},{"postCode":2212}],
	"excludedOccupations":[{"occupation":"PAINTER"}],
	"minimumTurnOver":195000
},
{
	"name":"InsurerC",
	"website":"www.insurerC.com",
	"excludedPostCodes":[{"postCode":2018},{"postCode":2213}],
	"excludedOccupations":[{"occupation":"ANALYST"}],
	"minimumTurnOver":127000
}

]


Insert Single Insurer
URL:
http://localhost:8383/rest/api/insurer

Sample JSON for storing some Insurers before Customer requests quotation:

{
	"name":"InsurerB",
	"website":"www.insurerB.com",
	"excludedPostCodes":[{"postCode":2005},{"postCode":2100}],
	"excludedOccupations":[{"occupation":"ASTRONAUT"}],
	"minimumTurnOver":126000
}

</p>


<p>
The Project has certain drawbacks and may be improved as follows :

<p>*Test Coverage is pending for unit and integration tests</p>
<p>* Detailed Custom Error /Exception Framework</p>
<p>*Addition of more apis or functionalities to better handle crud operations of insurer </p>
<p>*No authorization or security has been implemented yet</p>
<p>*Unable to insert insurers with duplicate postcode or occupation that already exist in DB (known bug)</p>
<p>*Test resources separation from main resources</p>
</p>


