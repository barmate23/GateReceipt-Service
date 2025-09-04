package com.gateReceiptServices.repository;

import com.gateReceiptServices.model.ItemSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemScheduleRepository extends JpaRepository<ItemSchedule, Integer> {

}
