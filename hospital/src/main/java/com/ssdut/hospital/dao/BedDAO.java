package com.ssdut.hospital.dao;

import com.ssdut.hospital.entity.Bed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BedDAO extends JpaRepository<Bed, Integer> {
}