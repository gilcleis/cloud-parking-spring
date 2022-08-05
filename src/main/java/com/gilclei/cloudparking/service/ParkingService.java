package com.gilclei.cloudparking.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gilclei.cloudparking.model.Parking;
import com.gilclei.cloudparking.service.exception.ParkinkNotFoundException;

@Service
public class ParkingService {

	private static Map<String, Parking> parkingMap = new HashMap();

	static {
		var id = getUUID();
		var id1 = getUUID();
		Parking parking = new Parking(id, "MMS-2222", "SP", "CELTA", "PRETO");
		Parking parking1 = new Parking(id1, "BBB-33#3", "BA", "GOL", "PRETO");
		parkingMap.put(id, parking);
		parkingMap.put(id1, parking1);
	}

	public List<Parking> findAll() {
		return parkingMap.values().stream().collect(Collectors.toList());
	}

	private static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public Parking findById(String id) {
		var parking = parkingMap.get(id);
		if (parking == null) {
			throw new ParkinkNotFoundException(id);
		}
		return parking;

	}

	public Parking create(Parking parkingCreate) {
		String uuid = getUUID();
		parkingCreate.setId(uuid);
		parkingCreate.setEntryDate(LocalDateTime.now());
		parkingMap.put(uuid, parkingCreate);
		return parkingCreate;
	}

	public void delete(String id) {
		Parking parking = findById(id);
		parkingMap.remove(id);

	}

	public Parking update(String id, Parking parkingCreate) {
		Parking parking = findById(id);
		parking.setColor(parkingCreate.getColor());
		parking.setState(parkingCreate.getState());
		parking.setModel(parkingCreate.getModel());
		parking.setLicense(parkingCreate.getLicense());
		parkingMap.replace(id, parking);
		return parking;
	}

	public Parking checkOut(String id) {
		Parking parking = findById(id);
		parking.setExitDate(LocalDateTime.now());
		parkingMap.replace(id, parking);
		return parking;
	}

}
