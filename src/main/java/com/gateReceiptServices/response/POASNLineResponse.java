package com.gateReceiptServices.response;


import com.gateReceiptServices.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class POASNLineResponse {
    private ASNHead asnHead;
    private List<ASNLine> asnLineList;
    private User buyer;
    private Supplier supplier;
    private String userName;
    private String supplierUserName;
    private PurchaseOrderHead purchaseOrderHead;
    private List<PurchaseOrderLine> purchaseOrderLineList;
}
