package com.gateReceiptServices.repository;



import com.gateReceiptServices.model.PODocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoDocumentRepository extends JpaRepository<PODocument,Integer> {

}
