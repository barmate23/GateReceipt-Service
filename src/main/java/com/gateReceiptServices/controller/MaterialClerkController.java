package com.gateReceiptServices.controller;


import com.gateReceiptServices.model.Document;
import com.gateReceiptServices.model.InvoiceHead;
import com.gateReceiptServices.model.QCCertificate;
import com.gateReceiptServices.request.*;
import com.gateReceiptServices.response.*;
import com.gateReceiptServices.service.MaterialClerkService;
import com.gateReceiptServices.utils.APIConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
@RequestMapping({APIConstants.BASE_REQUEST + APIConstants.SERVICENAME})
public class MaterialClerkController {
    @Autowired
    private MaterialClerkService materialClerkService;

    @GetMapping(APIConstants.GET_INVOICE_DETAILS)
    public BaseResponse<InvoiceHead> getInvoiceDetails(@RequestParam Integer asnHeadId, @RequestParam Integer poHeadId) {
        return materialClerkService.getInvoiceDetails(asnHeadId, poHeadId);
    }

    @GetMapping(APIConstants.GET_QC_CERTIFICATE)
    public BaseResponse<QCCertificate> getQcCertificate(@RequestParam Integer asnHeadId, @RequestParam Integer poHeadId) {
        return materialClerkService.getQcCertificate(asnHeadId, poHeadId);
    }

    @GetMapping(APIConstants.GET_DOCUMENTS)
    public BaseResponse<Document> getDocuments(@RequestParam Integer asnHeadId, @RequestParam Integer poHeadId) {
        return materialClerkService.getDocuments(asnHeadId, poHeadId);
    }

    @GetMapping(APIConstants.GET_MATERIAL_CLERK_RECEIPT)
    public BaseResponse<MaterialClerkReceiptResponse> getMaterialClerkReceipt(@RequestParam Integer asnHeadId, @RequestParam Integer poHeadId) {
        return materialClerkService.getMaterialClerkReceipt(asnHeadId, poHeadId);
    }

    @GetMapping(APIConstants.GENERATE_CIN_LABEL)
    public BaseResponse<CinLabelResponse> generateCinLabel(@RequestParam Integer asnHeadId, @RequestParam Integer poHeadId, @RequestParam Integer printerId) {
        return materialClerkService.generateCinLabel(asnHeadId, poHeadId, printerId);
    }

    @GetMapping(APIConstants.GENERATE_GATE_PASS)
    public BaseResponse<GatePassResponse> generateGatePass(@RequestParam Integer transportId) {
        return materialClerkService.generateGatePass(transportId);
    }


    @PostMapping(APIConstants.UPDATE_INVOICE_DETAILS)
    public BaseResponse<POASNLineResponse> updateInvoiceDetails(@RequestBody UpdateInvoiceRequest updateInvoiceRequest) {
        return materialClerkService.updateInvoiceDetails(updateInvoiceRequest);
    }

    @PostMapping(APIConstants.UPDATE_QC_CERTIFICATE)
    public BaseResponse<String> updateQcCertificate(@RequestBody List<UpdateQCRequest> updateQCRequestList) {
        return materialClerkService.updateQcCertificate(updateQCRequestList);
    }

    @PostMapping(APIConstants.UPDATE_PRINT_STATUS)
    public BaseResponse<String> updatePrintStatus(@RequestBody UpdatePrintStatus updatePrintStatus) {
        return materialClerkService.updatePrintStatus(updatePrintStatus);
    }

    @PostMapping(APIConstants.UPDATE_DOCUMENTS)
    public BaseResponse<String> updateDocuments(@RequestBody List<UpdateDocumentRequest> updateDocumentRequestList) {
        return materialClerkService.updateDocuments(updateDocumentRequestList);
    }

    @PostMapping(APIConstants.UPDATE_ASN_PO_ITEM_DETAILS)
    public BaseResponse<String> updateAsnPoItemDetails(@RequestBody List<UpdateItemRequest> updateItemRequestList) {
        return materialClerkService.updateAsnPoItemDetails(updateItemRequestList);
    }

}

