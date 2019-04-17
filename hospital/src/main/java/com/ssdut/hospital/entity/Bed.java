package com.ssdut.hospital.entity;


import javax.persistence.*;

@Entity
@Table(name = "bed")
public class Bed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  bed_no;
    @Column(length = 1)
    private String bed_state;


    public Integer  getBed_no() {
        return bed_no;
    }

    public void setBed_no(Integer  bed_no) {
        this.bed_no = bed_no;
    }

    public String getBed_state() {
        return bed_state;
    }

    public void setBed_state(String bed_state) {
        this.bed_state = bed_state;
    }


}
