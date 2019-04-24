package com.ssdut.hospital.controller;

import com.ssdut.hospital.dao.*;
import com.ssdut.hospital.entity.*;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LeavePatientController {
    @Autowired
    InpatientDAO inpatientDAO;
    @Autowired
    BedDAO bedDAO;
    @Autowired
    PatientDAO patientDAO;
    @Autowired
    PrepayDAO prepayDAO;
    @Autowired
    CostDAO costDAO;

    @RequestMapping(value = "/leaveInpatientInfoSearch", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject leaveInpatientInfoSearch(@RequestBody JSONObject inPatientNo) throws Exception {

        JSONObject Data=new JSONObject();
        JSONObject inpatientJSON = new JSONObject();
        JSONObject patientJSON = new JSONObject();
        JSONObject bedJSON = new JSONObject();
        JSONArray prepayJSON = new JSONArray();
        JSONArray costJSON = new JSONArray();


        Integer inPatientNo1 = Integer.valueOf(inPatientNo.getAsString("inPatientNo"));
        Double prepayAll = 0.0; //预交金总额
        Double costAll = 0.0; //花销总额

        Inpatient inpatient = inpatientDAO.findOne(inPatientNo1);
        Bed bed = bedDAO.findOne(inpatient.getBedNo());
        Patient patient = patientDAO.findByIdCard(inpatient.getIdCard());
        List<Prepay> prepay = prepayDAO.findByInpatientNo(inPatientNo1);
        List<Cost> cost = costDAO.findByInpatientNo(inPatientNo1);

        //System.out.println(inpatient.getInpatientNo());
        System.out.println(bed.getBedNo());
        System.out.println(patient.getPatientName());
        System.out.println(prepay.get(0).getPrepayCost());
        System.out.println(cost.get(0).getCostAmount());


        //将inpatient的值加入到JSON中
        inpatientJSON.appendField("inpatientNo",inpatient.getInpatientNo());
        inpatientJSON.appendField("patientName",inpatient.getPatientName());
        inpatientJSON.appendField("idCard",inpatient.getIdCard());
        inpatientJSON.appendField("inDate",inpatient.getInDate());
        inpatientJSON.appendField("inSource",inpatient.getInSource());
        inpatientJSON.appendField("inPath",inpatient.getInPath());
        inpatientJSON.appendField("status",inpatient.getStatus());
        inpatientJSON.appendField("deptNo",inpatient.getDeptNo());
        inpatientJSON.appendField("bedNo",inpatient.getBedNo());
        inpatientJSON.appendField("houseDocNo",inpatient.getHouseDocNo());
        inpatientJSON.appendField("chargeDocNo",inpatient.getChargeDocNo());
        inpatientJSON.appendField("chiefDocNo",inpatient.getChiefDocNo());
        inpatientJSON.appendField("dutyNurseNo",inpatient.getDutyNurseNo());
        inpatientJSON.appendField("outDate",inpatient.getOutDate());
        inpatientJSON.appendField("outState",inpatient.getOutState());

        //将patient的值加入到JSON中
        patientJSON.appendField("cardNo",patient.getCardNo());
        patientJSON.appendField("patientName",patient.getPatientName());
        patientJSON.appendField("sexCode",patient.getSexCode());
        patientJSON.appendField("idCard",patient.getIdCard());
        patientJSON.appendField("patientState",patient.getPatientState());
        patientJSON.appendField("nation",patient.getNation());
        patientJSON.appendField("birthday",patient.getBirthday());
        patientJSON.appendField("age",patient.getAge());
        patientJSON.appendField("country",patient.getCountry());
        patientJSON.appendField("dist",patient.getDist());
        patientJSON.appendField("birthArea",patient.getBirthArea());
        patientJSON.appendField("mariCode",patient.getMariCode());
        patientJSON.appendField("workName",patient.getWorkName());
        patientJSON.appendField("occupation",patient.getOccupation());
        patientJSON.appendField("workTel",patient.getWorkTel());
        patientJSON.appendField("home",patient.getHome());
        patientJSON.appendField("homeTel",patient.getHomeTel());
        patientJSON.appendField("linkmanName",patient.getLinkmanName());
        patientJSON.appendField("linkmanRelation",patient.getLinkmanRelation());
        patientJSON.appendField("linkmanAdd",patient.getLinkmanAdd());
        patientJSON.appendField("linkmanTel",patient.getLinkmanTel());
        patientJSON.appendField("pactName",patient.getPactName());

        //将bed的值添加到JSON中
        bedJSON.appendField("bedNo",bed.getBedNo());
        bedJSON.appendField("bedState",bed.getBedState());


        //将预交金记录添加到JSON中
        for(int i = 0; i < prepay.size(); i++){
            Prepay prepayItem=prepay.get(i); //接收集合中的每一个perpay实体对象
            JSONObject prepayJSONItem=new JSONObject(); //用来将每一条数据存放到prepayJSON中

            prepayAll = prepayAll + prepayItem.getPrepayCost();

            prepayJSONItem.appendField("inpatientNo", prepayItem.getInpatientNo());
            prepayJSONItem.appendField("happenNo", prepayItem.getHappenNum());
            prepayJSONItem.appendField("patientName", prepayItem.getPatientName());
            prepayJSONItem.appendField("prepayCost", prepayItem.getPrepayCost());
            prepayJSONItem.appendField("recipeNo", prepayItem.getRecipeNo());
            prepayJSONItem.appendField("prepayState", prepayItem.getPrepayState());
            prepayJSONItem.appendField("issettlement",prepayItem.getPrepayState());
            prepayJSONItem.appendField("staffNo",prepayItem.getStaffNo());
            prepayJSONItem.appendField("operDate", prepayItem.getOperDate());
            prepayJSONItem.appendField("prepayMethod", prepayItem.getPrepayMethod());

            prepayJSON.add(prepayJSONItem);
        }

        //将消费条目添加到JSON中
        for(int i = 0; i< cost.size(); i++){
            Cost costItem=cost.get(i); //接收集合中的每一个cost实体对象
            JSONObject costJSONItem=new JSONObject(); //用来将每一条数据存放到costJSON中

            costAll = costAll + costItem.getCostAmount();

            costJSONItem.appendField("inpatientNo",costItem.getInpatientNo());
            costJSONItem.appendField("happenNo", costItem.getHappenNum());
            costJSONItem.appendField("costName",costItem.getCostName());
            costJSONItem.appendField("costAmount",costItem.getCostAmount());

            costJSON.add(costJSONItem);
        }

        //将inpatientJSON，patientJSON，bedJSON，prepayJSON，costJSON,prepayAll,costAll添加到Date中
        Data.appendField("inpatientJSON",inpatientJSON);
        Data.appendField("patientJSON",patientJSON);
        Data.appendField("bedJSON",bedJSON);
        Data.appendField("prepayJSON",prepayJSON);
        Data.appendField("costJSON",costJSON);
        Data.appendField("prepayAll",prepayAll);
        Data.appendField("costAll",costAll);

        //给前端返回Data
        return Data;
    }
}
