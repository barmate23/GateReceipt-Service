package com.gateReceiptServices.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTransportRequest {
    private Integer transporterId;
    private String transporterName;
    private String shipMode;
    private String shipThrough;
    private String contactName;
    private String mobileNo;
    private String landlineNo;
    private Integer lorryReceiptNumber;
    private String dispatchDate;
    private String dispatchTime;
    private String expectedArrivalDate;
    private String expectedArrivalTime;
    private String vehicleType;
    private String vehicleNumber;
    private Double vehicleWeight;
    private Integer pucCertificateNumber;
    private String pucCertificateNumberValidTill;
    private String pucCenter;
    private String pucCenterId;
    private String driver;
    private String licenceNumber;
    private String licenceNumberValidTill;
    private String licenceType;
    private String licenceTypeDate;
    private List<AccompaniedByRequest> accompaniedByList;
}
