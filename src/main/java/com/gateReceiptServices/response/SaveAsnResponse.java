package com.gateReceiptServices.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveAsnResponse {
private Integer id;
private Integer asnNumber;
private String supplierCode;
private String supplierName;
private Integer purchaseOrder;
private String purchaseOrderDate;
}
