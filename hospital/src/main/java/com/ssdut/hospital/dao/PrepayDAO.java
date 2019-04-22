package com.ssdut.hospital.dao;


import com.ssdut.hospital.entity.Bed;
import com.ssdut.hospital.entity.Prepay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrepayDAO extends JpaRepository<Prepay, Integer> {
    List<Prepay> findByInpatientNo(Integer inpatientNumber);
}