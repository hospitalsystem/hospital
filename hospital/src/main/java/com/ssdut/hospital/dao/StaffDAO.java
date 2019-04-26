package com.ssdut.hospital.dao;

import com.ssdut.hospital.entity.Prepay;
import com.ssdut.hospital.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffDAO extends JpaRepository<Staff, Integer> {
    Staff findByStaffSpell(String staffSpell);
}
