package com.gateReceiptServices.repository;

import com.gateReceiptServices.model.PurchaseOrderHead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrderHeadRepository extends JpaRepository<PurchaseOrderHead,Integer> {

    List<PurchaseOrderHead> findByOrganizationIdAndPurchaseOrderNumber(Integer organizationId, String purchaseOrderNumber);
    List<PurchaseOrderHead> findByOrganizationIdAndIsDeleted(Integer organizationId, boolean status);
    PurchaseOrderHead findByIdAndIsDeleted(Integer Id, boolean status);
    PurchaseOrderHead findBySubOrganizationIdAndPurchaseOrderNumberAndIsDeleted(Integer subOrgId, String poNumber, boolean status);
    PurchaseOrderHead findBySubOrganizationIdAndPurchaseOrderNumberAndIsDeletedAndStatusStatusName(Integer subOrgId, String poNumber, boolean isDeleted, String statusName);

    List<PurchaseOrderHead> findBySupplierIdAndSubOrganizationIdAndIsDeleted(Integer userId, Integer orgId, boolean b);

    PurchaseOrderHead findBySubOrganizationIdAndIdAndIsDeleted(Integer subOrgId, Integer poHeadId, boolean b);
}
