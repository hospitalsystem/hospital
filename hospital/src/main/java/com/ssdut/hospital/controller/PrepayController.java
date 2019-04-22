package com.ssdut.hospital.controller;

import com.ssdut.hospital.dao.CostDAO;
import com.ssdut.hospital.dao.InpatientDAO;
import com.ssdut.hospital.dao.PatientDAO;
import com.ssdut.hospital.dao.PrepayDAO;
import com.ssdut.hospital.entity.Inpatient;
import com.ssdut.hospital.entity.Patient;
import com.ssdut.hospital.entity.Prepay;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@RestController
public class PrepayController {
    @Autowired
    PrepayDAO prepayDAO;
    @Autowired
    InpatientDAO inpatientDAO;
    @Autowired
    PatientDAO patientDAO;
    @Autowired
    CostDAO costDAO;

    @RequestMapping(value = "/getPrepay", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject getPrepay(@RequestBody JSONObject inPatientNumber) throws Exception {
        System.out.println("inPatientNumber:" + inPatientNumber.getAsString("inPatientNumber"));
        Integer temp = Integer.valueOf(inPatientNumber.getAsString("inPatientNumber"));
        System.out.println("temp:" + temp);
        List<Prepay> prepay = prepayDAO.findByInpatientNo(temp);
        System.out.println("prepayLength:" + prepay.size());
        System.out.println("InpatientNo:" + prepay.get(0).getInpatientNo());
        Integer Inpatient_no = new Integer(prepay.get(0).getInpatientNo());
        System.out.println("Inpatient_no:" + Inpatient_no);
        Inpatient inpatient = inpatientDAO.findOne(Inpatient_no);
        System.out.println("inpatientIdCard:" + inpatient.getIdCard());
        String idCard = inpatient.getIdCard();
        System.out.println("idCard:" + idCard);
        //Integer IdCard=Integer.valueOf(idCard);
        Patient patient = patientDAO.findByIdCard(idCard);
        System.out.println(patient.getLinkmanTel());
        JSONObject json = new JSONObject();
        json.appendField("patientName", patient.getPatientName());
        json.appendField("workName", patient.getWorkName());
        json.appendField("deptNo", inpatient.getDeptNo());
        json.appendField("inPath", inpatient.getInPath());
        json.appendField("inDate", inpatient.getInDate());
        json.appendField("houseDocNo", inpatient.getHouseDocNo());
        json.appendField("bedNo", inpatient.getBedNo());
        json.appendField("birthday", patient.getBirthday());
        json.appendField("prepayMethod", prepay.get(0).getPrepayMethod());
        //json.appendField("prepayCost", prepay.get(0).getPrepayCost());
        //json.appendField("", patient);
        JSONArray data = new JSONArray();
        JSONObject json1=new JSONObject();
        Double prepayAll=0.0;
        for(int i=0;i<prepay.size();i++){
            Prepay prepay1=prepay.get(i);
            System.out.println("InpatientNo:" + prepay1.getInpatientNo());
            Integer Inpatient_no1 = new Integer(prepay1.getInpatientNo());
            System.out.println("Inpatient_no1:" + Inpatient_no);
            Inpatient inpatient1 = inpatientDAO.findOne(Inpatient_no);
            System.out.println("inpatientIdCard1:" + inpatient.getIdCard());
            String idCard1 = inpatient.getIdCard();
            System.out.println("idCard1:" + idCard1);
            //Integer IdCard=Integer.valueOf(idCard);
            Patient patient1 = patientDAO.findByIdCard(idCard1);
            System.out.println(patient1.getLinkmanTel());
            prepayAll+=prepay1.getPrepayCost();
            json1.appendField("recipeNo",prepay1.getRecipeNo());
            json1.appendField("prepayState",prepay1.getPrepayState());
            json1.appendField("prepayCost",prepay1.getPrepayCost());
            json1.appendField("issettlement",prepay1.getIssettlement());
            json1.appendField("staffNo",prepay1.getStaffNo());
            json1.appendField("operDate", prepay1.getOperDate());
            data.add(json1);
        }
        Double costAll=costDAO.getCostAll(Inpatient_no);
        Double remain=prepayAll-costAll;
       json.appendField("prepayAll",prepayAll);
       json.appendField("remain",remain);
       JSONObject Data=new JSONObject();
       Data.appendField("json",json);
       Data.appendField("data",data);
       System.out.println("Data:"+Data.toJSONString());
       return Data;
    }
}
