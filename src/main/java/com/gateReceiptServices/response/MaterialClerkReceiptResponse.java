package com.gateReceiptServices.response;

import com.gateReceiptServices.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialClerkReceiptResponse {

    private ASNHead asnHead;
    private PurchaseOrderHead poHead;
    private Integer transportId;
    private String supplierName;
    private User buyer;
    private List<ASNLine> asnLineList;
    private List<PurchaseOrderLine> poLineList;
    private List<QCCertificate> qcCertificateList;
    private List<Document> documentList;
    private List<InvoiceHead> invoiceHeadList;

}
