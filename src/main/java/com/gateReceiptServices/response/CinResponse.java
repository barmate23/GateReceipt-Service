package com.gateReceiptServices.response;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CinResponse {
    private String organizationName;
    private String cinNumber;
    private Integer invoiceNumber;
    private Date invoiceDate;
    private String transporter;
    private String vehicleNumber;
    private List<DockItem> dockItemList;
}
