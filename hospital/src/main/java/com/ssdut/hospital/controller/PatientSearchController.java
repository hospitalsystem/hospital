package com.ssdut.hospital.controller;
import com.ssdut.hospital.dao.*;
import com.ssdut.hospital.entity.*;
//import com.ssdut.hospital.entity.Prepay;
//import com.ssdut.hospital.entity.Staff;
//import net.minidev.json.JSONArray;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class PatientSearchController {
    @Autowired
    InpatientDAO inpatientDAO;
    @Autowired
    OutpatientDAO outpatientDAO;
    @Autowired
    PatientDAO patientDAO;
    @Autowired
    PrepayDAO prepayDAO;
    @RequestMapping(value = "/getPatient", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject getPatient(@RequestBody JSONObject cardNo) throws Exception {
        Integer Card_no = Integer.valueOf(cardNo.getAsString("cardNo"));
        Patient patient = patientDAO.findByCardNo(Card_no);
        String id_card = patient.getIdCard();
        Inpatient inpatient = inpatientDAO.findByIdCard(id_card);
        Outpatient outpatient = outpatientDAO.findByIdCard(id_card);
        Integer inpatient_no =  inpatient.getInpatientNo();
        List<Prepay> prepay = prepayDAO.findByInpatientNo(inpatient_no);
        System.out.println(prepay);
        JSONObject json = new JSONObject();
        json.appendField("patientName", patient.getPatientName());
        json.appendField("idCard", patient.getIdCard());
        if(Integer.valueOf(patient.getSexCode())==1) {
            json.appendField("sexCode",'男');
        }else if(Integer.valueOf(patient.getSexCode())==2) {
            json.appendField("sexCode",'女');
        }
        //json.appendField("cardNo", patient.getCardNo());
        json.appendField("nation", patient.getNation());
        json.appendField("birthday", patient.getBirthday());
        json.appendField("age", patient.getAge());
        json.appendField("country", patient.getCountry());
        json.appendField("dist", patient.getDist());
        json.appendField("birthArea", patient.getBirthArea());
        json.appendField("mariCode", patient.getMariCode());
        json.appendField("workName", patient.getWorkName());
        json.appendField("occupation", patient.getOccupation());
        json.appendField("workTel", patient.getWorkTel());
        json.appendField("home", patient.getHome());
        json.appendField("homeTel", patient.getHomeTel());
        json.appendField("linkmanName", patient.getLinkmanName());
        json.appendField("linkmanRelation", patient.getLinkmanRelation());
        json.appendField("linkmanAdd", patient.getLinkmanAdd());
        json.appendField("linkmanTel", patient.getLinkmanTel());
        json.appendField("packName", patient.getPactName());

        json.appendField("inSource", inpatient.getInSource());

        json.appendField("inPath", inpatient.getInPath());

        json.appendField("houseDocNo", inpatient.getHouseDocNo());
        json.appendField("diagnose", outpatient.getDiagnose());
        Double prepayAll=0.0;
        for(int i =0;i<prepay.size();i++){
            Prepay prepay1=prepay.get(i);
            prepayAll+=prepay1.getPrepayCost();
        }
        json.appendField("prepayCost", prepayAll);
       // json.appendField("prepayMethod", prepay.getPrepayMethod());
        JSONObject Data=new JSONObject();
        Data.appendField("json",json);
       // Data.appendField("data",data);
        System.out.println("Data:"+Data.toJSONString());
        System.out.println("11111");
        return Data;
    }

    @RequestMapping(value = "/getPatientList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject getPatientList(@RequestBody JSONObject search) throws Exception{
        //System.out.println(search.getAsString("data"));
        System.out.println("searchOption"+search.getAsString("searchOption"));
        Integer searchOption = Integer.valueOf(search.getAsString("searchOption"));
        //System.out.println(searchOption);
        List<Inpatient> InpatientList = new ArrayList();
        List<Patient> PatientList = new ArrayList();
        JSONObject Data = new JSONObject();
        JSONArray inpatientJSON = new JSONArray();
        JSONArray patientJSON = new JSONArray();
        //住院号，病历号，患者姓名，民族，单位，籍贯，科室，病床号，收住医生
        //根据住院表属性进行查询
        if(searchOption==1||searchOption==2||searchOption==3||searchOption==4||searchOption==5){
            if(searchOption==1){
                Integer cardNo = Integer.valueOf(search.getAsString("searchCondition"));
                PatientList = patientDAO.findAllByCardNo(cardNo);
            }else if(searchOption==2){
                String patientName = search.getAsString("searchCondition");
                PatientList = patientDAO.findAllByPatientName(patientName);
            }else if(searchOption==3){
                String nation = search.getAsString("searchCondition");
                PatientList = patientDAO.findAllByNation(nation);
            }else if(searchOption==4){
                String workName = search.getAsString("searchCondition");
                PatientList = patientDAO.findAllByWorkName(workName);
            }else if(searchOption==5){
             String dist = search.getAsString("searchCondition");
                PatientList = patientDAO.findAllByDist(dist);
            }
            for(Patient item : PatientList){
//                Inpatient inpatientItem = inpatientDAO.findByIdCard(item.getIdCard());
////               InpatientList.add(inpatientItem);
                List<Inpatient> inpatients = inpatientDAO.findAllByIdCard(item.getIdCard());
                for(Inpatient item1 : inpatients){
                    InpatientList.add(item1);
                }
            }
            for(Inpatient item : InpatientList){
                Patient patientItem = patientDAO.findByIdCard(item.getIdCard());
                PatientList.add(patientItem);
            }

        }
        if(searchOption==6||searchOption==7||searchOption==8||searchOption==9){
            if(searchOption == 6){
                //通过住院号查询
                Integer inpatientNo = Integer.valueOf(search.getAsString("searchCondition"));
                InpatientList = inpatientDAO.findAllByInpatientNo(inpatientNo);
            }else if(searchOption == 7){
                //科室号 床位号 收住医师
                Integer deptNo = Integer.valueOf(search.getAsString("searchCondition"));
                InpatientList = inpatientDAO.findAllByDeptNo(deptNo);
            }else if(searchOption == 8){
                Integer bedNo=Integer.valueOf(search.getAsString("searchCondition"));
                InpatientList = inpatientDAO.findAllByBedNo(bedNo);
            }else if(searchOption == 9){
                Integer houseDocNo=Integer.valueOf(search.getAsString("searchCondition"));
                InpatientList = inpatientDAO.findAllByHouseDocNo(houseDocNo);
            }
        for(Inpatient temp: InpatientList){
            Patient patientItem = patientDAO.findByIdCard(temp.getIdCard());
            PatientList.add(patientItem);
        }
        }
        if(searchOption==10){
            InpatientList = inpatientDAO.findAll();
            for(Inpatient temp : InpatientList){
                Patient patientItem = patientDAO.findByIdCard(temp.getIdCard());
                PatientList.add(patientItem);
            }
        }


        for(int i = 0; i< InpatientList.size(); i++){
            Inpatient inpatientItem = InpatientList.get(i); //接收集合中的每一个inpatient实体对象
            JSONObject inpatientJSONItem=new JSONObject(); //用来将每一条数据存放到inpatientJSON中

            inpatientJSONItem.appendField("inpatientNo",inpatientItem.getInpatientNo());
            inpatientJSONItem.appendField("deptNo",inpatientItem.getDeptNo());
            inpatientJSONItem.appendField("bedNo",inpatientItem.getBedNo());
            inpatientJSONItem.appendField("houseDocNo",inpatientItem.getHouseDocNo());
            inpatientJSON.add(inpatientJSONItem);
        }

        for(int i = 0; i< PatientList.size(); i++){
            Patient patientItem = PatientList.get(i); //接收集合中的每一个patient实体对象
            JSONObject patientJSONItem=new JSONObject(); //用来将每一条数据存放到patientJSON中
            patientJSONItem.appendField("cardNo",patientItem.getCardNo());
            patientJSONItem.appendField("patientName",patientItem.getPatientName());
            patientJSONItem.appendField("nation",patientItem.getNation());
            patientJSONItem.appendField("workName",patientItem.getWorkName());
            patientJSONItem.appendField("dist",patientItem.getDist());

            patientJSON.add(patientJSONItem);
        }

        System.out.println("满足条件的住院信息有条："+InpatientList.size());
        System.out.println("满足条件的病人信息有条："+PatientList.size());


        Data.appendField("inpatientJSON",inpatientJSON);
        Data.appendField("patientJSON",patientJSON);

        return Data;
    }

}
