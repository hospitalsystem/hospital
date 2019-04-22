package com.ssdut.hospital.entity;

import javax.persistence.*;

@Entity
@Table(name = "permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="staffNo")
    private Integer staffNo;
    @Column(name="funcNo")
    private Integer funcNo;
    @Column(name="funcRead",length = 1)
    private String funcRead;
    @Column(name="funcAdd",length = 1)
    private String funcAdd;
    @Column(name="funcEdit",length = 1)
    private String funcEdit;
    @Column(name="funcDelete",length = 1)
    private String funcDelete;

    public Integer getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(Integer staffNo) {
        this.staffNo = staffNo;
    }

    public Integer getFuncNo() {
        return funcNo;
    }

    public void setFuncNo(Integer funcNo) {
        this.funcNo = funcNo;
    }

    public String getFuncRead() {
        return funcRead;
    }

    public void setFuncRead(String funcRead) {
        this.funcRead = funcRead;
    }

    public String getFuncAdd() {
        return funcAdd;
    }

    public void setFuncAdd(String funcAdd) {
        this.funcAdd = funcAdd;
    }

    public String getFuncEdit() {
        return funcEdit;
    }

    public void setFuncEdit(String funcEdit) {
        this.funcEdit = funcEdit;
    }

    public String getFuncDelete() {
        return funcDelete;
    }

    public void setFuncDelete(String funcDelete) {
        this.funcDelete = funcDelete;
    }
}
