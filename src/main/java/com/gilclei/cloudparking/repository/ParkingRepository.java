package com.gilclei.cloudparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gilclei.cloudparking.model.Parking;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, String>{

}
