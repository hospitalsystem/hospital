package com.ssdut.hospital.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cardNo")
    private Integer cardNo;
    @Column(name="patientName",length = 10, nullable = false)
    private String patientName;
    @Column(name="sexCode",length = 1, nullable = false)
    private String sexCode;
    @Column(name="idCard",length = 18, nullable = false)
    private String idCard;
    @Column(name="patientState",length =2,nullable = false)
    private String patientState;
    @Column(length = 10)
    private String nation;
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date birthday;
    @Column
    private Integer age;
    @Column(length = 15)
    private String country;
    @Column(length = 50)
    private String dist;
    @Column(name="birthArea",length = 50)
    private String birthArea;
    @Column(name="mariCode",length = 1)
    private String mariCode;
    @Column(name="workName",length = 50)
    private String workName;
    @Column(length = 2)
    private String occupation;
    @Column(name="workTel",length = 20)
    private String workTel;
    @Column(length = 50)
    private String home;
    @Column(name="homeTel",length = 20)
    private String homeTel;
    @Column(name="linkmanName",length = 10)
    private String linkmanName;
    @Column(name="linkmanRelation",length = 5)
    private String linkmanRelation;
    @Column(name="linkmanAdd",length = 50)
    private String linkmanAdd;
    @Column(name="linkmanTel",length = 20)
    private String linkmanTel;
    @Column(name="pactName",length = 50)
    private String pactName;

    public Integer getCardNo() {
        return cardNo;
    }

    public void setCardNo(Integer cardNo) {
        this.cardNo = cardNo;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getSexCode() {
        return sexCode;
    }

    public void setSexCode(String sexCode) {
        this.sexCode = sexCode;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public String getBirthArea() {
        return birthArea;
    }

    public void setBirthArea(String birthArea) {
        this.birthArea = birthArea;
    }

    public String getMariCode() {
        return mariCode;
    }

    public void setMariCode(String mariCode) {
        this.mariCode = mariCode;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getWorkTel() {
        return workTel;
    }

    public void setWorkTel(String workTel) {
        this.workTel = workTel;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getHomeTel() {
        return homeTel;
    }

    public void setHomeTel(String homeTel) {
        this.homeTel = homeTel;
    }

    public String getLinkmanName() {
        return linkmanName;
    }

    public void setLinkmanName(String linkmanName) {
        this.linkmanName = linkmanName;
    }

    public String getLinkmanRelation() {
        return linkmanRelation;
    }

    public void setLinkmanRelation(String linkmanRelation) {
        this.linkmanRelation = linkmanRelation;
    }

    public String getLinkmanAdd() {
        return linkmanAdd;
    }

    public void setLinkmanAdd(String linkmanAdd) {
        this.linkmanAdd = linkmanAdd;
    }

    public String getLinkmanTel() {
        return linkmanTel;
    }

    public void setLinkmanTel(String linkmanTel) {
        this.linkmanTel = linkmanTel;
    }

    public String getPactName() {
        return pactName;
    }

    public void setPactName(String pactName) {
        this.pactName = pactName;
    }

    public String getPatientState() {
        return patientState;
    }

    public void setPatientState(String patientState) {
        this.patientState = patientState;
    }
}
