package com.gateReceiptServices.repository;

import com.gateReceiptServices.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document,Integer> {

    public List<Document> findByIsDeletedAndSubOrganizationIdAndAsnHeadId(boolean b, Integer orgId, Integer asnId);
    public Document findByIsDeletedAndSubOrganizationIdAndId(boolean b, Integer orgId, Integer asnId);
    public List<Document> findByIsDeletedAndSubOrganizationIdAndPurchaseOrderHeadId(boolean b, Integer orgId, Integer asnId);
    List<Document> findByIsDeletedAndSubOrganizationIdAndAsnHeadIdAndIsAccepted(boolean b, Integer subOrgId, Integer asnHeadId, boolean b1);
    List<Document> findByIsDeletedAndSubOrganizationIdAndPurchaseOrderHeadIdAndIsAccepted(boolean b, Integer subOrgId, Integer poHeadId, boolean b1);
}
