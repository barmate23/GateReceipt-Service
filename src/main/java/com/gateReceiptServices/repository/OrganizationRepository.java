package com.gateReceiptServices.repository;

import com.gateReceiptServices.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization,Integer> {

    Organization findByIdAndIsDeleted(Integer subOrgId, boolean isDeleted);
}
