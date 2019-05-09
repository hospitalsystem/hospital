package com.ssdut.hospital.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "inpatient")
public class Inpatient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="inpatientNo")
    private Integer inpatientNo;
    @Column(name="patientName",length = 10, nullable = false)
    private String patientName;
    @Column(name="idCard",length = 18, nullable = false)
    private String idCard;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="inDate",nullable = false)
    private Date inDate;
    @Column(name="inSource",length = 1, nullable = false)
    private String inSource;
    @Column(name="inPath",length = 1)
    private String inPath;
    @Column(name="status",length = 50)
    private String status;
    @Column(name="deptNo",nullable = false)
    private Integer deptNo;
    @Column(name="bedNo",nullable = false)
    private Integer bedNo;
    @Column(name="houseDocNo",nullable = false)
    private Integer houseDocNo;
    @Column(name="chargeDocNo")
    private Integer chargeDocNo;
    @Column(name="chiefDocNo")
    private Integer chiefDocNo;
    @Column(name="dutyNurseNo")
    private Integer dutyNurseNo;
    @Column(name="outDate")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date outDate;
    @Column(name="outState",length = 1)
    private String outState;
    public Integer getInpatientNo() {
        return inpatientNo;
    }

    public void setInpatientNo(Integer inpatientNo) {
        this.inpatientNo = inpatientNo;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public String getInSource() {
        return inSource;
    }

    public void setInSource(String inSource) {
        this.inSource = inSource;
    }

    public String getInPath() {
        return inPath;
    }

    public void setInPath(String inPath) {
        this.inPath = inPath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
    }

    public Integer getBedNo() {
        return bedNo;
    }

    public void setBedNo(Integer bedNo) {
        this.bedNo = bedNo;
    }

    public Integer getHouseDocNo() {
        return houseDocNo;
    }

    public void setHouseDocNo(Integer houseDocNo) {
        this.houseDocNo = houseDocNo;
    }

    public Integer getChargeDocNo() {
        return chargeDocNo;
    }

    public void setChargeDocNo(Integer chargeDocNo) {
        this.chargeDocNo = chargeDocNo;
    }

    public Integer getChiefDocNo() {
        return chiefDocNo;
    }

    public void setChiefDocNo(Integer chiefDocNo) {
        this.chiefDocNo = chiefDocNo;
    }

    public Integer getDutyNurseNo() {
        return dutyNurseNo;
    }

    public void setDutyNurseNo(Integer dutyNurseNo) {
        this.dutyNurseNo = dutyNurseNo;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public String getOutState() {
        return outState;
    }

    public void setOutState(String outState) {
        this.outState = outState;
    }
}
