package com.gateReceiptServices.repository;

import com.gateReceiptServices.model.TransportAccompyMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportAccompanyRepository extends JpaRepository<TransportAccompyMapper, Integer> {


    List<TransportAccompyMapper> findBySubOrganizationIdAndIsDeletedAndTransportId(Integer subOrgId, boolean b, Integer id);
}
