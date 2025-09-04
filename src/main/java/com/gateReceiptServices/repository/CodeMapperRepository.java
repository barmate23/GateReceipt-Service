package com.gateReceiptServices.repository;

import com.gateReceiptServices.model.ASNHead;
import com.gateReceiptServices.model.CodeMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeMapperRepository extends JpaRepository<CodeMapper, Integer> {
    @Query("SELECT COUNT(c) " +
            "FROM CodeMapper c " +
            "WHERE c.subOrganizationId = :subOrganizationId " +
            "AND c.isDeleted = :isDeleted " +
            "AND FUNCTION('YEAR', c.createdOn) = :year " +
            "AND FUNCTION('MONTH', c.createdOn) = :month")
    Long countBySubOrganizationIdAndIsDeletedAndCreatedOnYearAndCreatedOnMonth(
            @Param("subOrganizationId") Integer subOrganizationId,
            @Param("isDeleted") Boolean isDeleted,
            @Param("year") Integer year,
            @Param("month") Integer month);

    List<CodeMapper> findBySubOrganizationIdAndAsnNumber(Integer subOrgId, String asnNumber);

    List<CodeMapper> findBySubOrganizationIdAndPoNumber(Integer subOrgId, String purchaseOrderNumber);
}
