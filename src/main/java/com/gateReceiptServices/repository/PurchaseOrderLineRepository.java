package com.gateReceiptServices.repository;

import com.gateReceiptServices.model.PurchaseOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseOrderLineRepository extends JpaRepository<PurchaseOrderLine,Integer> {

    Optional<PurchaseOrderLine> findById(Integer id);
    List<PurchaseOrderLine> findBySubOrganizationIdAndIsDeletedAndPurchaseOrderHeadId(Integer orgId, Boolean status, Integer purchaseOrderHeaderId);
    PurchaseOrderLine findBySubOrganizationIdAndIsDeletedAndId(Integer orgId, Boolean status, Integer id);
    List<PurchaseOrderLine> findBySubOrganizationIdAndIsDeletedAndPurchaseOrderHeadIdAndIsAccepted(Integer subOrgId, boolean b, Integer id, boolean b1);
}
