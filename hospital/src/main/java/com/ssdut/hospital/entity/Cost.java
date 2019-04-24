package com.ssdut.hospital.entity;

import javax.persistence.*;
import java.math.BigDecimal;



@Entity
@Table(name = "cost")
public class Cost {
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Id
    @Column(name="happenNo")
    private Integer happenNo;
    @Column(name="inpatientNo")
    private Integer inpatientNo;
    @Column(name="costName",length = 20)
    private String costName;
    @Column(name="costAmount")
    private Double costAmount;

    public String getCostName() {
        return costName;
    }

    public void setCostName(String costName) {
        this.costName = costName;
    }

    public Double getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(Double costAmount) {
        this.costAmount = costAmount;
    }


    public Integer getInpatientNo() {
        return inpatientNo;
    }

    public void setInpatientNo(Integer inpatientNo) {
        this.inpatientNo = inpatientNo;
    }

    //public Integer getHappenNum() {
      //  return happenNo;
    //}

    public void setHappenNum(Integer happenNo) {
        this.happenNo = happenNo;
    }


}
