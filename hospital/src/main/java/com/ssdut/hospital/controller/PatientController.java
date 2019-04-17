package com.ssdut.hospital.controller;


import com.ssdut.hospital.dao.PatientDAO;
import com.ssdut.hospital.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PatientController {

    @Autowired
    PatientDAO patientDAO;


    @GetMapping("/patient/{card_no}")
    public Patient getPatient(@PathVariable("card_no") Integer card_no){
        Patient patient = patientDAO.findOne(card_no);
        return patient;
    }
    @PostMapping("/addPatient")
    public void addPatient(@RequestBody Patient p)throws Exception{
        System.out.println("home:"+p.getHome());
        patientDAO.save(p);
    }
}
