package com.ssdut.hospital.entity;

import javax.persistence.*;

@Entity
@Table(name = "dept")
public class Dept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  dept_no;
    @Column(length = 20)
    private String dept_name;

    public Integer  getDept_no() {
        return dept_no;
    }

    public void setDept_no(Integer  dept_no) {
        this.dept_no = dept_no;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }
}
