package com.ssdut.hospital.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PrepayPK implements Serializable {

    @Column
    private Integer inpatient_no;

    @Column
    private Integer happen_no;

    public int getInpatient_no() {
        return inpatient_no;
    }

    public void setInpatient_no(Integer inpatient_no) {
        this.inpatient_no = inpatient_no;
    }

    public int getHappen_no() {
        return happen_no;
    }

    public void setHappen_no(Integer happen_no) {
        this.happen_no = happen_no;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((happen_no == null) ? 0 : happen_no.hashCode());
        result = prime * result + ((inpatient_no == null) ? 0 : inpatient_no.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;

        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PrepayPK other = (PrepayPK) obj;
        if (happen_no == null) {
            if (other.happen_no != null)
                return false;
        } else if (!happen_no.equals(other.happen_no))
            return false;
        if (inpatient_no == null) {
            if (other.inpatient_no != null)
                return false;
        } else if (!inpatient_no.equals(other.inpatient_no))
            return false;
        return true;
    }
}
