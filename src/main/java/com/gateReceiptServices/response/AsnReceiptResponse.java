package com.gateReceiptServices.response;


import com.gateReceiptServices.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsnReceiptResponse {
    private ASNHead asnHead;
    private PurchaseOrderHead poHead;
    private List<ASNLine> asnLineList;
    private List<PurchaseOrderLine> poLineList;
    private Transport transportDetails;
    private Insurance insuranceDetails;
    private List<Document> documentList;
    List<InvoiceHead> invoiceHeadList;
    List<QCCertificate> qcCertificateList;
    private User buyer;
}
