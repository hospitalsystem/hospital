package com.ssdut.hospital.dao;

import com.ssdut.hospital.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PatientDAO extends JpaRepository<Patient, Integer> {
    Patient findByIdCard(String idCard);
    Patient findByCardNo(Integer cardNo);
    List <Patient> findAllByCardNo(Integer cardNo) ;
    List <Patient> findAllByPatientName(String patientName) ;
    List <Patient> findAllByNation(String nation) ;
    List <Patient> findAllByWorkName(String workName) ;
    List <Patient> findAllByDist(String dist) ;
}
