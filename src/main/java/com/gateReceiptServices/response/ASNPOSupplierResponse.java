package com.gateReceiptServices.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ASNPOSupplierResponse {
    Integer supplierId;
    Integer poHeadId;
    String supplierCode;
    String supplierName;
    Integer purchaseOrderNumber;
    Integer purchaseOrderQuantity;
    Integer balanceQuantity;

}
