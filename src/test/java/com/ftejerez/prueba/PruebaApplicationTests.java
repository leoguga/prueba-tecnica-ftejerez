package com.ftejerez.prueba;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-test.properties")
class PruebaApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void test1() throws Exception {
		this.mockMvc
				.perform(get("/event/searchFlights")
						.param("dateFrom", "2020-01-01")
						.param("dateTo", "2020-01-02")
						.param("eventType", "SOLO"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("[]"));
	}

	@Test
	void test2() throws Exception {
		this.mockMvc
				.perform(get("/event/searchFlights")
						.param("dateFrom", "2021-03-14")
						.param("dateTo", "2021-03-29")
						.param("eventType", "SOLO"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$.[?(@.idEvent == 1 && @.startDate == \"2021-03-14-15.30.00\" && @.endDate == \"2021-03-14-16.17.00\" && @.resource == \"EC-IOT\" && @.eventType == \"SOLO\" && @.captain == \"cesar\")]").exists())
				.andExpect(jsonPath("$.[?(@.idEvent == 2 && @.startDate == \"2021-03-15-09.00.00\" && @.endDate == \"2021-03-15-09.59.00\" && @.resource == \"EC-JFC\" && @.eventType == \"SOLO\" && @.captain == \"real\")]").exists())
				.andExpect(jsonPath("$.[?(@.idEvent == 3 && @.startDate == \"2021-03-21-09.37.00\" && @.endDate == \"2021-03-21-10.29.00\" && @.resource == \"EC-IOT\" && @.eventType == \"SOLO\" && @.captain == \"martin\")]").exists());
	}

	@Test
	void test3() throws Exception {
		this.mockMvc
				.perform(get("/event/searchFlights")
						.param("dateFrom", "2021-04-04")
						.param("dateTo", "2021-04-05")
						.param("eventType", "DUAL"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$.[?(@.idEvent == 4 && @.startDate == \"2021-04-04-15.30.00\" && @.endDate == \"2021-04-04-17.15.00\" && @.resource == \"B-737\" && @.eventType == \"DUAL\" && @.captain == \"evans\")]").exists());
	}

}
