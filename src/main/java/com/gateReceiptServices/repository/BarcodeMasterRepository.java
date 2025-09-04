package com.gateReceiptServices.repository;

import com.gateReceiptServices.model.BarcodeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BarcodeMasterRepository extends JpaRepository<BarcodeMaster, Integer> {
    BarcodeMaster findByLabelFor(String cin);
}
