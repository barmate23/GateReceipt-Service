package com.gateReceiptServices.repository;

import com.gateReceiptServices.model.PPEHead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PPEHeadRepository extends JpaRepository<PPEHead,Integer> {
List<PPEHead> findBySubOrganizationIdAndIsDeletedAndStatus(Integer subOrgId, boolean isDeleted, Integer status);
PPEHead findBySubOrganizationIdAndIsDeletedAndId(Integer subOrgId, boolean isDeleted, Integer id);
}
