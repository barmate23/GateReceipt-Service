package com.gateReceiptServices.repository;

import com.gateReceiptServices.model.PPELine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PPELineRepository extends JpaRepository<PPELine, Integer> {
    List<PPELine> findByIsDeletedAndSubOrganizationIdAndPpeHeadPpeStatusStatusNameInOrderByIdDesc(Boolean isDeleted, Integer subOrganizationId, List<String> statusList);
    List<PPELine> findByIsDeletedAndSubOrganizationIdAndPpeHeadId(Boolean isDeleted, Integer subOrganizationId, Integer ppeHeadId);

    @Query("SELECT pl FROM PPELine pl " +
            "JOIN pl.ppeHead ph JOIN pl.item i " +
            "WHERE pl.isDeleted = false " +
            "AND (:subOrganizationId IS NULL OR pl.subOrganizationId = :subOrganizationId) " +
            "AND (:itemId IS NULL OR i.id = :itemId) " +
            "AND (:month IS NULL OR EXTRACT(MONTH FROM ph.startDate) = :month)")
    List<PPELine> findByIsDeletedAndSubOrganizationIdAndItemIdAndPpeHeadStartDate(
            @Param("subOrganizationId") Integer subOrganizationId,
            @Param("itemId") Integer itemId,
            @Param("month") Integer month);

}
