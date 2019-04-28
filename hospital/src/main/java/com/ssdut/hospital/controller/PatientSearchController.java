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

    @PostMapping("/editPatientInfo")
    public void editPatientInfo(@RequestBody JSONObject data){
        System.out.println("接受的数据为:"+data.toJSONString());
        //根据接收的inpatientNo来查找inpatient进行更新，根据接收的cardNo来查找patient进行更新
        Inpatient inpatient = inpatientDAO.findOne(Integer.valueOf(data.getAsString("inpatientNo")));
        Patient patient = patientDAO.findOne(Integer.valueOf(data.getAsString("cardNo")));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);

        /*
        接下来的工作：
        1. 将前端发回的部分数据转换成数字
        2. 将String转化为Date
         */
        inpatient.setPatientName(data.getAsString("patientName"));
        inpatient.setIdCard(data.getAsString("idCard"));

        inpatient.setInSource(data.getAsString("inSource"));
//        if(data.getAsString("inSource") == "门诊"){
//            inpatient.setInSource("1");
//        }else if(data.getAsString("inSource") == "急诊"){
//            inpatient.setInSource("2");
//        }else if(data.getAsString("inSource") == "转科"){
//            inpatient.setInSource("3");
//        }else if(data.getAsString("inSource") == "转院"){
//            inpatient.setInSource("4");
//        }
        inpatient.setInPath(data.getAsString("inPath"));
//        if(data.getAsString("inPath") == "本市"){
//            inpatient.setInPath("1");
//        }else if(data.getAsString("inPath") == "外市"){
//            inpatient.setInPath("2");
//        }
        inpatient.setStatus(data.getAsString("status"));
        inpatient.setDeptNo(Integer.valueOf(data.getAsString("deptNo")));
        inpatient.setBedNo(Integer.valueOf(data.getAsString("bedNo")));
        inpatient.setHouseDocNo(Integer.valueOf(data.getAsString("houseDocNo")));
        inpatient.setChargeDocNo(Integer.valueOf(data.getAsString("chargeDocNo")));
        inpatient.setChiefDocNo(Integer.valueOf(data.getAsString("chiefDocNo")));
        inpatient.setDutyNurseNo(Integer.valueOf(data.getAsString("dutyNurseNo")));

        inpatient.setOutState(data.getAsString("outState"));
//        if(data.getAsString("outState") == "治愈"){
//            inpatient.setOutState("1");
//        }else if(data.getAsString("outState") == "好转"){
//            inpatient.setOutState("2");
//        }else if(data.getAsString("outState") == "死亡"){
//            inpatient.setOutState("3");
//        }else if(data.getAsString("outState") == "治残"){
//            inpatient.setOutState("4");
//        }else if(data.getAsString("outState") == "未治愈"){
//            inpatient.setOutState("5");
//        }else if(data.getAsString("outState") == "正常分娩"){
//            inpatient.setOutState("6");
//        }else if(data.getAsString("outState") == "其他"){
//            inpatient.setOutState("7");
//        }

        patient.setPatientName(data.getAsString("patientName"));
        patient.setSexCode(data.getAsString("sexCode"));
//        if(data.getAsString("sexCode") == "男"){
//            patient.setSexCode("1");
//        }else if(data.getAsString("sexCode") == "女"){
//            patient.setSexCode("2");
//        }
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
//        if(data.getAsString("mariCode") == "未婚"){
//            patient.setMariCode("1");
//        }else if(data.getAsString("mariCode") == "已婚"){
//            patient.setMariCode("2");
//        }else if(data.getAsString("mariCode") == "丧偶"){
//            patient.setMariCode("3");
//        }else if(data.getAsString("mariCode") == "离婚"){
//            patient.setMariCode("4");
//        }
        patient.setWorkName(data.getAsString("workName"));

        patient.setOccupation(data.getAsString("occupation"));
//        if(data.getAsString("occupation") == "国家公务人员"){
//            patient.setOccupation("11");
//        }else if(data.getAsString("occupation") == "专业技术人员"){
//            patient.setOccupation("13");
//        }else if(data.getAsString("occupation") == "职员"){
//            patient.setOccupation("17");
//        }else if(data.getAsString("occupation") == "企业管理人员"){
//            patient.setOccupation("21");
//        }else if(data.getAsString("occupation") == "工人"){
//            patient.setOccupation("24");
//        }else if(data.getAsString("occupation") == "农民"){
//            patient.setOccupation("27");
//        }else if(data.getAsString("occupation") == "学生"){
//            patient.setOccupation("31");
//        }else if(data.getAsString("occupation") == "现役军人"){
//            patient.setOccupation("37");
//        }else if(data.getAsString("occupation") == "自由职业者"){
//            patient.setOccupation("51");
//        }else if(data.getAsString("occupation") == "个人经营者"){
//            patient.setOccupation("54");
//        }else if(data.getAsString("occupation") == "无业人员"){
//            patient.setOccupation("70");
//        }else if(data.getAsString("occupation") == "退（离）休人员"){
//            patient.setOccupation("80");
//        }else if(data.getAsString("occupation") == "其他"){
//            patient.setOccupation("90");
//        }
        patient.setWorkTel(data.getAsString("workTel"));
        patient.setHome(data.getAsString("home"));
        patient.setHomeTel(data.getAsString("homeTel"));
        patient.setLinkmanName(data.getAsString("linkmanName"));
        patient.setLinkmanRelation(data.getAsString("linkmanRelation"));
//        if(data.getAsString("linkmanRelation") == "配偶"){
//            patient.setLinkmanRelation("1");
//        }else if(data.getAsString("linkmanRelation") == "子"){
//            patient.setLinkmanRelation("2");
//        }else if(data.getAsString("linkmanRelation") == "女"){
//            patient.setLinkmanRelation("3");
//        }else if(data.getAsString("linkmanRelation") == "孙子、孙女或外孙子、外孙女"){
//            patient.setLinkmanRelation("4");
//        }else if(data.getAsString("linkmanRelation") == "父母"){
//            patient.setLinkmanRelation("5");
//        }else if(data.getAsString("linkmanRelation") == "祖父母或外祖父母"){
//            patient.setLinkmanRelation("6");
//        }else if(data.getAsString("linkmanRelation") == "兄弟姐妹"){
//            patient.setLinkmanRelation("7");
//        }else if(data.getAsString("linkmanRelation") == "其他"){
//            patient.setLinkmanRelation("8");
//        }
        patient.setLinkmanAdd(data.getAsString("linkmanAdd"));
        patient.setLinkmanTel(data.getAsString("linkmanTel"));
        patient.setPactName(data.getAsString("pactName"));


        //将修改后的inpatient和patient重新保存
        inpatientDAO.save(inpatient);
        patientDAO.save(patient);




    }
}
