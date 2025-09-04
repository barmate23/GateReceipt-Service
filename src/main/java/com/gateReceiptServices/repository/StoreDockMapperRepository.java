package com.gateReceiptServices.repository;

import com.gateReceiptServices.model.ASNHead;
import com.gateReceiptServices.model.Dock;
import com.gateReceiptServices.model.StoreDockMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface StoreDockMapperRepository extends JpaRepository<StoreDockMapper, Integer> {

    List<StoreDockMapper> findByIsDeletedAndSubOrganizationIdAndDockIdAndStoreIdIn(boolean isDeleted, Integer subOrgId, Integer dockId, Set<Integer> storeIds);
}
