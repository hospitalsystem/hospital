package com.ssdut.hospital.controller;
import com.ssdut.hospital.dao.BedDAO;
import com.ssdut.hospital.dao.PatientDAO;
import com.ssdut.hospital.entity.Bed;
import com.ssdut.hospital.entity.Info;
import com.ssdut.hospital.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class PatientController {
    @Autowired PatientDAO patientDAO;
    @Autowired BedDAO bedDAO;
    @ResponseBody
    @PostMapping("/addPatient")
    public Info addPatient(@RequestBody Patient p) throws Exception {
        System.out.println("1111");
        System.out.println("p:"+p.getHome());
        patientDAO.save(p);
        return new Info("success","1");
    }
    @RequestMapping("/currentUserName ")
    public void test() throws Exception{
        System.out.println("shoudaole");
    }
    @GetMapping("/getBed/{number}")
    public Bed getBed(@PathVariable("number")Integer num){
        System.out.println("111111");
        System.out.println("num:"+num);
        Bed bed= bedDAO.findOne(num);
        return  bed;
    }
}

