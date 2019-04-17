package com.ssdut.hospital.controller;

import com.ssdut.hospital.dao.InpatientDAO;
import com.ssdut.hospital.entity.Inpatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class InpatientController {

    @Autowired
    InpatientDAO inpatientDAO;

    //根据inpatient_no来返回住院信息
    @GetMapping("/inpatient/{inpatient_no}")
    public Inpatient getInpatient(@PathVariable("inpatient_no") Integer inpatient_no){
        Inpatient inpatient = inpatientDAO.findOne(inpatient_no);
        return inpatient;
    }
}
