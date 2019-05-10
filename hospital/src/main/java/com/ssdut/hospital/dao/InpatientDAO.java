package com.ssdut.hospital.dao;

import com.ssdut.hospital.entity.Inpatient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InpatientDAO extends JpaRepository<Inpatient, Integer> {
    List<Inpatient> findAllByIdCard(String idCard);
    List<Inpatient> findAllByHouseDocName(String houseDocName);
    List<Inpatient> findAllByInpatientNo(Integer inpatientNo);
    List<Inpatient> findAllByDeptName(String deptName);
    List<Inpatient> findAllByBedNo(Integer bedNo);
    Inpatient findByIdCard(String idCard);

}
