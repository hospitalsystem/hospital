package com.ssdut.hospital.dao;

import com.ssdut.hospital.entity.Inpatient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InpatientDAO extends JpaRepository<Inpatient, Integer> {
}
