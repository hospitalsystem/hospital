package com.ssdut.hospital.controller;

import com.ssdut.hospital.dao.*;
import com.ssdut.hospital.entity.*;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

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
            //prepayJSONItem.appendField("prepayState", prepayItem.getPrepayState());
            if(Integer.valueOf(prepayItem.getPrepayState())==1){
                prepayJSONItem.appendField("prepayState","收取");
            }
            else if(Integer.valueOf(prepayItem.getPrepayState())==2){
                prepayJSONItem.appendField("prepayState","返还");
            }
            else if(Integer.valueOf(prepayItem.getPrepayState())==3){
                prepayJSONItem.appendField("prepayState","补打");
            }
            //prepayJSONItem.appendField("issettlement",prepayItem.getIssettlement());
            if(Integer.valueOf(prepayItem.getIssettlement())==0){
                prepayJSONItem.appendField("issettlement","未结算");
            }
            else if(Integer.valueOf(prepayItem.getIssettlement())==1){
                prepayJSONItem.appendField("issettlement","已结算");
            }
            //prepayJSONItem.appendField("prepayMethod", prepayItem.getPrepayMethod());
            if(Integer.valueOf(prepayItem.getPrepayMethod()) == 1){
                prepayJSONItem.appendField("prepayMethod","现金支付");
            }else if(Integer.valueOf(prepayItem.getPrepayMethod()) == 2){
                prepayJSONItem.appendField("prepayMethod","银行卡支付");
            }else if(Integer.valueOf(prepayItem.getPrepayMethod()) == 3){
                prepayJSONItem.appendField("prepayMethod","支票支付");
            }
            prepayJSONItem.appendField("staffNo",prepayItem.getStaffNo());
            prepayJSONItem.appendField("operDate", prepayItem.getOperDate());

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

    @PostMapping("/leavePatientRegister")
    public void leavePatientRegister(@RequestBody JSONObject data){
        System.out.println("接受的数据为:"+data.toJSONString());
        Inpatient inpatient = inpatientDAO.findOne(Integer.valueOf(data.getAsString("inpatientNoTemp")));
        Bed bed = new Bed();
        Cost cost = new Cost();
        Patient patient = patientDAO.findByIdCard(data.getAsString("patientIdcardTemp"));
        Prepay prepay = new Prepay();

        Double remain = Double.valueOf(data.getAsString("remainTemp"));

        Boolean flag = true;


        //如果存在remain小于0，我们就该取相反数，然后把它加入到prepay的记录里面去
        if(remain < 0){
            flag =false;//添加预交金就不用添加cost了
            remain = 0 - remain;
            prepay.setInpatientNo(Integer.valueOf(data.getAsString("inpatientNoTemp")));
            prepay.setPatientName(patient.getPatientName());
            prepay.setPrepayCost(remain);

            Date now = new Date();

            System.out.println(now.toString());
            prepay.setPrepayState("3"); //直接置状态为补打
            prepay.setIssettlement("1"); //直接置结算状态为结算
            prepay.setOperDate(now);
            prepay.setRecipeNo(Integer.valueOf(data.getAsString("recipeNoTemp")));
            prepay.setStaffNo(Integer.valueOf(data.getAsString("staffNoTemp")));
            prepay.setPrepayMethod(data.getAsString("prepayMethodTemp"));
            prepayDAO.save(prepay);
        }
        if(remain > 0 && flag){//因为上面刚把remain取相反数
            cost.setCostName(data.getAsString("costNameTemp"));
            cost.setCostAmount(Double.valueOf(data.getAsString("costAmountTemp")));
            cost.setInpatientNo(Integer.valueOf(data.getAsString("inpatientNoTemp")));
            costDAO.save(cost);
        }
        bed.setBedNo(Integer.valueOf(data.getAsString("bedNoTemp")));
        bed.setBedState(data.getAsString("bedStateTemp"));
        patient.setPatientState(data.getAsString("patientStateTemp"));


        Date now = new Date();
        inpatient.setOutState(data.getAsString("outStateTemp"));
        inpatient.setOutDate(now);
        patientDAO.save(patient);
        inpatientDAO.save(inpatient);
        bedDAO.save(bed);
    }

    @RequestMapping(value = "/updateNewCost", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject updateNewCost(@RequestBody JSONObject inPatientNo) throws Exception {
        JSONObject Data=new JSONObject();
        JSONArray costJSON = new JSONArray();

        System.out.println("updateNewCost"+inPatientNo.getAsString("inPatientNo"));
        Integer inPatientNo1 = Integer.valueOf(inPatientNo.getAsString("inPatientNo"));
        List<Cost> cost = costDAO.findByInpatientNo(inPatientNo1);

        for(int i = 0; i< cost.size(); i++){
            Cost costItem=cost.get(i); //接收集合中的每一个cost实体对象
            JSONObject costJSONItem=new JSONObject(); //用来将每一条数据存放到costJSON中

            costJSONItem.appendField("inpatientNo",costItem.getInpatientNo());
            costJSONItem.appendField("happenNo", costItem.getHappenNum());
            costJSONItem.appendField("costName",costItem.getCostName());
            costJSONItem.appendField("costAmount",costItem.getCostAmount());

            costJSON.add(costJSONItem);
        }
        Data.appendField("costJSON",costJSON);
        return Data;
    }

    @RequestMapping(value = "/updateNewPrepay", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject updateNewPrepay(@RequestBody JSONObject inPatientNo)throws Exception{
        JSONObject Data=new JSONObject();
        JSONArray prepayJSON = new JSONArray();
        System.out.println("updateNewPrepay"+inPatientNo.getAsString("inPatientNo"));

        Integer inPatientNo1 = Integer.valueOf(inPatientNo.getAsString("inPatientNo"));
        List<Prepay> prepay = prepayDAO.findByInpatientNo(inPatientNo1);

        for(int i = 0; i < prepay.size(); i++){
            Prepay prepayItem=prepay.get(i); //接收集合中的每一个perpay实体对象
            JSONObject prepayJSONItem=new JSONObject(); //用来将每一条数据存放到prepayJSON中

            prepayJSONItem.appendField("inpatientNo", prepayItem.getInpatientNo());
            prepayJSONItem.appendField("happenNo", prepayItem.getHappenNum());
            prepayJSONItem.appendField("patientName", prepayItem.getPatientName());
            prepayJSONItem.appendField("prepayCost", prepayItem.getPrepayCost());
            prepayJSONItem.appendField("recipeNo", prepayItem.getRecipeNo());
            if(Integer.valueOf(prepayItem.getPrepayState())==1){
                prepayJSONItem.appendField("prepayState","收取");
            }
            else if(Integer.valueOf(prepayItem.getPrepayState())==2){
                prepayJSONItem.appendField("prepayState","返还");
            }
            else if(Integer.valueOf(prepayItem.getPrepayState())==3){
                prepayJSONItem.appendField("prepayState","补打");
            }
            if(Integer.valueOf(prepayItem.getIssettlement())==0){
                prepayJSONItem.appendField("issettlement","未结算");
            }
            else if(Integer.valueOf(prepayItem.getIssettlement())==1){
                prepayJSONItem.appendField("issettlement","已结算");
            }
            if(Integer.valueOf(prepayItem.getPrepayMethod()) == 1){
                prepayJSONItem.appendField("prepayMethod","现金支付");
            }else if(Integer.valueOf(prepayItem.getPrepayMethod()) == 2){
                prepayJSONItem.appendField("prepayMethod","银行卡支付");
            }else if(Integer.valueOf(prepayItem.getPrepayMethod()) == 3){
                prepayJSONItem.appendField("prepayMethod","支票支付");
            }
            prepayJSONItem.appendField("staffNo",prepayItem.getStaffNo());
            prepayJSONItem.appendField("operDate", prepayItem.getOperDate());

            prepayJSON.add(prepayJSONItem);
        }

        Data.appendField("prepayJSON",prepayJSON);

        return Data;
    }

    @RequestMapping(value = "/recallInpatientInfoSearch", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject recallInpatientInfoSearch(@RequestBody JSONObject inpatientNoSearch)throws Exception{

        System.out.println("收到的数据为"+inpatientNoSearch.getAsString("inpatientNoSearch"));

        JSONObject Data = new JSONObject();
        JSONObject patientJSON = new JSONObject();
        Integer inPatientNo1 = Integer.valueOf(inpatientNoSearch.getAsString("inpatientNoSearch"));

        Inpatient inpatient = inpatientDAO.findOne(inPatientNo1);
        Patient patient = patientDAO.findByIdCard(inpatient.getIdCard());

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

        Data.appendField("patientJSON",patientJSON);

        return Data;
    }


    @RequestMapping(value = "/recallPatientRegister", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject recallPatientRegister(@RequestBody JSONObject data)throws Exception{
        System.out.println("接受的数据为:"+data.toJSONString());

        Inpatient inpatient = new Inpatient();
        Patient patient = patientDAO.findByIdCard(data.getAsString("idCard"));
        Bed bed = bedDAO.findOne(Integer.valueOf(data.getAsString("bedNo")));
        Date now = new Date();

        patient.setPatientState("0");

        bed.setBedState("1");

        inpatient.setPatientName(data.getAsString("patientName"));
        inpatient.setIdCard(data.getAsString("idCard"));
        inpatient.setInDate(now);
        inpatient.setInSource(data.getAsString("inSource"));
        inpatient.setInPath(data.getAsString("inPath"));
        inpatient.setStatus(data.getAsString("status"));
        inpatient.setDeptNo(Integer.valueOf(data.getAsString("deptNo")));
        inpatient.setBedNo(Integer.valueOf(data.getAsString("bedNo")));
        inpatient.setHouseDocNo(Integer.valueOf(data.getAsString("houseDocNo")));
        inpatient.setChargeDocNo(Integer.valueOf(data.getAsString("chargeDocNo")));
        inpatient.setChiefDocNo(Integer.valueOf(data.getAsString("chiefDocNo")));
        inpatient.setDutyNurseNo(Integer.valueOf(data.getAsString("dutyNurseNo")));

        inpatientDAO.save(inpatient);
        patientDAO.save(patient);
        bedDAO.save(bed);

        //接下来，我们进行的步骤是返回刚刚生成的inpatient对象，让前端进行显示，因为前端没有得到inDate和inpatientNo
        JSONObject Data = new JSONObject();
        JSONObject inpatientJSON = new JSONObject();

        List<Inpatient> inpatientList = inpatientDAO.findAllByIdCard(data.getAsString("idCard"));
        Inpatient inpatientTemp = inpatientList.get(inpatientList.size() - 1); //获取所有满足条件的inpatient中的最后一个，即为我们刚刚创建的记录
        System.out.println("刚刚的住院记录的编号是:"+inpatientTemp.getInpatientNo());
        //将inpatientTemp的所有元素封装到inpatientJSON中
        inpatientJSON.appendField("inpatientNo",inpatientTemp.getInpatientNo());
        inpatientJSON.appendField("patientName",inpatientTemp.getPatientName());
        inpatientJSON.appendField("idCard",inpatientTemp.getIdCard());
        inpatientJSON.appendField("inDate",inpatientTemp.getInDate());
        inpatientJSON.appendField("inSource",inpatientTemp.getInSource());
        inpatientJSON.appendField("inPath",inpatientTemp.getInPath());
        inpatientJSON.appendField("status",inpatientTemp.getStatus());
        inpatientJSON.appendField("deptNo",inpatientTemp.getDeptNo());
        inpatientJSON.appendField("bedNo",inpatientTemp.getBedNo());
        inpatientJSON.appendField("houseDocNo",inpatientTemp.getHouseDocNo());
        inpatientJSON.appendField("chargeDocNo",inpatientTemp.getChargeDocNo());
        inpatientJSON.appendField("chiefDocNo",inpatientTemp.getChiefDocNo());
        inpatientJSON.appendField("dutyNurseNo",inpatientTemp.getDutyNurseNo());


        System.out.println("住院时间是："+inpatientJSON.getAsString("inDate"));

        //把inpatientJSON封装到Data中
        Data.appendField("inpatientJSON",inpatientJSON);
        return Data;


    }
}
