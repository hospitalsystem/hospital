package com.ssdut.hospital.entity;


import javax.persistence.*;

@Entity
@Table(name = "outpatient")
public class Outpatient {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="outpatientNo")
    private Integer outpatientNo;
    @Column(name="patientName",length = 10, nullable = false)
    private String patientName;
    @Column(name="idCard",length = 18, nullable = false)
    private String idCard;
    @Column(length = 50)
    private String diagnose;


    public Integer getOutpatientNo() {
        return outpatientNo;
    }

    public void setOutpatientNo(Integer outpatientNo) {
        this.outpatientNo = outpatientNo;
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

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }
}
