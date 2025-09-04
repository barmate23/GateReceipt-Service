package com.gateReceiptServices.response;

import com.gateReceiptServices.model.Organization;
import com.gateReceiptServices.model.PurchaseOrderHead;
import com.gateReceiptServices.model.PurchaseOrderLine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ASNResponse {

    private List<PurchaseOrderLine> lineList;
    private PurchaseOrderHead purchaseOrderHead;
    private Organization organization;
}
