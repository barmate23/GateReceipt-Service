package com.gateReceiptServices.repository;

import com.gateReceiptServices.model.PurchaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseStatusRepository extends JpaRepository<PurchaseStatus, Integer> {
    PurchaseStatus findByStatusNameAndStatusType(String statusName, String statusType);
}
