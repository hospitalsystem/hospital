package com.ssdut.hospital.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer card_no;
    @Column(length = 10, nullable = false)
    private String patient_name;
    @Column(length = 1, nullable = false)
    private String sex_code;
    @Column(length = 18, nullable = false)
    private String idcard_no;
    @Column(length = 10)
    private String nation;
    @Column
    private Date birthday;
    @Column
    private Integer age;
    @Column(length = 15)
    private String country;
    @Column(length = 50)
    private String dist;
    @Column(length = 50)
    private String birth_area;
    @Column(length = 1)
    private String mari_code;
    @Column(length = 50)
    private String work_name;
    @Column(length = 2)
    private String occupation;
    @Column(length = 20)
    private String work_tel;
    @Column(length = 50)
    private String home;
    @Column(length = 20)
    private String home_tel;
    @Column(length = 10)
    private String linkman_name;
    @Column(length = 1)
    private String linkman_relation;
    @Column(length = 50)
    private String linkman_add;
    @Column(length = 20)
    private String linkman_tel;
    @Column(length = 50)
    private String pact_name;

    public Integer getCard_no() {
        return card_no;
    }

    public void setCard_no(Integer card_no) {
        this.card_no = card_no;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getSex_code() {
        return sex_code;
    }

    public void setSex_code(String sex_code) {
        this.sex_code = sex_code;
    }

    public String getIdcard_no() {
        return idcard_no;
    }

    public void setIdcard_no(String idcard_no) {
        this.idcard_no = idcard_no;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

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

    public String getBirth_area() {
        return birth_area;
    }

    public void setBirth_area(String birth_area) {
        this.birth_area = birth_area;
    }

    public String getMari_code() {
        return mari_code;
    }

    public void setMari_code(String mari_code) {
        this.mari_code = mari_code;
    }

    public String getWork_name() {
        return work_name;
    }

    public void setWork_name(String work_name) {
        this.work_name = work_name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getWork_tel() {
        return work_tel;
    }

    public void setWork_tel(String work_tel) {
        this.work_tel = work_tel;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getHome_tel() {
        return home_tel;
    }

    public void setHome_tel(String home_tel) {
        this.home_tel = home_tel;
    }

    public String getLinkman_name() {
        return linkman_name;
    }

    public void setLinkman_name(String linkman_name) {
        this.linkman_name = linkman_name;
    }

    public String getLinkman_relation() {
        return linkman_relation;
    }

    public void setLinkman_relation(String linkman_relation) {
        this.linkman_relation = linkman_relation;
    }

    public String getLinkman_add() {
        return linkman_add;
    }

    public void setLinkman_add(String linkman_add) {
        this.linkman_add = linkman_add;
    }

    public String getLinkman_tel() {
        return linkman_tel;
    }

    public void setLinkman_tel(String linkman_tel) {
        this.linkman_tel = linkman_tel;
    }

    public String getPact_name() {
        return pact_name;
    }

    public void setPact_name(String pact_name) {
        this.pact_name = pact_name;
    }
}
