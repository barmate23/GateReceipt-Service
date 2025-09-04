package com.gateReceiptServices.repository;



import com.gateReceiptServices.model.SysConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigurationRepository extends JpaRepository<SysConfiguration,Integer> {

    List<SysConfiguration> findByIsDeleted(boolean b);
}
