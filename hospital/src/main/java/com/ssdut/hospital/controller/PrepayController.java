package com.ssdut.hospital.controller;

import com.ssdut.hospital.dao.*;
import com.ssdut.hospital.entity.*;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    @Autowired
    StaffDAO staffDAO;

    @RequestMapping(value = "/getPrepay", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject getPrepay(@RequestBody JSONObject inPatientNumber) throws Exception {
        //System.out.println("inPatientNumber:" + inPatientNumber.getAsString("inPatientNumber"));
        Integer temp = Integer.valueOf(inPatientNumber.getAsString("inPatientNumber"));
        //System.out.println("temp:" + temp);
        List<Prepay> prepay = prepayDAO.findByInpatientNo(temp);
        //System.out.println("prepayLength:" + prepay.size());
        //System.out.println("InpatientNo:" + prepay.get(0).getInpatientNo());
        Integer Inpatient_no = new Integer(prepay.get(0).getInpatientNo());
        //System.out.println("Inpatient_no:" + Inpatient_no);
        Inpatient inpatient = inpatientDAO.findOne(Inpatient_no);
        //System.out.println("inpatientIdCard:" + inpatient.getIdCard());
        String idCard = inpatient.getIdCard();
        //System.out.println("idCard:" + idCard);
        Integer staffNo=inpatient.getHouseDocNo();
        Staff staff=staffDAO.findOne(staffNo);
        //Integer IdCard=Integer.valueOf(idCard);
        Patient patient = patientDAO.findByIdCard(idCard);
        //System.out.println(patient.getLinkmanTel());
        JSONObject json = new JSONObject();
        json.appendField("patientName", patient.getPatientName());
        json.appendField("workName", patient.getWorkName());
        json.appendField("deptNo", inpatient.getDeptNo());
        json.appendField("inPath", inpatient.getInPath());
        json.appendField("inDate", inpatient.getInDate());
        json.appendField("houseDocNo", staff.getStaffName());
        json.appendField("bedNo", inpatient.getBedNo());
        json.appendField("birthday", patient.getBirthday());
        json.appendField("prepayMethod", prepay.get(0).getPrepayMethod());
        //json.appendField("prepayCost", prepay.get(0).getPrepayCost());
        //json.appendField("", patient);
        JSONArray data = new JSONArray();
        Double prepayAll=0.0;
        for(int i=0;i<prepay.size();i++){
            Prepay prepay1=prepay.get(i);
            JSONObject json1=new JSONObject();

            prepayAll+=prepay1.getPrepayCost();
            System.out.println("prepay1.getPrepayState():"+prepay1.getPrepayState());
            if(Integer.valueOf(prepay1.getPrepayState())==1){
                System.out.println("2222");
                json1.appendField("prepayState","收取");
            }
            else if(Integer.valueOf(prepay1.getPrepayState())==2){
                System.out.println("3333");
                json1.appendField("prepayState","返还");
            }
            else if(Integer.valueOf(prepay1.getPrepayState())==3){
                System.out.println("4444");
                json1.appendField("prepayState","补打");
            }
            System.out.println("7777");
            if(Integer.valueOf(prepay1.getPrepayState())==0){
                System.out.println("5555");
                json1.appendField("issettlement","未结算");
            }
            else if(Integer.valueOf(prepay1.getPrepayState())==1){
                System.out.println("6666");
                json1.appendField("issettlement","已结算");
            }
            json1.appendField("recipeNo",prepay1.getRecipeNo());
            json1.appendField("prepayCost",prepay1.getPrepayCost());
            //json1.appendField("issettlement",prepay1.getIssettlement());
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
        System.out.println("11111");
        return Data;
    }
    @RequestMapping(value = "/prepayCollect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Info prepayCollect(@RequestBody JSONObject data) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.parse(data.getAsString("operDate")));
        System.out.println("data:"+data.toJSONString());
        String patientName=data.getAsString("patientName");
        String issettlement=data.getAsString("issettlement");
        String prepayMethod=data.getAsString("prepayMethod");
        String prepayState=data.getAsString("prepayState");
        Integer inpatientNo=Integer.valueOf(data.getAsString("inpatientNo"));
        Double prepayCost=Double.valueOf(data.getAsString("prepayCost"));
        Integer recipeNo=Integer.valueOf(data.getAsString("recipeNo"));
        Integer staffNo=Integer.valueOf(data.getAsString("staffNo"));
        Date date=sdf.parse(data.getAsString("operDate"));
        Prepay prepay=new Prepay();
        prepay.setPatientName(patientName);
        prepay.setInpatientNo(inpatientNo);
        prepay.setIssettlement(issettlement);
        prepay.setPrepayState(prepayState);
        prepay.setOperDate(date);
        prepay.setPrepayCost(prepayCost);
        prepay.setPrepayMethod(prepayMethod);
        prepay.setRecipeNo(recipeNo);
        prepay.setStaffNo(staffNo);
        prepayDAO.save(prepay);
        return new Info("success","成功");
    }
}
