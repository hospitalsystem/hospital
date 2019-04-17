package com.ssdut.hospital.dao;

import com.ssdut.hospital.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientDAO extends JpaRepository<Patient, Integer> {
}
