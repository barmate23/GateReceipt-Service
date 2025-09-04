package com.gateReceiptServices.repository;

import com.gateReceiptServices.model.Transport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportRepository extends JpaRepository<Transport,Integer> {
    Transport findByIsDeletedAndSubOrganizationIdAndAsnHeadId(boolean b, Integer orgId, Integer id);
    Transport findByIsDeletedAndSubOrganizationIdAndPurchaseOrderHeadId(boolean b, Integer orgId, Integer id);

    Transport findByIdAndSubOrganizationIdAndIsDeleted(Integer transporterID, Integer subOrgId, boolean b);
}
