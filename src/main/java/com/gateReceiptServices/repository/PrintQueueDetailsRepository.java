package com.gateReceiptServices.repository;


import com.gateReceiptServices.model.PrintQueueDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PrintQueueDetailsRepository extends JpaRepository<PrintQueueDetail, Integer> {

}
