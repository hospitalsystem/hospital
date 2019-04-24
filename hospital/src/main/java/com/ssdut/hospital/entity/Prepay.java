package com.ssdut.hospital.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name = "prepay")
public class Prepay {

    @Column(name="happenNo")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer happenNo;
    @Column(name="patientName",length = 10, nullable = false)
    private String patientName;
    @Column(name="prepayCost")
    private Double prepayCost;
    @Column(name="recipeNo")
    private Integer recipeNo;
    @Column(name="prepayState",length = 1)
    private String prepayState;
    @Column(length = 1)
    private String issettlement;
    @Column(name="staffNo")
    private Integer staffNo;
    @Column(name="operDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operDate;
    @Column(name="prepayMethod",length = 1)
    private String prepayMethod;
    @Column(name="inpatientNo")
    private Integer inpatientNo;

    public int getInpatientNo() {
        return inpatientNo;
    }

    public void setInpatientNo(Integer inpatientNo) {
        this.inpatientNo = inpatientNo;
    }

//    public int getHappenNo() {
//        return happenNo;
//    }

    public void setHappenNo(Integer happenNo) {
        this.happenNo = happenNo;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Double getPrepayCost() {
        return prepayCost;
    }

    public void setPrepayCost(Double prepayCost) {
        this.prepayCost = prepayCost;
    }

    public Integer getRecipeNo() {
        return recipeNo;
    }

    public void setRecipeNo(Integer recipeNo) {
        this.recipeNo = recipeNo;
    }

    public String getPrepayState() {
        return prepayState;
    }

    public void setPrepayState(String prepayState) {
        this.prepayState = prepayState;
    }

    public String getIssettlement() {
        return issettlement;
    }

    public void setIssettlement(String issettlement) {
        this.issettlement = issettlement;
    }

    public Integer getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(Integer staffNo) {
        this.staffNo = staffNo;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getOperDate() {
        return  operDate;
    }

    public void setOperDate(Date operDate) {
        this.operDate = operDate;
    }

    public String getPrepayMethod() {
        return prepayMethod;
    }

    public void setPrepayMethod(String prepayMethod) {
        this.prepayMethod = prepayMethod;
    }
}
