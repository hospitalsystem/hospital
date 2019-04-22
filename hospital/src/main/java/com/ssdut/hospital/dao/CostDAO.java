package com.ssdut.hospital.dao;

import com.ssdut.hospital.entity.Cost;
import com.ssdut.hospital.entity.Prepay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.GeneratedValue;
import java.util.List;

public interface CostDAO extends JpaRepository<Cost, Integer> {
    @Query("select sum(c.costAmount) from Cost c where c.inpatientNo=?1")
    Double getCostAll(Integer inpatientNo);
}