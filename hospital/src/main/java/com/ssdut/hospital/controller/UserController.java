package com.ssdut.hospital.controller;


import com.ssdut.hospital.dao.StaffDAO;
import com.ssdut.hospital.entity.Info;
import com.ssdut.hospital.entity.Staff;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.Null;

@RestController
public class UserController {
    @Autowired
    StaffDAO staffDAO;
    @RequestMapping(value = "/login",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public JSONObject login(@RequestBody JSONObject user)throws Exception {
        System.out.println("user:" + user);
        Staff staff = staffDAO.findByStaffSpell(user.getAsString("name"));
        if (staff == null) {
            JSONObject json=new JSONObject();
            json.appendField("status",false);
            json.appendField("message","账号不存在");
            return  json;
        } else {
            String pass1=user.getAsString("pass");
            System.out.println("pass1:"+pass1);
            String pass2=staff.getStaffPassword();
            System.out.println("pass2:"+pass2);
            if(pass1.equals(pass2)){
                Integer staffNo = staff.getStaffNo();
                System.out.println("staffNo:" + staffNo);
                String staffNo1=staffNo.toString();
                JSONObject json =new JSONObject();
                json.appendField("status",true);
                json.appendField("token",staff.getStaffNo());
                json.appendField("name",staff.getStaffName());
                return json;
            }
            else{
                JSONObject json=new JSONObject();
                json.appendField("status",false);
                json.appendField("message","密码错误");
                return  json;
            }
        }
    }
    @RequestMapping(value = "/getUserRole",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public JSONObject getUserRole(@RequestBody JSONObject staffNo)throws Exception{
        System.out.println("staffNo:"+staffNo.getAsString("staffNo"));
        Integer staffNo1= Integer.valueOf(staffNo.getAsString("staffNo"));
        Staff staff= staffDAO.findOne(staffNo1);
        JSONObject json=new JSONObject();
        json.appendField("staffRole",staff.getStaffRole());
        return json;
    }
    @RequestMapping(value = "/userRegister",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public JSONObject userRegister(@RequestBody JSONObject register)throws Exception{
        System.out.println("register:"+register.toJSONString());
        String staffName=register.getAsString("staffName");
        String staffPassword=register.getAsString("staffPassword");
        String staffSpell=register.getAsString("staffSpell");
        Staff staff= staffDAO.findByStaffSpell(staffSpell);
        if(staff==null){
            String staffRole="2";
            Staff staff1=new Staff();
            staff1.setStaffName(staffName);
            staff1.setStaffPassword(staffPassword);
            staff1.setStaffSpell(staffSpell);
            staff1.setStaffRole(staffRole);
            staffDAO.save(staff1);
            JSONObject json=new JSONObject();
            json.appendField("success",true);
            return json;
        }
        else{
            JSONObject json=new JSONObject();
            json.appendField("success",false);
            return json;
        }
    }
}
