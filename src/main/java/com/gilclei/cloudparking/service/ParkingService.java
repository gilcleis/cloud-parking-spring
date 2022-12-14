package com.gilclei.cloudparking.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gilclei.cloudparking.model.Parking;
import com.gilclei.cloudparking.repository.ParkingRepository;
import com.gilclei.cloudparking.service.exception.ParkinkNotFoundException;

@Service
public class ParkingService {

//	private static Map<String, Parking> parkingMap = new HashMap();

//	static {
//		var id = getUUID();
//		var id1 = getUUID();
//		Parking parking = new Parking(id, "MMS-2222", "SP", "CELTA", "PRETO");
//		Parking parking1 = new Parking(id1, "BBB-33#3", "BA", "GOL", "PRETO");
//		parkingMap.put(id, parking);
//		parkingMap.put(id1, parking1);
//	}
	
	@Autowired
	private ParkingRepository parkingRepository;
	

	@Transactional
	public List<Parking> findAll() {
		return parkingRepository.findAll();
	}

	@Transactional
	private static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	@Transactional
	public Parking findById(String id) {
		return parkingRepository.findById(id).orElseThrow(() -> new ParkinkNotFoundException(id));
	}

	@Transactional
	public Parking create(Parking parkingCreate) {
		String uuid = getUUID();
		parkingCreate.setId(uuid);
		parkingCreate.setEntryDate(LocalDateTime.now());
		parkingRepository.save(parkingCreate);
		return parkingCreate;
	}

	@Transactional
	public void delete(String id) {
		findById(id);
		parkingRepository.deleteById(id);

	}

	@Transactional
	public Parking update(String id, Parking parkingCreate) {
		Parking parking = findById(id);
		parking.setColor(parkingCreate.getColor());
		parking.setState(parkingCreate.getState());
		parking.setModel(parkingCreate.getModel());
		parking.setLicense(parkingCreate.getLicense());
		parkingRepository.save(parking);
		return parking;
	}
	
	@Transactional
	public Parking checkOut(String id) {
		Parking parking = findById(id);
		parking.setExitDate(LocalDateTime.now());
		parking.setBill(ParkingCheckOut.getBill(parking));
		parkingRepository.save(parking);
		return parking;
	}

}
