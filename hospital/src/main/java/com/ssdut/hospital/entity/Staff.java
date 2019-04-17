package com.ssdut.hospital.entity;


import javax.persistence.*;

@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer staff_no;
    @Column(length = 20, nullable = false)
    private String staff_name;
    @Column(length = 50)
    private String staff_spell;
    @Column(length = 1, nullable = false)
    private String staff_role;
    @Column(length = 20, nullable = false)
    private String staff_password;

    public Integer getStaff_no() {
        return staff_no;
    }

    public void setStaff_no(Integer staff_no) {
        this.staff_no = staff_no;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getStaff_spell() {
        return staff_spell;
    }

    public void setStaff_spell(String staff_spell) {
        this.staff_spell = staff_spell;
    }

    public String getStaff_role() {
        return staff_role;
    }

    public void setStaff_role(String staff_role) {
        this.staff_role = staff_role;
    }

    public String getStaff_password() {
        return staff_password;
    }

    public void setStaff_password(String staff_password) {
        this.staff_password = staff_password;
    }
}
