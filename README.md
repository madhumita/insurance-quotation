# insurance-quotation
Spring Boot/ Spring data project with Rest Api for a Customer to request a Quote
It uses inmemory DB H2.
The commands to build it using maven and deploy it in Tomcat that comes with spring boot starter:

mvn clean install      (optional)
mvn spring-boot:run -Dserver.port=8383    (port where we want tomcat to run

The application contains a data.sql that helps to insert initial data in appropriate tables.

Following are the availble apis(assuming we are running the default tomcat provided by spring boot on localhost):


<h2>REST API(s) exposed by the Application</h2>

<h3>Api to retrieve quote for Customer with Insurers eligible for Panel:</h3>
<p>
URL:
http://localhost:8383/rest/api/customer/quotation
</p>
<br></br>
<p>
Sample JSON
{ 
    "name":"Madhu",
    "postCode":"2007",
    "occupation":"BARBER",
    "turnOver" : 116000
 }
</p>
<br></br>
<p>
<h3>To help the application to achieve the functionality of above API following apis are available:</h3>

<h3>Get all Insurers in DB:</h3>

<p>Url: http://localhost:8383/rest/api/insurer/getInsurers</p>

<h3>Get all PostCodes in DB:</h3>

<p>Url: http://localhost:8383/rest/api/insurer/getPostCodes</p>

<h3>Get all Occupations in DB:</h3>

<p>Url: http://localhost:8383/rest/api/insurer/getOccupations</p>

<h3>Insert Multiple Insurers :</h3>

<p>Url:
http://localhost:8383/rest/api/insurer/addMultiple</p>
<p>Sample JSON:</p>
<p>
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
</p>

<h3>Insert Single Insurer</h3>
<p>URL:
http://localhost:8383/rest/api/insurer</p>

<p>Sample JSON for storing some Insurers before Customer requests quotation:</p>

<p>
{
	"name":"InsurerB",
	"website":"www.insurerB.com",
	"excludedPostCodes":[{"postCode":2005},{"postCode":2100}],
	"excludedOccupations":[{"occupation":"ASTRONAUT"}],
	"minimumTurnOver":126000
}

</p>


<p>
<h3>The Project has certain drawbacks and may be improved as follows :</h3>

<p>*Test Coverage is pending for unit and integration tests</p>
<p>* Detailed Custom Error /Exception Framework</p>
<p>*Addition of more apis or functionalities to better handle crud operations of insurer </p>
<p>*No authorization or security has been implemented yet</p>
<p>*Unable to insert insurers with duplicate postcode or occupation that already exist in DB (known bug)</p>
<p>*Test resources separation from main resources</p>
</p>


