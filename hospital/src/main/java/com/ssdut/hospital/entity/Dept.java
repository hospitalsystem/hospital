package com.ssdut.hospital.entity;

import javax.persistence.*;

@Entity
@Table(name = "dept")
public class Dept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="deptNo")
    private Integer deptNo;
    @Column(name="deptName",length = 20)
    private String deptName;

    public Integer getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
