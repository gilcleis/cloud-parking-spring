package com.gilclei.cloudparking.controller;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gilclei.cloudparking.model.Parking;

@RestController
@RequestMapping("/parking")
public class ParkingController {
	
	@GetMapping
	public List<Parking> findAll(){
		var parking = new Parking();
		parking.setColor("PRETO");
		parking.setLicense("MSS-1111");
		parking.setModel("vw GOL");
		parking.setState("SP");
		
		return Arrays.asList(parking);
	}

}
