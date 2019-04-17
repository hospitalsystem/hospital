package com.ssdut.hospital.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "inpatient")
public class Inpatient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inpatient_no;
    @Column(length = 10, nullable = false)
    private String patient_name;
    @Column(length = 18, nullable = false)
    private String idcard_no;
    @Column(nullable = false)
    private Date in_date;
    @Column(length = 1, nullable = false)
    private String in_source;
    @Column(length = 1)
    private String in_path;
    @Column(length = 50)
    private String status;
    @Column(nullable = false)
    private Integer dept_no;
    @Column(nullable = false)
    private Integer bed_no;
    @Column(nullable = false)
    private Integer house_doc_no;
    @Column
    private Integer charge_doc_no;
    @Column
    private Integer chief_doc_no;
    @Column
    private Integer duty_nurse_no;
    @Column
    private Date out_date;
    @Column(length = 1)
    private String out_state;

    public Integer getInpatient_no() {
        return inpatient_no;
    }

    public void setInpatient_no(Integer inpatient_no) {
        this.inpatient_no = inpatient_no;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getIdcard_no() {
        return idcard_no;
    }

    public void setIdcard_no(String idcard_no) {
        this.idcard_no = idcard_no;
    }

    public Date getIn_date() {
        return in_date;
    }

    public void setIn_date(Date in_date) {
        this.in_date = in_date;
    }

    public String getIn_source() {
        return in_source;
    }

    public void setIn_source(String in_source) {
        this.in_source = in_source;
    }

    public String getIn_path() {
        return in_path;
    }

    public void setIn_path(String in_path) {
        this.in_path = in_path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDept_no() {
        return dept_no;
    }

    public void setDept_no(Integer dept_no) {
        this.dept_no = dept_no;
    }

    public Integer getBed_no() {
        return bed_no;
    }

    public void setBed_no(Integer bed_no) {
        this.bed_no = bed_no;
    }

    public Integer getHouse_doc_no() {
        return house_doc_no;
    }

    public void setHouse_doc_no(Integer house_doc_no) {
        this.house_doc_no = house_doc_no;
    }

    public Integer getCharge_doc_no() {
        return charge_doc_no;
    }

    public void setCharge_doc_no(Integer charge_doc_no) {
        this.charge_doc_no = charge_doc_no;
    }

    public Integer getChief_doc_no() {
        return chief_doc_no;
    }

    public void setChief_doc_no(Integer chief_doc_no) {
        this.chief_doc_no = chief_doc_no;
    }

    public Integer getDuty_nurse_no() {
        return duty_nurse_no;
    }

    public void setDuty_nurse_no(Integer duty_nurse_no) {
        this.duty_nurse_no = duty_nurse_no;
    }

    public Date getOut_date() {
        return out_date;
    }

    public void setOut_date(Date out_date) {
        this.out_date = out_date;
    }

    public String getOut_state() {
        return out_state;
    }

    public void setOut_state(String out_state) {
        this.out_state = out_state;
    }
}
