package com.gateReceiptServices.repository;

import com.gateReceiptServices.model.ASNHead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsnHeadRepository extends JpaRepository<ASNHead, Integer> {
    public List<ASNHead> findByPurchaseOrderHeadIdAndSubOrganizationIdAndIsDeleted(Integer poId, Integer organizationId, boolean status);

    public List<ASNHead> findBySupplierIdAndSubOrganizationIdAndIsDeletedAndPurchaseStatusId(Integer poId, Integer organizationId, boolean isDeleted, Integer status);

    public ASNHead findByIdAndSubOrganizationIdAndIsDeleted(Integer id, Integer organizationId, boolean status);
    public ASNHead findByAsnNumberAndSubOrganizationIdAndIsDeleted(String id, Integer organizationId, boolean status);
    public ASNHead findByAsnNumberAndSubOrganizationIdAndIsDeletedAndPurchaseStatusStatusName(String id, Integer organizationId, boolean isDeleted, String status);

    List<ASNHead> findBySubOrganizationIdAndIsDeletedAndPurchaseStatusId(Integer organizationId, boolean isDeleted, Integer status);

    @Query("SELECT asn FROM ASNHead asn " +
            "WHERE asn.subOrganizationId = :organizationId " +
            "AND asn.isDeleted = :isDeleted " +
            "AND asn.purchaseStatus.id = :status " +
            "AND asn.purchaseOrderHead.purchaseOrderDate BETWEEN :startDate AND :endDate")
    List<ASNHead> findBySubOrganizationIdAndIsDeletedAndPurchaseStatusIdAndPurchaseOrderHeadPurchaseOrderDate(
            @Param("organizationId") Integer organizationId,
            @Param("isDeleted") boolean isDeleted,
            @Param("status") Integer status,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate);

}
