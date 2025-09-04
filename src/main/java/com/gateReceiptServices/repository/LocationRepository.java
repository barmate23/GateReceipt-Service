package com.gateReceiptServices.repository;

import com.gateReceiptServices.model.ASNLine;
import com.gateReceiptServices.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location,Integer> {
    List<Location> findBySubOrganizationIdAndIsDeletedAndItemIdAndZoneAreaStoreIdIn(Integer subOrgId, boolean isDeleted, Integer itemId, List<Integer> storeIds);
    List<Location> findBySubOrganizationIdAndIsDeletedAndItemId(Integer subOrgId, boolean isDeleted, Integer itemId);

}
