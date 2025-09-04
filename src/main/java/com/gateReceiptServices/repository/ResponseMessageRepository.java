package com.gateReceiptServices.repository;

import com.gateReceiptServices.model.ResponseMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseMessageRepository extends JpaRepository<ResponseMessage, Integer> {
}
