package com.gateReceiptServices.repository;

import com.gateReceiptServices.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
User findByIsDeletedAndIdAndSubOrganizationId(boolean isDeleted, Integer id, Integer subOrgId);
User findByIsDeletedAndSupplierIdAndSubOrganizationId(boolean isDeleted, Integer id, Integer subOrgId);
}
