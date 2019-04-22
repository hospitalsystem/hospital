package com.ssdut.hospital.entity;


import javax.persistence.*;

@Entity
@Table(name = "bed")
public class Bed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bedNo")
    private Integer bedNo;
    @Column(name="bedState",length = 1)
    private String bedState;


    public Integer getBedNo() {
        return bedNo;
    }

    public void setBedNo(Integer bedNo) {
        this.bedNo = bedNo;
    }

    public String getBedState() {
        return bedState;
    }

    public void setBedState(String bedState) {
        this.bedState = bedState;
    }


}
