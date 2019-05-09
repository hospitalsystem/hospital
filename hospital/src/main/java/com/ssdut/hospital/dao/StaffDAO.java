package com.ssdut.hospital.dao;

import com.ssdut.hospital.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StaffDAO extends JpaRepository<Staff, Integer> {
    Staff findByStaffSpell(String staffSpell);
}
