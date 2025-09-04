package com.gateReceiptServices.repository;

import com.gateReceiptServices.model.Container;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContainerRepository extends JpaRepository<Container, Integer> {

Container findByItemIdAndIsDeletedAndSubOrganizationId(Integer itamId, boolean isDeleted, Integer subOrgId);

}
