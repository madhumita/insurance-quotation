package com.quotation.controller;

 
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.*;

import com.quotation.TestDataUtil;
import com.quotation.entity.Insurer;
import com.quotation.model.CustomerQuery;
import com.quotation.exception.InsurerNotFoundException;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**This is endtoend integration test of the Rest API using Spring boot to start server
 * @author MADHUMITA
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class CustomerApiRestControllerTest {
 

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
      
        this.base = new URL("http://localhost:" + port + "/rest/api/insurer");
    }

    /**Testcase for scenario where customer query returns no suitable Insurers as per business criteria
     * @throws Exception
     */
    @Test
    public void getEligibleInsurersTestwhenPanelhasNoEligibleInsurers() throws Exception{
    	CustomerQuery query = TestDataUtil.getRequestQuery();
    	Insurer insurer = TestDataUtil.getInsurer("2000","1200","PAINTER","Insurer1",190000);
    	ResponseEntity<Insurer> responseAfterInsert = template.postForEntity(base.toString(), insurer, Insurer.class);
    	this.base = new URL("http://localhost:" + port + "/rest/api/customer/quotation");
        ResponseEntity<Map> response = template.postForEntity(base.toString(), query, Map.class);
        assertEquals("204",response.getStatusCode().toString());
    }
    
    /**Testcase for scenario where customer query request is invalid and returnss ValidationError
     * @throws Exception
     */
    @Test
    public void getEligibleInsurersTestwhenValidationFails() throws Exception {
    	CustomerQuery query = TestDataUtil.getRequestQuery();
    	query.setPostCode("");
    	Insurer insurer = TestDataUtil.getInsurer("2000","1200","PAINTER","Insurer1",170000);
    	this.base = new URL("http://localhost:" + port + "/rest/api/insurer");
    	ResponseEntity<Insurer> responseAfterInsert = template.postForEntity(base.toString(), insurer, Insurer.class);
    	this.base = new URL("http://localhost:" + port + "/rest/api/customer/quotation");
        ResponseEntity<Map> response = template.postForEntity(base.toString(), query, Map.class);
        assertNotNull(response.getBody());
        assertEquals("400",response.getStatusCode().toString());
        assertTrue((response.getBody().toString()).contains("{Invalid Request:customer.postCode.empty"));
    }
    
    /**Testcase for scenario where customer query request is valid and application
     * returns a valid list of eligible insurers with quote
     * @throws Exception
     */
    @Test
    public void getEligibleInsurersTestwhenPanelContainsInsurer() throws Exception {
    	CustomerQuery query = TestDataUtil.getRequestQuery();
    	Insurer insurer = TestDataUtil.getInsurer("2000","1200","PAINTER","Insurer1",170000);
    	this.base = new URL("http://localhost:" + port + "/rest/api/insurer");
    	ResponseEntity<Insurer> responseAfterInsert = template.postForEntity(base.toString(), insurer, Insurer.class);
    	this.base = new URL("http://localhost:" + port + "/rest/api/customer/quotation");
        ResponseEntity<Map> response = template.postForEntity(base.toString(), query, Map.class);
        assertNotNull(response.getBody());
        assertEquals("200",response.getStatusCode().toString());
        assertEquals("{Insurer1=200000.0}",response.getBody().toString());
    }
     
   
}