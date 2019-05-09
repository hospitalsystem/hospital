package com.ssdut.hospital.controller;

import com.ssdut.hospital.dao.*;
import com.ssdut.hospital.entity.*;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class PatientController {
    @Autowired PatientDAO patientDAO;
    @Autowired BedDAO bedDAO;
    @Autowired InpatientDAO inpatientDAO;
    @Autowired PrepayDAO prepayDAO;
    @Autowired OutpatientDAO outpatientDAO;
    @ResponseBody
    @RequestMapping(value = "/patientAdd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Info addPatient(@RequestBody JSONObject data) throws Exception {
        System.out.println("1111");
        System.out.println("data:"+data.toJSONString());
       // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String patientName=data.getAsString("patientName");
        String idCard=data.getAsString("idCard");
        String sexCode=data.getAsString("sexCode");
        String nation=data.getAsString("nation");
        Date birthday=sdf.parse(data.getAsString("birthday"));
        Integer age=Integer.valueOf(data.getAsString("age"));
        String country=data.getAsString("country");
        String dist = data.getAsString("dist");
        String birthArea=data.getAsString("birthArea");
        String mariCode=data.getAsString("mariCode");
        String workName=data.getAsString("workName");
        String occupation=data.getAsString("occupation");
        String workTel=data.getAsString("workTel");
        String home=data.getAsString("home");
        String homeTel=data.getAsString("homeTel");
        String linkmanName=data.getAsString("linkmanName");
        String linkmanRelation=data.getAsString("linkmanRelation");
        String linkmanAdd=data.getAsString("linkmanAdd");
        String linkmanTel=data.getAsString("linkmanTel");
        String pactName=data.getAsString("pactName");
        String patientState=data.getAsString("patientState");
        String inSource=data.getAsString("inSource");
        String inPath=data.getAsString("inPath");
        Integer houseDocNo=Integer.valueOf(data.getAsString("houseDocNo"));
        String diagnose=data.getAsString("diagnose");
      //  Double prepayCost=Double.valueOf(data.getAsString("prepayCost"));
       // String prepayMethod=data.getAsString("prepayMethod");

        Date inDate=new Date();//获取当前时间
        System.out.println(inDate);
        Integer deptNo=Integer.valueOf(data.getAsString("deptNo"));
        Integer bedNo=Integer.valueOf(data.getAsString("bedNo"));
        Patient patient=new Patient();
       // Prepay prepay=new Prepay();
        Inpatient inpatient=new Inpatient();
        Outpatient outpatient=new Outpatient();
        patient.setPatientName(patientName);
        patient.setIdCard(idCard);
        patient.setSexCode(sexCode);
        patient.setNation(nation);
        patient.setBirthday(birthday);
        patient.setAge(age);
        patient.setCountry(country);
        patient.setBirthArea(birthArea);
        patient.setMariCode(mariCode);
        patient.setWorkName(workName);
        patient.setOccupation(occupation);
        patient.setWorkTel(workTel);
        patient.setHome(home);
        patient.setHomeTel(homeTel);
        patient.setLinkmanName(linkmanName);
        patient.setLinkmanAdd(linkmanAdd);
        patient.setLinkmanRelation(linkmanRelation);
        patient.setLinkmanTel(linkmanTel);
        patient.setPactName(pactName);
        patient.setPatientState(patientState);
        patient.setDist(dist);
        inpatient.setPatientName(patientName);
        inpatient.setInSource(inSource);
        inpatient.setInPath(inPath);
        inpatient.setHouseDocNo(houseDocNo);
        inpatient.setBedNo(bedNo);
        inpatient.setDeptNo(deptNo);
        inpatient.setInDate(inDate);
        inpatient.setIdCard(idCard);
        inpatient.setInSource(inSource);
        outpatient.setDiagnose(diagnose);
        outpatient.setIdCard(idCard);
        outpatient.setPatientName(patientName);
//        prepay.setPatientName(patientName);
//        prepay.setPrepayCost(prepayCost);
//        prepay.setPrepayMethod(prepayMethod);
        //List<Inpatient> inpatient_list = inpatientDAO.findAllByIdCard(idCard);
        //Integer inpatient_no = inpatient_list.get(inpatient_list.size()).getInpatientNo();
       // prepay.setInpatientNo(inpatient_no);
         patientDAO.save(patient);
         inpatientDAO.save(inpatient);
         //prepayDAO.save(prepay);
         outpatientDAO.save(outpatient);
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

