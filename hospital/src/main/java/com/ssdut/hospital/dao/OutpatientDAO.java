package com.ssdut.hospital.dao;

import com.ssdut.hospital.entity.Inpatient;
import com.ssdut.hospital.entity.Outpatient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutpatientDAO extends JpaRepository<Outpatient, Integer> {
    Outpatient findByIdCard(String idCard);
}