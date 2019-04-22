package com.ssdut.hospital.entity;

import javax.persistence.*;

@Entity
@Table(name = "func")
public class Func {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="funcNo")
    private Integer funcNo;
    @Column(name="funcName",length = 64)
    private String funcName;

    public Integer getFuncNo() {
        return funcNo;
    }

    public void setFuncNo(Integer funcNo) {
        this.funcNo = funcNo;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }
}
