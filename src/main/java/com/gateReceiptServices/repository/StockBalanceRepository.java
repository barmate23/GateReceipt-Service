package com.gateReceiptServices.repository;

import com.gateReceiptServices.model.StockBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockBalanceRepository extends JpaRepository<StockBalance,Integer> {
    StockBalance  findByIsDeletedAndItemIdIdAndSubOrganizationId(boolean isDeleted, Integer itemid, Integer subOrgId);
}
