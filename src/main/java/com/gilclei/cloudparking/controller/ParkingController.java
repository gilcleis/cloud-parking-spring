package com.gilclei.cloudparking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gilclei.cloudparking.controller.dto.ParkingCreateDTO;
import com.gilclei.cloudparking.controller.dto.ParkingDTO;
import com.gilclei.cloudparking.controller.mapper.ParkingMapper;
import com.gilclei.cloudparking.model.Parking;
import com.gilclei.cloudparking.service.ParkingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/parking")
@Api(tags = "Parking Controller")
public class ParkingController {

	private final ParkingService parkingService;
	private final ParkingMapper parkingMapper;

	public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
		this.parkingService = parkingService;
		this.parkingMapper = parkingMapper;
	}

	@GetMapping
	@ApiOperation("Find All Parkings")
	public ResponseEntity<List<ParkingDTO>> findAll() {
		List<Parking> parkingList = parkingService.findAll();
		List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/{id}")
	@ApiOperation("Find By Id")
	public ResponseEntity<ParkingDTO> findById(@PathVariable String id) {
		Parking parking = parkingService.findById(id);
		ParkingDTO result = parkingMapper.toParkingDTO(parking);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/{id}")
	@ApiOperation("Delete By Id")
	public ResponseEntity delete(@PathVariable String id) {
		parkingService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	@ApiOperation("Update")
	public ResponseEntity<ParkingDTO> update(@PathVariable String id, @RequestBody ParkingCreateDTO dto) {
		var parkingCreate = parkingMapper.toParkingCreate(dto);
		var parking = parkingService.update(id, parkingCreate);
		var result = parkingMapper.toParkingDTO(parking);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	@PostMapping
	@ApiOperation("Create")
	public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto) {
		var parkingCreate = parkingMapper.toParkingCreate(dto);
		var parking = parkingService.create(parkingCreate);
		var result = parkingMapper.toParkingDTO(parking);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}



	@PostMapping("/{id}")
	@ApiOperation("CheckOut")
	public ResponseEntity<ParkingDTO> checkOut(@PathVariable String id) {
		Parking parking = parkingService.checkOut(id);
		return ResponseEntity.ok(parkingMapper.toParkingDTO(parking));
	}

}
