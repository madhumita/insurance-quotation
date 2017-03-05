package com.quotation.controller;

import static org.junit.Assert.assertEquals;

import java.net.URL;
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
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.quotation.TestDataUtil;
import com.quotation.entity.Insurer;
import com.quotation.model.CustomerQuery;

/**
 * This is endtoend integration test of the Rest API using Spring boot to start
 * server
 * 
 * @author MADHUMITA
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class InsurerApiControllerTest {

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
	public void testAddInsurer() throws Exception {
		Insurer insurer1 = TestDataUtil.getInsurer("2000", "1200", "PAINTER",
				"Insurer1", 190000);
		ResponseEntity<Insurer> responseAfterFirstInsert = template
				.postForEntity(base.toString(), insurer1, Insurer.class);
		Insurer insurer2 = TestDataUtil.getInsurer("2010", "1201", "BUTCHER",
				"Insurer2", 160000);
		ResponseEntity<Insurer> responseAfterSecondInsert = template
				.postForEntity(base.toString(), insurer2, Insurer.class);

		assertEquals("201", responseAfterSecondInsert.getStatusCode()
				.toString());
	}

	@Test
	public void testAddMultipleInsurers() throws Exception {
		this.base = new URL("http://localhost:" + port
				+ "/rest/api/insurer/addMultiple");
		List<Insurer> insurers = TestDataUtil.getInsurers();
		ResponseEntity<String> responseAfterFirstInsert = template
				.postForEntity(base.toString(), insurers, String.class);

		assertEquals("201", responseAfterFirstInsert.getStatusCode().toString());
		assertEquals("All Insurers Created Successfully",
				responseAfterFirstInsert.getBody().toString());

	}

}
