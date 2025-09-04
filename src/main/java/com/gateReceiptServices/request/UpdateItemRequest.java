package com.gateReceiptServices.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateItemRequest {
    private Integer asnLineId;
    private Integer poLineId;
    private Integer invoiceQuantity;
    private boolean isAccepted;
}
