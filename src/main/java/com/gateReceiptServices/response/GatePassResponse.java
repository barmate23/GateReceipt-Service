package com.gateReceiptServices.response;

import com.gateReceiptServices.model.GatePassGenerate;
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
public class GatePassResponse {
    private String organizationName;
    private String orgAddress;
    private String orgState;
    private String orgCity;
    private Integer orgPostCode;
    private String orgCountry;
    private String transporterFullName;
    private String date;
    private String vehicleNumber;
    private String comingFrom;
    private String registrationNumber;
    private String representing;
    private String timeIn;
    private String transporterAddress;
    List<StoreDockInfoResponse> storeDockInfoResponseList;
    List<AccompanyDetailsResponse> accompanyDetailsResponseList;
    private String currentUserName;
    private byte[] registrationNumberBarcode;


}
