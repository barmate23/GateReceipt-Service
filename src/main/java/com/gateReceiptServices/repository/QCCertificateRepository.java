package com.gateReceiptServices.repository;

import com.gateReceiptServices.model.QCCertificate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QCCertificateRepository extends JpaRepository<QCCertificate,Integer> {
    List<QCCertificate> findBySubOrganizationIdAndAsnLineAsnHeadIdIdAndIsDeleted(Integer subOrgId, Integer asnHeadId, boolean isDeleted);
    QCCertificate findBySubOrganizationIdAndIdAndIsDeleted(Integer subOrgId, Integer id, boolean isDeleted);
    List<QCCertificate> findBySubOrganizationIdAndPurchaseOrderLinePurchaseOrderHeadIdAndIsDeleted(Integer subOrgId, Integer asnHeadId, boolean isDeleted);
    List<QCCertificate> findBySubOrganizationIdAndPurchaseOrderLinePurchaseOrderHeadIdAndIsDeletedAndIsAccepted(Integer subOrgId, Integer poHeadId, boolean b, boolean b1);
    List<QCCertificate> findBySubOrganizationIdAndAsnLineAsnHeadIdIdAndIsDeletedAndIsAccepted(Integer subOrgId, Integer asnHeadId, boolean b, boolean b1);
}
