package com.gateReceiptServices.repository;

import com.gateReceiptServices.model.InvoiceHead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceHeadRepository extends JpaRepository<InvoiceHead, Integer> {
    List<InvoiceHead> findBySubOrganizationIdAndAsnHeadIdAndIsDeleted(Integer subOrgId, Integer asnHeadId, boolean isDeleted);
    InvoiceHead findBySubOrganizationIdAndIdAndIsDeleted(Integer subOrgId, Integer id, boolean isDeleted);

    List<InvoiceHead> findBySubOrganizationIdAndPurchaseOrderHeadIdAndIsDeleted(Integer subOrgId, Integer asnHeadId, boolean isDeleted);

    List<InvoiceHead> findBySubOrganizationIdAndAsnHeadIdAndIsDeletedAndIsAccepted(Integer subOrgId, Integer asnHeadId, boolean b, boolean b1);

    List<InvoiceHead> findBySubOrganizationIdAndPurchaseOrderHeadIdAndIsDeletedAndIsAccepted(Integer subOrgId, Integer poHeadId, boolean b, boolean b1);
}
