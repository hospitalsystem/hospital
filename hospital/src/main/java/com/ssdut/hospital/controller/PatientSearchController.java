package com.ssdut.hospital.controller;
import com.ssdut.hospital.dao.*;
import com.ssdut.hospital.entity.*;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
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
    public JSONObject getPatient(@RequestBody JSONObject inPatientNo) throws Exception {

        JSONObject Data=new JSONObject();
        JSONObject inpatientJSON = new JSONObject();
        JSONObject patientJSON = new JSONObject();

        Integer inPatientNo1 = Integer.valueOf(inPatientNo.getAsString("inPatientNo"));

        Inpatient inpatient = inpatientDAO.findOne(inPatientNo1);
        Patient patient = patientDAO.findByIdCard(inpatient.getIdCard());

        //将inpatient的值加入到JSON中
        inpatientJSON.appendField("inpatientNo",inpatient.getInpatientNo());
        inpatientJSON.appendField("patientName",inpatient.getPatientName());
        inpatientJSON.appendField("idCard",inpatient.getIdCard());
        inpatientJSON.appendField("inDate",inpatient.getInDate());
        inpatientJSON.appendField("inSource",inpatient.getInSource());
        inpatientJSON.appendField("inPath",inpatient.getInPath());
        inpatientJSON.appendField("status",inpatient.getStatus());
        inpatientJSON.appendField("deptName",inpatient.getDeptName());
        inpatientJSON.appendField("bedNo",inpatient.getBedNo());
        inpatientJSON.appendField("houseDocName",inpatient.getHouseDocName());
        inpatientJSON.appendField("chargeDocName",inpatient.getChargeDocName());
        inpatientJSON.appendField("chiefDocName",inpatient.getChiefDocName());
        inpatientJSON.appendField("dutyNurseName",inpatient.getDutyNurseName());
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

        //将inpatientJSON，patientJSON，bedJSON，prepayJSON，costJSON,prepayAll,costAll添加到Date中
        Data.appendField("inpatientJSON",inpatientJSON);
        Data.appendField("patientJSON",patientJSON);

        System.out.println("inpatientJSON:"+Data.getAsString("inpatientJSON"));
        System.out.println("patientJSON:"+Data.getAsString("patientJSON"));

        //给前端返回Data
        return Data;

    }

    @RequestMapping(value = "/getPatientList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject getPatientList(@RequestBody JSONObject search) throws Exception{
        System.out.println("searchOption"+search.getAsString("searchOption"));
        Integer searchOption = Integer.valueOf(search.getAsString("searchOption"));
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
                //科室名称
                String deptName = search.getAsString("searchCondition");
                InpatientList = inpatientDAO.findAllByDeptName(deptName);
            }else if(searchOption == 8){
                //床位号
                Integer bedNo=Integer.valueOf(search.getAsString("searchCondition"));
                InpatientList = inpatientDAO.findAllByBedNo(bedNo);
            }else if(searchOption == 9){
                //收住医师
                String houseDocName=search.getAsString("searchCondition");
                InpatientList = inpatientDAO.findAllByHouseDocName(houseDocName);
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
            inpatientJSONItem.appendField("deptName",inpatientItem.getDeptName());
            inpatientJSONItem.appendField("bedNo",inpatientItem.getBedNo());
            inpatientJSONItem.appendField("houseDocName",inpatientItem.getHouseDocName());
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
            if(patientItem.getPatientState().equals("0")){
                patientJSONItem.appendField("patientState","住院");
            }else if(patientItem.getPatientState().equals("1")){
                patientJSONItem.appendField("patientState","出院");
            }else if(patientItem.getPatientState().equals("2")){
                patientJSONItem.appendField("patientState","转院");
            }


            patientJSON.add(patientJSONItem);
        }

        System.out.println("满足条件的住院信息有条："+InpatientList.size());
        System.out.println("满足条件的病人信息有条："+PatientList.size());


        Data.appendField("inpatientJSON",inpatientJSON);
        Data.appendField("patientJSON",patientJSON);

        return Data;
    }

    @PostMapping("/editPatientInfo")
    public void editPatientInfo(@RequestBody JSONObject data){
        System.out.println("接受的数据为:"+data.toJSONString());
        //根据接收的inpatientNo来查找inpatient进行更新，根据接收的cardNo来查找patient进行更新
        Inpatient inpatient = inpatientDAO.findOne(Integer.valueOf(data.getAsString("inpatientNo")));
        Patient patient = patientDAO.findOne(Integer.valueOf(data.getAsString("cardNo")));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);


        inpatient.setPatientName(data.getAsString("patientName"));
        inpatient.setIdCard(data.getAsString("idCard"));
        inpatient.setInSource(data.getAsString("inSource"));
        inpatient.setInPath(data.getAsString("inPath"));
        inpatient.setStatus(data.getAsString("status"));
        inpatient.setDeptName(data.getAsString("deptName"));
        inpatient.setBedNo(Integer.valueOf(data.getAsString("bedNo")));
        inpatient.setHouseDocName(data.getAsString("houseDocName"));
        inpatient.setChargeDocName(data.getAsString("chargeDocName"));
        inpatient.setChiefDocName(data.getAsString("chiefDocName"));
        inpatient.setDutyNurseName(data.getAsString("dutyNurseName"));
        inpatient.setOutState(data.getAsString("outState"));

        patient.setPatientName(data.getAsString("patientName"));
        patient.setSexCode(data.getAsString("sexCode"));
        patient.setIdCard(data.getAsString("idCard"));
        patient.setNation(data.getAsString("nation"));


        //用日期选择器返回的时间格式例子如下：2019-04-10T16:00:00.000Z，所以要替换掉多余的内容，改成2019-04-10 16:00:00
        Date birthdayTemp = formatter.parse(data.getAsString("birthday").replace(".000Z", "").replace("T", " "), pos);
        System.out.println("出生日期为"+birthdayTemp.toString());
        patient.setBirthday(birthdayTemp);

        patient.setAge(Integer.valueOf(data.getAsString("age")));
        patient.setCountry(data.getAsString("country"));
        patient.setDist(data.getAsString("dist"));
        patient.setBirthArea(data.getAsString("birthArea"));
        patient.setMariCode(data.getAsString("mariCode"));
        patient.setWorkName(data.getAsString("workName"));

        patient.setOccupation(data.getAsString("occupation"));
        patient.setWorkTel(data.getAsString("workTel"));
        patient.setHome(data.getAsString("home"));
        patient.setHomeTel(data.getAsString("homeTel"));
        patient.setLinkmanName(data.getAsString("linkmanName"));
        patient.setLinkmanRelation(data.getAsString("linkmanRelation"));
        patient.setLinkmanAdd(data.getAsString("linkmanAdd"));
        patient.setLinkmanTel(data.getAsString("linkmanTel"));
        patient.setPactName(data.getAsString("pactName"));

        //将修改后的inpatient和patient重新保存
        inpatientDAO.save(inpatient);
        patientDAO.save(patient);

    }
}
