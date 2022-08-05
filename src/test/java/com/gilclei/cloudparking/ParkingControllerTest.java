package com.gilclei.cloudparking;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.rsocket.context.LocalRSocketServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.gilclei.cloudparking.controller.dto.ParkingCreateDTO;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerTest {

	@LocalServerPort
	private int randomPort;

	@BeforeEach
	public void setUpTest() {
		System.out.println(randomPort);
		RestAssured.port = randomPort;
	}

	@Test
	void whenfindAllThenCheckResult() {
		RestAssured.given()
		.when()
		.get("/parking")
		.then()
		.statusCode(HttpStatus.OK.value())
//		.body("license[0]",Matchers.equalTo("MMS-2222"))
		.extract().response().body().prettyPrint();

	}

	@Test
	void whenCreateThenCheckIsCreated() {

		var createDTO = new ParkingCreateDTO();
		createDTO.setColor("AMARELO");
		createDTO.setLicense("WRT-5555");
		createDTO.setModel("BRASILIA");
		createDTO.setState("SP");

		RestAssured.given()
			.when()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(createDTO)
			.post("/parking")
			.then()
			.statusCode(HttpStatus.CREATED.value())			
			.body("license",Matchers.equalTo("WRT-5555"))
			.body("color",Matchers.equalTo("AMARELO"))
			.body("model",Matchers.equalTo("BRASILIA"))
			.body("state",Matchers.equalTo("SP"))
			.extract().response().body().prettyPrint();

	}
//
//	@Test
//	void create() {
//
//	}
}