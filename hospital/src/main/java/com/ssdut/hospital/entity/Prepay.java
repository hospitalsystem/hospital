package com.ssdut.hospital.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name = "prepay")
public class Prepay {

    @EmbeddedId
    //上述注解标注id这个属性为实体的标识符
    private PrepayPK id;

    @Column(length = 10, nullable = false)
    private String patient_name;
    @Column(precision=10, scale=2, nullable = false)
    private BigDecimal prepay_cost;
    @Column
    private Integer recipe_no;
    @Column(length = 1)
    private String prepay_state;
    @Column(length = 1)
    private String issettlement;
    @Column
    private Integer staff_no;
    @Column
    private Date oper_date;
    @Column(length = 1)
    private String prepay_method;

    public PrepayPK getId() {
        return id;
    }

    public void setId(PrepayPK id) {
        this.id = id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public BigDecimal getPrepay_cost() {
        return prepay_cost;
    }

    public void setPrepay_cost(BigDecimal prepay_cost) {
        this.prepay_cost = prepay_cost;
    }

    public Integer getRecipe_no() {
        return recipe_no;
    }

    public void setRecipe_no(Integer recipe_no) {
        this.recipe_no = recipe_no;
    }

    public String getPrepay_state() {
        return prepay_state;
    }

    public void setPrepay_state(String prepay_state) {
        this.prepay_state = prepay_state;
    }

    public String getIssettlement() {
        return issettlement;
    }

    public void setIssettlement(String issettlement) {
        this.issettlement = issettlement;
    }

    public Integer getStaff_no() {
        return staff_no;
    }

    public void setStaff_no(Integer staff_no) {
        this.staff_no = staff_no;
    }

    public Date getOper_date() {
        return oper_date;
    }

    public void setOper_date(Date oper_date) {
        this.oper_date = oper_date;
    }

    public String getPrepay_method() {
        return prepay_method;
    }

    public void setPrepay_method(String prepay_method) {
        this.prepay_method = prepay_method;
    }
}
