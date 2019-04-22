package com.ssdut.hospital.controller;


import com.ssdut.hospital.dao.CostDAO;
import com.ssdut.hospital.entity.Cost;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class rebackController {
    @Autowired
    CostDAO costDAO;
    @PostMapping("/reback")
    public void reback(@RequestBody JSONObject data){
        System.out.println("rebackData:"+data.toJSONString());
        Cost cost=new Cost();
        cost.setCostName(data.getAsString("costName"));
        cost.setInpatientNo(Integer.valueOf(data.getAsString("inpatientNo")));
        cost.setCostAmount(Double.valueOf(data.getAsString("remain")));
        costDAO.save(cost);
    }
}
