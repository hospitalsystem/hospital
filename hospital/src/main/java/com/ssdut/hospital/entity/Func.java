package com.ssdut.hospital.entity;

import javax.persistence.*;

@Entity
@Table(name = "func")
public class Func {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer func_no;
    @Column(length = 64)
    private String func_name;

    public Integer getFunc_no() {
        return func_no;
    }

    public void setFunc_no(Integer func_no) {
        this.func_no = func_no;
    }

    public String getFunc_name() {
        return func_name;
    }

    public void setFunc_name(String func_name) {
        this.func_name = func_name;
    }
}
