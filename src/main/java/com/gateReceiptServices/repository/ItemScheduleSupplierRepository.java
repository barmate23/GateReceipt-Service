package com.gateReceiptServices.repository;

import com.gateReceiptServices.model.ItemScheduleSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ItemScheduleSupplierRepository extends JpaRepository<ItemScheduleSupplier, Integer> {
    ItemScheduleSupplier findByRequiredDateAndItemScheduleItemId(Date requiredDate, Integer itemId);

    List<ItemScheduleSupplier> findByIsDeletedAndSubOrganizationIdAndItemScheduleItemIdAndItemScheduleScheduleMonthAndSupplierId(boolean b, Integer subOrgId, Integer itemId, String month, Integer supplierId);
    List<ItemScheduleSupplier> findByIsDeletedAndSubOrganizationIdAndItemScheduleScheduleMonthAndSupplierIdOrderByIdDesc(boolean b, Integer subOrgId, String month, Integer supplierId);
}
