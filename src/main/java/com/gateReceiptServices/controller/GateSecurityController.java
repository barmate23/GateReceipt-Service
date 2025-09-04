package com.gateReceiptServices.controller;


import com.gateReceiptServices.model.Transport;
import com.gateReceiptServices.request.UpdateBuyerStatus;
import com.gateReceiptServices.request.UpdateItemRequest;
import com.gateReceiptServices.request.UpdateTransportRequest;
import com.gateReceiptServices.response.BaseResponse;
import com.gateReceiptServices.response.POASNLineResponse;
import com.gateReceiptServices.service.GateSecurityService;
import com.gateReceiptServices.utils.APIConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
@RequestMapping({APIConstants.BASE_REQUEST + APIConstants.SERVICENAME})
public class GateSecurityController {
    @Autowired
    private GateSecurityService gateSecurityService;

    @GetMapping(APIConstants.GET_ASN_PO_DETAILS)
    public BaseResponse<POASNLineResponse> getAsnPODetails(@RequestParam String asnNumber, @RequestParam String purchaseOrderNumber) {
        return gateSecurityService.getAsnPODetails(asnNumber, purchaseOrderNumber);
    }

    @GetMapping(APIConstants.GET_TRANSPORT_DETAILS)
    public BaseResponse<Transport> getTransportDetails(@RequestParam Integer asnHeadId, @RequestParam Integer poHeadId) {
        return gateSecurityService.getTransportDetails(asnHeadId, poHeadId);
    }

    @PostMapping(APIConstants.UPDATE_ITEM_LINE)
    public BaseResponse<String> updateItemDetails(@RequestBody List<UpdateItemRequest> updateItemRequestList) {
        return gateSecurityService.updateItemDetails(updateItemRequestList);
    }

    @PostMapping(APIConstants.UPDATE_TRANSPORT)
    public BaseResponse<String> updateTransport(@RequestBody UpdateTransportRequest updateTransport) {
        return gateSecurityService.updateTransport(updateTransport);
    }

    @PostMapping(APIConstants.UPDATE_BUYER_STATUS)
    public BaseResponse<String> updateBuyerStatus(@RequestBody UpdateBuyerStatus updateBuyerStatus) {
        return gateSecurityService.updateBuyerStatus(updateBuyerStatus);
    }

}

