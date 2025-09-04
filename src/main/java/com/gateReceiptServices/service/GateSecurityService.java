package com.gateReceiptServices.service;

import com.gateReceiptServices.model.Transport;
import com.gateReceiptServices.request.UpdateBuyerStatus;
import com.gateReceiptServices.request.UpdateItemRequest;
import com.gateReceiptServices.request.UpdateTransportRequest;
import com.gateReceiptServices.response.BaseResponse;
import com.gateReceiptServices.response.POASNLineResponse;


import java.util.List;

public interface GateSecurityService {
    BaseResponse<POASNLineResponse> getAsnPODetails(String asnNumber, String purchaseOrderNumber);

    BaseResponse<Transport> getTransportDetails(Integer asnHeadId, Integer poHeadId);

    BaseResponse<String> updateItemDetails(List<UpdateItemRequest> updateItemRequestList);

    BaseResponse<String> updateTransport(UpdateTransportRequest updateTransport);

    BaseResponse<String> updateBuyerStatus(UpdateBuyerStatus updateBuyerStatus);
}
