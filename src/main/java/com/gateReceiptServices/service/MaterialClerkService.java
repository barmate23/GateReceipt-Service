package com.gateReceiptServices.service;

import com.gateReceiptServices.model.Document;
import com.gateReceiptServices.model.InvoiceHead;
import com.gateReceiptServices.model.QCCertificate;
import com.gateReceiptServices.request.*;
import com.gateReceiptServices.response.*;

import java.util.List;

public interface MaterialClerkService {
    BaseResponse<InvoiceHead> getInvoiceDetails(Integer asnHeadId, Integer poHeadId);
    BaseResponse<QCCertificate> getQcCertificate(Integer asnHeadId, Integer poHeadId);
    BaseResponse<Document> getDocuments(Integer asnHeadId, Integer poHeadId);
    BaseResponse<MaterialClerkReceiptResponse> getMaterialClerkReceipt(Integer asnHeadId, Integer poHeadId);
    BaseResponse<CinLabelResponse> generateCinLabel(Integer asnHeadId, Integer poHeadId, Integer printerId);
    BaseResponse<POASNLineResponse> updateInvoiceDetails(UpdateInvoiceRequest updateInvoiceRequest);
    BaseResponse<String> updateQcCertificate(List<UpdateQCRequest> updateQCRequestList);
    BaseResponse<String> updateDocuments(List<UpdateDocumentRequest> updateDocumentRequestList);
    BaseResponse<String> updateAsnPoItemDetails(List<UpdateItemRequest> updateItemRequestList);
    BaseResponse<GatePassResponse> generateGatePass(Integer trnsportId);
    BaseResponse<String> updatePrintStatus(UpdatePrintStatus updatePrintStatus);
}

