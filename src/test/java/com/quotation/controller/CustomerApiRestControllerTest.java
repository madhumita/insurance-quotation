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

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**This is endtoend integration test of the Rest API using Spring boot to start server
 * FIXME Tests are passing individually but failing in a suite
 * @author MADHUMITA
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
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

    @Test
    public void getEligibleInsurersTestwhenPanelhasNoInsurers() throws Exception {
    	CustomerQuery query = TestDataUtil.getRequestQuery();
    	Insurer insurer = TestDataUtil.getInsurer("2000","1200","PAINTER","Insurer1",190000);
    	ResponseEntity<Insurer> responseAfterInsert = template.postForEntity(base.toString(), insurer, Insurer.class);
    	this.base = new URL("http://localhost:" + port + "/rest/api/customer/quotation");
        ResponseEntity<Map> response = template.postForEntity(base.toString(), query, Map.class);
        assertEquals("204",response.getStatusCode().toString());
    }
    
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