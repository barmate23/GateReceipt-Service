package com.gateReceiptServices.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveInvoiceLineRequest {
    private String itemCode;
    private String itemName;
    private Integer invoiceItemQuantity;
}
