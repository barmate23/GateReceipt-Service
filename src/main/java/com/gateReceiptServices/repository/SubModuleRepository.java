package com.gateReceiptServices.repository;

import com.gateReceiptServices.model.SubModules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SubModuleRepository extends JpaRepository<SubModules, Integer> {

    SubModules findBySubModuleCode(String subModuleCode);
}
