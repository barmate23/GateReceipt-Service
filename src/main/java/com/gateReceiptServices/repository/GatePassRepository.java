package com.gateReceiptServices.repository;

import com.gateReceiptServices.model.Container;
import com.gateReceiptServices.model.GatePassGenerate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GatePassRepository extends JpaRepository<GatePassGenerate, Integer> {
GatePassGenerate findByIsDeletedAndSubOrganizationIdAndTransportId(boolean isDeleted, Integer subOrgId, Integer transportId);
}
