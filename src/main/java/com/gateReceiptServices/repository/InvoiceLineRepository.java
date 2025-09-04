package com.gateReceiptServices.repository;

import com.gateReceiptServices.model.InvoiceLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceLineRepository extends JpaRepository<InvoiceLine,Integer> {

}
