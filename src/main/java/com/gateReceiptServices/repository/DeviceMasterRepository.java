package com.gateReceiptServices.repository;

import com.gateReceiptServices.model.DeviceMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DeviceMasterRepository extends JpaRepository<DeviceMaster, Integer> {

}
