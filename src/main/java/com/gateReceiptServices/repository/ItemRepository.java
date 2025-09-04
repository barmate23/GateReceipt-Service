package com.gateReceiptServices.repository;

import com.gateReceiptServices.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    public List<Item> findByIsDeletedAndOrganizationId(boolean status, Integer orgId);
    Item findByIsDeletedAndSubOrganizationIdAndNameOrItemId(boolean status, Integer orgId, String itemName, String itemCode);

    public List<Item> findByIsDeletedAndIdIn(boolean status,List<Integer> idList);
}
