package com.ssdut.hospital.dao;

import com.ssdut.hospital.entity.Inpatient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InpatientDAO extends JpaRepository<Inpatient, Integer> {
    List<Inpatient> findAllByIdCard(String idCard);
    List<Inpatient> findAllByHouseDocNo(Integer houseDocNo);
    List<Inpatient> findAllByInpatientNo(Integer inpatientNo);
    List<Inpatient> findAllByDeptNo(Integer deptNo);
    List<Inpatient> findAllByBedNo(Integer bedNo);
    Inpatient findByIdCard(String idCard);

}
