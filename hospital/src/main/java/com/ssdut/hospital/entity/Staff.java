package com.ssdut.hospital.entity;


import javax.persistence.*;

@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="staffNo")
    private Integer staffNo;
    @Column(name="staffName",length = 20, nullable = false)
    private String staffName;
    @Column(name="staffSpell",length = 50)
    private String staffSpell;
    @Column(name="staffRole",length = 1, nullable = false)
    private String staffRole;
    @Column(name="staffPassword",length = 20, nullable = false)
    private String staffPassword;

    public Integer getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(Integer staffNo) {
        this.staffNo = staffNo;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffSpell() {
        return staffSpell;
    }

    public void setStaffSpell(String staffSpell) {
        this.staffSpell = staffSpell;
    }

    public String getStaffRole() {
        return staffRole;
    }

    public void setStaffRole(String staffRole) {
        this.staffRole = staffRole;
    }

    public String getStaffPassword() {
        return staffPassword;
    }

    public void setStaffPassword(String staffPassword) {
        this.staffPassword = staffPassword;
    }
}
