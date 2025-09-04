package com.gateReceiptServices.repository;

import com.gateReceiptServices.model.ASNLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsnLineRepository extends JpaRepository<ASNLine,Integer> {
    public List<ASNLine> findByAsnHeadIdIdAndSubOrganizationIdAndIsDeleted(Integer asnHeadId, Integer organizationId, boolean status);
    public ASNLine findByIdAndSubOrganizationIdAndIsDeleted(Integer asnHeadId, Integer organizationId, boolean status);
    public List<ASNLine> findByPurchaseOrderLineIdAndOrganizationIdAndIsDeleted(Integer asnHeadId, Integer organizationId, boolean status);
    public List<ASNLine> findBySubOrganizationIdAndAsnHeadIdIdAndIsDeleted(Integer subOrgId, Integer asnHeadId, boolean status);

    List<ASNLine> findBySubOrganizationIdAndAsnHeadIdIdAndIsDeletedAndIsAccepted(Integer subOrgId, Integer id, boolean b, boolean b1);
}
