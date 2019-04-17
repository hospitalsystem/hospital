package com.ssdut.hospital.entity;

import javax.persistence.*;
import java.math.BigDecimal;



@Entity
@Table(name = "cost")
public class Cost {

    @EmbeddedId
    //上述注解标注id这个属性为实体的标识符
    private CostPK id;

    @Column(length = 20)
    private String cost_name;
    @Column(precision=10, scale=2)
    private BigDecimal cost_amount;

    public CostPK getId() {
        return id;
    }

    public void setId(CostPK id) {
        this.id = id;
    }
    public String getCost_name() {
        return cost_name;
    }

    public void setCost_name(String cost_name) {
        this.cost_name = cost_name;
    }

    public BigDecimal getCost_amount() {
        return cost_amount;
    }

    public void setCost_amount(BigDecimal cost_amount) {
        this.cost_amount = cost_amount;
    }



}
