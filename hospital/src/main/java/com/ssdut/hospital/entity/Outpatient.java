package com.ssdut.hospital.entity;


import javax.persistence.*;

@Entity
@Table(name = "outpatient")
public class Outpatient {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer outpatient_no;
    @Column(length = 10, nullable = false)
    private String patient_name;
    @Column(length = 18, nullable = false)
    private String idcard_no;
    @Column(length = 50)
    private String diagnose;


    public Integer getOutpatient_no() {
        return outpatient_no;
    }

    public void setOutpatient_no(Integer outpatient_no) {
        this.outpatient_no = outpatient_no;
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

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }
}
