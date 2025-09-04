package com.gateReceiptServices.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsnSupplierItemResponse {
    Integer asnHeadId;
    Integer poHeadId;
    String itemRequiredOnDate;
    Integer asnNumber;
    String purchaseOrderAsn;
    Integer remainingDays;
    Integer leadTime;
}
