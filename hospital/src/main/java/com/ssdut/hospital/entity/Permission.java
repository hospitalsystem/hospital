package com.ssdut.hospital.entity;

import javax.lang.model.element.NestingKind;
import javax.persistence.*;

@Entity
@Table(name = "permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer staff_no;
    @Column
    private Integer func_no;
    @Column(length = 1)
    private String func_read;
    @Column(length = 1)
    private String func_add;
    @Column(length = 1)
    private String func_edit;
    @Column(length = 1)
    private String func_delete;

    public Integer getStaff_no() {
        return staff_no;
    }

    public void setStaff_no(Integer staff_no) {
        this.staff_no = staff_no;
    }

    public Integer getFunc_no() {
        return func_no;
    }

    public void setFunc_no(Integer func_no) {
        this.func_no = func_no;
    }

    public String getFunc_read() {
        return func_read;
    }

    public void setFunc_read(String func_read) {
        this.func_read = func_read;
    }

    public String getFunc_add() {
        return func_add;
    }

    public void setFunc_add(String func_add) {
        this.func_add = func_add;
    }

    public String getFunc_edit() {
        return func_edit;
    }

    public void setFunc_edit(String func_edit) {
        this.func_edit = func_edit;
    }

    public String getFunc_delete() {
        return func_delete;
    }

    public void setFunc_delete(String func_delete) {
        this.func_delete = func_delete;
    }
}
